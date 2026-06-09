package com.example.model;

/**
 * Datos de una partida finalizada.
 * Pásalo a ScoreView.showResults() para mostrar los resultados.
 */
public record ScoreEntry(
        String playerName,
        int    score,
        int    attempts,
        int    pairsFound,
        long   timeSeconds,
        String gameMode
) {
    public double efficiencyPercent() {
        if (attempts == 0) return 100.0;
        return Math.min(100.0, (double) pairsFound / attempts * 100.0);
    }

    public String formattedTime() {
        return String.format("%02d:%02d", timeSeconds / 60, timeSeconds % 60);
    }
}
