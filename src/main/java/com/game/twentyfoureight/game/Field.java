package com.game.twentyfoureight.game;

import java.io.*;

public class Field implements Cloneable {

    private Integer size;

    private Cell[][] field;

    public Field(Integer size) {
        this.size = size;
        field = new Cell[size][size];
        initialization();
    }

    private void initialization() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field[i][j] = new Cell();
            }
        }
    }

    public Boolean addCell(Cell cell, Integer x, Integer y) {
        if (field[x][y].isEmpty()) {
            field[x][y] = cell;
            return true;
        }
        return false;
    }

    private Boolean canMove(Integer x, Integer y, Integer dx, Integer dy) {
        return x + dx < size && y + dy < size && x + dx >= 0 && y + dy >= 0 &&
                (field[x][y].getValue() == field[x + dx][y + dy].getValue() ||
                        (field[x + dx][y + dy].isEmpty() && !field[x][y].isEmpty()));
    }

    public Boolean abilityToSwipe(Directions direction) {
        Integer dx = 0, dy = 0;
        switch (direction) {
            case UP:
                dy = 1;
                break;

            case DOWN:
                dy = -1;
                break;

            case LEFT:
                dx = -1;
                break;

            case RIGHT:
                dx = 1;
                break;
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (canMove(i, j, dx, dy)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Integer swipe(Directions direction) {
        Integer score = 0;
        switch (direction) {
            case RIGHT:
                score += swipeRight(1);
                break;

            case LEFT:
                score += swipeLeft(-1);
                break;

            case UP:
                score += swipeUp(-1);
                break;

            case DOWN:
                score += swipeDown(1);
                break;
        }
        return score;
    }

    private int verticalSwipe(int k, int i, int dx) {
        int score = 0;
        if (canMove(k, i, dx, 0)) {
            if (field[k + dx][i].isEmpty()) {
                field[k + dx][i] = field[k][i];
                field[k][i] = new Cell();
            } else {
                score += field[k + dx][i].getValue() * 2;
                field[k + dx][i].doubleValue();
                field[k][i] = new Cell();
            }
        }
        return score;
    }

    public Boolean haveEmptyCells() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (field[i][j].isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    private int horizSwipe(int k, int i, int dy) {
        int score = 0;
        if (canMove(i, k, 0, dy)) {
            if (field[i][k + dy].isEmpty()) {
                field[i][k + dy] = field[i][k];
                field[i][k] = new Cell();
            } else {
                score += field[i][k + dy].getValue() * 2;
                field[i][k + dy].doubleValue();
                field[i][k] = new Cell();
            }
        }
        return score;
    }

    private int swipeUp(int dy) {
        int score = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = j; k > 0; k--) {
                    score += verticalSwipe(k, i, dy);
                }
            }
        }
        return score;
    }

    private int swipeDown(int dy) {
        int score = 0;
        for (int i = 0; i < size; i++) {
            for (int j = size - 1; j >= 0; j--) {
                for (int k = j; k < size - 1; k++) {
                    score += verticalSwipe(k, i, dy);
                }
            }
        }
        return score;
    }

    private int swipeLeft(int dx) {
        int score = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = j; k > 0; k--) {
                    score += horizSwipe(k, i, dx);
                }
            }
        }
        return score;
    }

    private int swipeRight(int dx) {
        int score = 0;
        for (int i = 0; i < size; i++) {
            for (int j = size - 1; j >= 0; j--) {
                for (int k = j; k < size - 1; k++) {
                    score += horizSwipe(k, i, dx);
                }
            }
        }
        return score;
    }

    @Override
    public Field clone() {
        Field f;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream ous = new ObjectOutputStream(baos);
            ous.writeObject(field);
            ous.close();
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            f = (Field) ois.readObject();
        } catch (IOException ex) {
            f = new Field(size);
        } catch (ClassNotFoundException ex) {
            f = new Field(size);
        }
        return f;
    }

    public Integer size() {
        return size;
    }

    public Cell[][] getField() {
        return field;
    }

}
