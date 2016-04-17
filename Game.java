package org.softuni;

import javax.swing.*;

import static javax.swing.JFrame.*;

public class Game {

    public static void main(String[] args) {

        JFrame window = new JFrame("Game of Thrones");             // setting a title
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);     // the application will be closed when pressing the X button

        window.setContentPane(new GamePanel());

        window.pack();
        window.setVisible(true);

    }
}
