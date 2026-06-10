package com.example.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CardTest {

    @Test
    public void constructorAndGetters() {
        Card c = new Card("img.png");
        assertEquals("img.png", c.getImagePath());
        assertFalse(c.isFlipped());
        assertFalse(c.isMatched());
    }

    @Test
    public void setFlippedAndMatched() {
        Card c = new Card("img.png");
        c.setFlipped(true);
        assertTrue(c.isFlipped());

        c.setMatched(true);
        assertTrue(c.isMatched());

        c.setFlipped(false);
        assertFalse(c.isFlipped());
    }
}
