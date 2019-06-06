package com.game.twentyfoureight.ui;

import com.game.twentyfoureight.game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TitleFrame extends JFrame {

    JButton startBtn = new JButton("Start");

    JPanel pane;

    GameFrame frame;

    JTextField tf = new JTextField("Write size here");

    JLabel label = new JLabel("2048 game");

    int size = -1;


    public TitleFrame() {
        super("2048");
        pane = new JPanel();
        pane.setLayout(null);
        startBtn.setFont(new Font("Arial", Font.BOLD, 50));
        startBtn.setBounds(new Rectangle(50, 105, 300, 100));
        tf.setBounds(new Rectangle(50, 70, 300, 20));
        label.setFont(new Font("Arial", Font.BOLD, 55));
        label.setBounds(new Rectangle(25, 0, 400, 55));
        pane.add(label);
        pane.add(tf);
        pane.add(startBtn);

        setContentPane(pane);

        startBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    size = Integer.parseInt(tf.getText());
                    if (size > 1 && size < 7) {
                        setVisible(false);
                        prepareFrame(new Game(size), false);
                    } else {
                        tf.setText("Size should be in 2..6");
                    }
                } catch (NumberFormatException ex) {
                    tf.setText("ONLY DIGITS HERE!!!");
                }
            }
        });

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                prepareFrame(new Game(4), true);
            }
        });

        tf.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tf.setText("");
            }
        });

    }

    public void prepareFrame(Game game, Boolean bot) {
        frame = new GameFrame(game.getField().size(), game, bot);
        frame.addComponents();
        frame.update();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        if (bot) {
            frame.autoSolution();
        }
    }


}
