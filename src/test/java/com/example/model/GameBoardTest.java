package com.example.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class GameBoardTest {
    @Test
    public void constructorAndGetters() {
        List<Card> cards = List.of(new Card("A"), new Card("B"), new Card("C"));
        GameBoard board = new GameBoard(2, 3, cards);
        assertEquals(2, board.getRows());
        assertEquals(3, board.getCols());
        assertEquals(cards, board.getCards());
    }
}
