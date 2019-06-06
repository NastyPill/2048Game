package com.game.twentyfoureight.game;

public class Game {

    private Field field;

    private Boolean gameIsOver;

    private Integer score;

    private Integer moves = 0;

    public Game(Integer size) {
        score = 0;
        gameIsOver = false;
        field = new Field(size);
        addRandomCell();
        addRandomCell();
    }

    public Game(Field field, int score) {
        this.score = score;
        gameIsOver = false;
        this.field = field;
    }

    private Integer randomCoord() {
        int size = field.size();
        int c = -1;
        switch (size) {
            case 2:
                c = (Math.random() > 0.5) ? 1 : 0;
                break;

            case 3:
                c = ((Math.random() > 0.4) ? 1 : 0) + ((Math.random() > 0.5) ? 1 : 0);
                break;

            case 4:
                c = ((Math.random() > 0.5) ? 2 : 0) + ((Math.random() > 0.5) ? 1 : 0);
                break;

            case 5:
                c = ((Math.random() > 0.5) ? 2 : 0) + ((Math.random() > 0.5) ? 1 : 0)
                        + ((Math.random() > 0.5) ? 1 : 0);
                break;

            case 6:
                c = ((Math.random() > 0.5) ? 2 : 0) + ((Math.random() > 0.5) ? 2 : 0)
                        + ((Math.random() > 0.5) ? 1 : 0);
                break;

        }
        return c;
    }

    private void addRandomCell() {
        if (!gameIsOver && field.haveEmptyCells()) {
            int x = randomCoord();
            int y = randomCoord();
            while (!field.addCell(new Cell((Math.random() > 0.85) ? 4 : 2), x, y)) {
                x = randomCoord();
                y = randomCoord();
            }
        }
    }

    public void swipe(Directions direction) {
        moves++;
        score += field.swipe(direction);
        addRandomCell();
        gameIsOver = gameOver();
    }

    public Boolean gameOver() {
        for (Directions dir : Directions.values()) {
            if (field.abilityToSwipe(dir) || field.haveEmptyCells()) {
                return false;
            }
        }
        return true;
    }

    public Boolean loose() {
        return gameIsOver;
    }

    public Field getField() {
        return field;
    }

    public Integer getScore() {
        return score;
    }

    public Integer getMoves() {
        return moves;
    }

}
