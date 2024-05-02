package edu.phystech;

import javax.swing.ImageIcon;

public class Ball extends Sprite {
    private int xdir;
    private int ydir;
    private final Commons commons;

    public Ball() {
        commons = new Commons();
        initBall();
    }

    private void initBall() {
        xdir = 1;
        ydir = -1;

        loadImage();
        getImageDimensions();
        resetState();
    }

    public void move() {
        x += xdir;
        y += ydir;

        if (x == 0) {
            setXDir(1);
        }

        if (x == commons.WIDTH - imageWidth) {
            System.out.println(imageWidth);
            setXDir(-1);
        }

        if (y == 0) {
            setYDir(1);
        }
    }

    private void loadImage() {
        ImageIcon ii = new ImageIcon("src/main/resources/ball.png");
        image = ii.getImage();
    }

    private void resetState() {
        x = commons.INIT_BALL_X;
        y = commons.INIT_BALL_Y;
    }

    public void setXDir(int xdir) {
        this.xdir = xdir;
    }

    public void setYDir(int ydir) {
        this.ydir = ydir;
    }

    public int getXDir() {
        return xdir;
    }

    public int getYDir() {
        return ydir;
    }
}
