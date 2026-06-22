package com.example.model;

import java.util.List;

/**
 * Holds the current board layout for a memory round.
 */
public final class GameBoard {

    private final int rows;
    private final int cols;
    private final List<Card> cards;

    public GameBoard(int rows, int cols, List<Card> cards) {
        this.rows = rows;
        this.cols = cols;
        this.cards = cards;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }


    public List<Card> getCards() {
        return cards;
    }
}