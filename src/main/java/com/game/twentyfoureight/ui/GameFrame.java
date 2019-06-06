package com.game.twentyfoureight.ui;

import com.game.twentyfoureight.bot.Bot;
import com.game.twentyfoureight.game.Directions;
import com.game.twentyfoureight.game.Game;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class GameFrame extends JFrame {
    private int size;
    private CellUi[][] cells;
    private JLabel scoreLabel;
    private Game game;
    private GameFrame thisFrame;

    public GameFrame(int size, Game game, Boolean bot) {
        super("2048");
        this.size = size;
        this.game = game;
        thisFrame = this;
    }

    public void addComponents() {
        JPanel panel = new JPanel();
        GridLayout layout = new GridLayout(size, size);
        layout.setHgap(10);
        layout.setVgap(10);
        panel.setLayout(layout);
        panel.setBackground(new Color(0xbb, 0xad, 0xa0));
        cells = new CellUi[size][size];
        for (int y = 0; y < size; y++)
            for (int x = 0; x < size; x++) {
                cells[y][x] = new CellUi();
                cells[y][x].setValue(0);
                panel.add(cells[y][x]);
            }
        scoreLabel = new JLabel("Score: 0");
        getContentPane().add(scoreLabel, BorderLayout.NORTH);
        getContentPane().add(new JSeparator(), BorderLayout.CENTER);
        getContentPane().add(panel, BorderLayout.SOUTH);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_DOWN: {
                        game.swipe(Directions.DOWN);
                    }
                    break;
                    case KeyEvent.VK_UP: {
                        game.swipe(Directions.UP);
                    }
                    break;
                    case KeyEvent.VK_LEFT: {
                        game.swipe(Directions.LEFT);
                    }
                    break;
                    case KeyEvent.VK_RIGHT: {
                        game.swipe(Directions.RIGHT);
                    }
                    break;
                }
                update();
                if(game.loose()) {
                    JOptionPane
                            .showMessageDialog(null, "GAME OVER \nYour score is: " + game.getScore() , "GAME OVER", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void autoSolution() {
        update();
        Bot b = new Bot(game, 100);
        while (!game.loose()) {
            game.swipe(b.run());
            update();
        }

    }

    public void update() {
        for (int y = 0; y < size; y++)
            for (int x = 0; x < size; x++)
                cells[y][x].setValue(game.getField().getField()[y][x].getValue());
        scoreLabel.setText("Score: " + game.getScore());
    }
}