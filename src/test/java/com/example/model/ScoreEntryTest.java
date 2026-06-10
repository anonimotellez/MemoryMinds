package com.example.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreEntryTest {

    @Test
    public void efficiencyWhenNoAttempts() {
        ScoreEntry s = new ScoreEntry("Player", 100, 0, 5, 90, "Modo");
        assertEquals(100.0, s.efficiencyPercent(), 1e-9);
    }

    @Test
    public void efficiencyNormal() {
        ScoreEntry s = new ScoreEntry("Player", 80, 10, 7, 120, "Modo");
        assertEquals(70.0, s.efficiencyPercent(), 1e-9);
    }

    @Test
    public void formattedTime() {
        ScoreEntry s = new ScoreEntry("Player", 0, 0, 0, 125, "Modo");
        assertEquals("02:05", s.formattedTime());
    }
}
