package edu.phystech;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MenuScreen extends JPanel {
    JButton startButton = new JButton("Start Game");
    JButton themeButton = new JButton("Change theme");
    private Commons commons = new Commons();

    public MenuScreen() {
        initMenu();
    }

    private void initMenu() {
        setFocusable(true);
        setPreferredSize(new Dimension(commons.WIDTH, commons.HEIGHT));

        setLayout(null);
        startButton.setBounds(commons.WIDTH / 2, 100, 120, 40);
        themeButton.setBounds(commons.WIDTH / 2, 200, 120, 40);
        add(startButton);
        add(themeButton);

        StartHendler stHendler = new StartHendler();
        startButton.addActionListener(stHendler);
        ThemeHendler thHendler = new ThemeHendler();
        themeButton.addActionListener(thHendler);
    }

    private class StartHendler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // try {
                setLayout(new FlowLayout());
                revalidate();
                repaint();
                Board gameBoard = new Board();
                add(gameBoard);
                gameBoard.requestFocusInWindow();
                remove(startButton);
                remove(themeButton);
            // } catch (IOException ex) {
            //     ex.printStackTrace();
            // }
        }
    }

    private class ThemeHendler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                setLayout(new BorderLayout());
                revalidate();
                repaint();
                ThemeMenu tMenu = new ThemeMenu();
                add(tMenu);
                tMenu.requestFocusInWindow();
                remove(startButton);
                remove(themeButton);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawTitle(g2d);
    }

    private void drawTitle(Graphics2D g2d) {
        Font font = new Font("Verdana", Font.BOLD, 30);
        FontMetrics fontMetrics = this.getFontMetrics(font);

        g2d.setColor(Color.GRAY);
        g2d.setFont(font);
        g2d.drawString("Brick Breaker!",
                (commons.WIDTH - fontMetrics.stringWidth("Brick Breaker!")) / 2,
                50);
        g2d.setColor(Color.DARK_GRAY);
        g2d.setFont(font);
        g2d.drawString("Brick Breaker!",
                ((commons.WIDTH - fontMetrics.stringWidth("Brick Breaker!")) / 2) + 3,
                52);
        g2d.setColor(Color.BLACK);
        g2d.setFont(font);
        g2d.drawString("Brick Breaker!",
                ((commons.WIDTH - fontMetrics.stringWidth("Brick Breaker!")) / 2) + 6,
                54);

    }

}
