package edu.phystech;

import javax.swing.JFrame;
import java.awt.EventQueue;

public class Breakout extends JFrame {
    public Breakout() {
        initUI();
    }

    private void initUI() {
        add(new Board());
        setTitle("Breakout");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        pack();
    }

    public static void loadGame() {
        EventQueue.invokeLater(() -> {
            Breakout game = new Breakout();
            game.setVisible(true);
            });
    }

    public static void main(String[] args) {
        loadGame();
    }
}
