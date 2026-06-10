package com.example.model;

/**
 * Immutable data for a finished game round.
 * <p>
 * An instance contains the player's name, the computed score, attempt
 * statistics and the game mode used. This record is intended to be passed
 * to the view layer (for example {@code ScoreView.showResults()}).
 * </p>
 *
 * @param playerName  the name of the player who played the round
 * @param score       the numeric score achieved by the player
 * @param attempts    the number of flip attempts made during the round
 * @param pairsFound  the number of matched pairs found by the player
 * @param timeSeconds the duration of the round in seconds
 * @param gameMode    textual identifier of the selected game mode
 */
public record ScoreEntry(
        String playerName,
        int    score,
        int    attempts,
        int    pairsFound,
        long   timeSeconds,
        String gameMode
) {
    /**
     * Calculates the efficiency as the percentage of successful pairs per attempt.
     * If no attempts were made the method returns 100.0.
     *
     * @return efficiency percentage in the range 0.0 - 100.0
     */
    public double efficiencyPercent() {
        if (attempts == 0) return 100.0;
        return Math.min(100.0, (double) pairsFound / attempts * 100.0);
    }

    /**
     * Formats the stored seconds into a MM:SS string suitable for display.
     *
     * @return formatted time string in minutes and seconds
     */
    public String formattedTime() {
        return String.format("%02d:%02d", timeSeconds / 60, timeSeconds % 60);
    }
}
