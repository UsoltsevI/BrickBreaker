package edu.phystech;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Board extends JPanel {
    private Timer timer;
    private String message = "Game Over";
    private String message2 = "Tap X to exit";
    private Ball ball;
    private ArrayList<Brick> bricks;
    private Paddle paddle;
    private boolean isGame= true;
    private final Commons commons; 

    public Board() {
        commons = new Commons();
        initBoard();
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setFocusable(isGame);
        setPreferredSize(new Dimension(commons.WIDTH, commons.HEIGHT));

        gameInit();
    }

    private void gameInit() {
        bricks = new ArrayList<>();

        ball   = new Ball();
        paddle = new Paddle();

        for (int i = 0; i < commons.NY_OF_BRICKS; i++) {
            for (int j = 0; j < commons.NX_OF_BRICKS; j++) {
                bricks.add(new Brick(j * 40 + 30, i * 10 + 50));
            }
        }

        timer = new Timer(commons.PERIOD, new GameCycle());
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING
            , RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHint(RenderingHints.KEY_RENDERING
            , RenderingHints.VALUE_RENDER_QUALITY);
        
        if (isGame) {
            drawObjects(g2d);

        } else {
            gameFinished(g2d);
        }

        Toolkit.getDefaultToolkit().sync();

    }

    private void drawObjects(Graphics2D g2d) {
        g2d.drawImage(ball.getImage(), ball.getX(), ball.getY()
            , ball.getImageWidth(), ball.getImageHeight(), this);
        g2d.drawImage(paddle.getImage(), paddle.getX(), paddle.getY()
            , paddle.getImageWidth(), paddle.getImageHeight(), this);

        for (int i = 0; i < bricks.size(); i++) {
            if (!bricks.get(i).isDestroyed()) {
                g2d.drawImage(bricks.get(i).getImage(), bricks.get(i).getX(), bricks.get(i).getY()
                    , bricks.get(i).getImageWidth(), bricks.get(i).getImageHeight(), this);
            }
        }
    }

    private void gameFinished(Graphics2D g2d) {
        Font font = new Font("Verdana", Font.BOLD, 18);
        FontMetrics fontMetrics = this.getFontMetrics(font);

        g2d.setColor(Color.BLACK);
        g2d.setFont(font);
        g2d.drawString(message
            , (commons.WIDTH - fontMetrics.stringWidth(message)) / 2
            , commons.WIDTH / 2);
        g2d.drawString(message2
            , (commons.WIDTH - fontMetrics.stringWidth(message)) / 2
            , commons.WIDTH / 2 + 40);
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            paddle.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            paddle.keyPressed(e);
        }
    }

    private class GameCycle implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            doGameCycle();
        } 
    }

    private void doGameCycle() {
        ball.move();
        paddle.move();
        checkCollision();
        repaint();
    }

    private void stopGame() {
        isGame = false;
        timer.stop();
    }

    private void checkCollision() {
        if (ball.getRect().getMaxY() > commons.BOTTOM_EDGE) {
            stopGame();
        }

        for (int i = 0, j = 0; i < bricks.size(); i++) {
            if (bricks.get(i).isDestroyed()) {
                j++;
            }

            if (j == bricks.size()) {
                message = "Victory";
                stopGame();
            }
        }

        if ((ball.getRect().intersects(paddle.getRect()))) {
            int paddlePos = (int) paddle.getRect().getMinX();
            int ballPos   = (int) ball.getRect().getMinX();

            int first  = paddlePos + 8;
            int second = paddlePos + 16;
            int third  = paddlePos + 24;
            int fourth = paddlePos + 32;

            if (ballPos < first) {
                ball.setXDir(-1);
                ball.setYDir(-1);

            } else if (ballPos >= first && ballPos < second) {
                ball.setXDir(-1);
                ball.setYDir(-1 * ball.getYDir());

            } else if (ballPos >= second && ballPos < third) {
                ball.setXDir(0);
                ball.setYDir(-1);

            } else if (ballPos >= third && ballPos <= fourth) {
                ball.setXDir(1);
                ball.setYDir(-1 * ball.getYDir());

            } else if (ballPos > fourth) {
                ball.setXDir(1);
                ball.setYDir(-1);
            }
        }

        for (int i = 0; i < bricks.size(); i++) {
            if (ball.getRect().intersects(bricks.get(i).getRect())) {
                int ballLeft   = (int) ball.getRect().getMinX();
                int ballHeight = (int) ball.getRect().getHeight();
                int ballWidth  = (int) ball.getRect().getWidth();
                int ballTop    = (int) ball.getRect().getMinY();

                Point pointRight  = new Point(ballLeft + ballWidth + 1, ballTop);
                Point pointLeft   = new Point(ballLeft - 1, ballTop);
                Point pointTop    = new Point(ballLeft, ballTop - 1);
                Point pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);

                if (!bricks.get(i).isDestroyed()) {
                    if (bricks.get(i).getRect().contains(pointRight)) {
                        ball.setXDir(-1);

                    } else if (bricks.get(i).getRect().contains(pointLeft)) {
                        ball.setXDir(1);
                    }

                    if (bricks.get(i).getRect().contains(pointTop)) {
                        ball.setYDir(1);

                    } else if (bricks.get(i).getRect().contains(pointBottom)) {
                        ball.setYDir(-1);
                    }

                    bricks.get(i).setDestroyed(true);
                }
            }
        }
    }
}
