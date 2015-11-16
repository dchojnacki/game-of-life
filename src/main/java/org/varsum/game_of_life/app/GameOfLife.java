package org.varsum.game_of_life.app;

import javax.swing.*;

/**
 * @author The Watchmaker
 */
public class GameOfLife extends JFrame {

    public static void main(String[] args) {
        BoardFrame life = new BoardFrame();

        life.setLocationRelativeTo(null);
        life.show();
    }
}
