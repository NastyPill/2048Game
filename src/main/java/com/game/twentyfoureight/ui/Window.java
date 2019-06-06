package com.game.twentyfoureight.ui;

import javax.swing.*;

public class Window {

    static TitleFrame frame;

    public static void main(String[] args) {
        prepareFrame();
    }

    public static void prepareFrame() {
        frame = new TitleFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setSize(400, 250);
    }

}
