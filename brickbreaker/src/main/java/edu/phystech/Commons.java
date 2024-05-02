package edu.phystech;

public class Commons {
    int WIDTH       = 300;
    int HEIGHT      = 400;
    int BOTTOM_EDGE = 390;
    int NX_OF_BRICKS = 6;
    int NY_OF_BRICKS = 6;
    int INIT_PADDLE_X = 200;
    int INIT_PADDLE_Y = 360;
    int INIT_BALL_X = 230;
    int INIT_BALL_Y = 355;    
    int PERIOD      = 10;

    public void setScale(int coef) {
        WIDTH       *= coef;
        HEIGHT      *= coef;
        BOTTOM_EDGE *= coef;
        INIT_PADDLE_X *= coef;
        INIT_PADDLE_Y *= coef;
        INIT_BALL_X *= coef;
        INIT_BALL_Y *= coef;  
    }

    public void setNumOfBricks(int x, int y) {
        NX_OF_BRICKS = x;
        NY_OF_BRICKS = y;
    }
}
