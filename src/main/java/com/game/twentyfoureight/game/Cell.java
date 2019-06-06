package com.game.twentyfoureight.game;

public class Cell {

    private Boolean empty;
    private int value;

    public Cell() {
        this.value = 0;
        this.empty = true;
    }

    public Cell(Integer value) {
        this.empty = false;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Boolean isEmpty() {
        return empty;
    }

    public void doubleValue() {
        value += value;
    }
}
