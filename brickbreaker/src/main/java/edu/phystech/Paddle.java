package edu.phystech;

import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Paddle extends Sprite {
    private int dx;
    private final Commons commons;

    public Paddle() {
        commons = new Commons();
        initPaddle();
    }

    private void initPaddle() {
        loadImage();
        getImageDimensions();
        resetState();
    }

    private void loadImage() {
        ImageIcon ii = new ImageIcon("src/main/resources/paddle.png");
        image = ii.getImage();
    }

    private void resetState() {
        x = commons.INIT_PADDLE_X;
        y = commons.INIT_PADDLE_Y;
    }

    public void move() {
        x += dx;

        if (x <= 0) {
            x = 0;
        }

        if (x >= commons.WIDTH - imageWidth) {
            x = commons.WIDTH - imageWidth;
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -1;

        } else if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;

        } else if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }
}
