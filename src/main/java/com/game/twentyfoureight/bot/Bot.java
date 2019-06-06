package com.game.twentyfoureight.bot;

import com.game.twentyfoureight.game.*;

public class Bot{

    Game currentGame;

    Field field;

    int count;

    long[] score;

    public Bot(Game game, int count) {
        this.field = game.getField();
        this.currentGame = game;
        this.count = count;
        score = new long[]{0l, 0l, 0l, 0l};
    }

    public Directions run() {
        int ctr = 0;
        int max[] = {-1,-1,-1,-1};
        for (Directions direction : Directions.values()) {
            for (int i = 0; i < count; i++) {
                Game game = new Game(field.clone(), 0);
                game.swipe(direction);
                score[ctr] += mulipleRandomMoves(game);
                if(max[ctr] < game.getScore()) {
                    max[ctr] = game.getScore();
                }
            }
            score[ctr] /= count;
            ctr++;
        }
        return whatBetter();
    }

    private Directions whatBetter() {
        long max = -1;
        int index = -1;
        for (int i = 0; i < score.length; i++) {
            if (max < score[i]) {
                index = i;
                max = score[i];
            }
        }
        if (score[0] == score[1] && score[0] == score[2] && score[0] == score[3]) {
            return randomDirection();
        } else {
            return Directions.values()[index];
        }
    }

    private long mulipleRandomMoves(Game game) {
        while (!game.loose()) {
            game.swipe(randomDirection());
        }
        return game.getScore();
    }

    private Directions randomDirection() {
        int i = Math.random() > 0.5 ? 2 : 0;
        i += Math.random() > 0.5 ? 1 : 0;
        return Directions.values()[i];
    }
}
