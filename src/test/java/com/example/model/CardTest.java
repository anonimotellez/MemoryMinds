package com.example.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CardTest {

    @Test
    public void constructorAndGetters() {
        Card c = new Card("cat");
        assertEquals("cat", c.getTextDisplay());
        assertFalse(c.isFlipped());
        assertFalse(c.isMatched());
    }

    @Test
    public void setFlippedAndMatched() {
        Card c = new Card("cat");
        c.setFlipped(true);
        assertTrue(c.isFlipped());

        c.setMatched(true);
        assertTrue(c.isMatched());

        c.setFlipped(false);
        assertFalse(c.isFlipped());
    }

    @Test
    public void constructorWithId() {
        Card c = new Card("cat", "id123");
        assertEquals("cat", c.getTextDisplay());
        assertEquals("id123", c.getId());
    }
}
