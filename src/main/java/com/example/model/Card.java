package com.example.model;

/**
 * Represents a single card used in the memory game.
 * <p>
 * A card holds the path to its image and two state flags: whether it is
 * currently flipped (face-up) and whether it has been matched with its pair.
 * </p>
 */
public class Card {

    /** Path to the image resource shown on the card face. */
    private String imagePath;

    /** True when the card is currently revealed (face-up). */
    private boolean flipped;

    /** True when the card has been matched and should remain revealed. */
    private boolean matched;

    /**
     * Creates a new card with the given image path. The card is
     * initially not flipped and not matched.
     *
     * @param imagePath the path to the image resource for this card
     */
    public Card(String imagePath) {
        this.imagePath = imagePath;
        this.flipped = false;
        this.matched = false;
    }

    /**
     * Returns the image path associated with this card.
     *
     * @return the image resource path
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Returns whether the card is currently flipped (face-up).
     *
     * @return {@code true} if the card is flipped, otherwise {@code false}
     */
    public boolean isFlipped() {
        return flipped;
    }

    /**
     * Sets the flipped state of the card.
     *
     * @param flipped {@code true} to mark the card as flipped (face-up)
     */
    public void setFlipped(boolean flipped) {
        this.flipped = flipped;
    }

    /**
     * Returns whether this card has already been matched with its pair.
     *
     * @return {@code true} if the card is matched, otherwise {@code false}
     */
    public boolean isMatched() {
        return matched;
    }

    /**
     * Sets the matched state of the card. When matched the card typically
     * remains revealed for the rest of the round.
     *
     * @param matched {@code true} to mark the card as matched
     */
    public void setMatched(boolean matched) {
        this.matched = matched;
    }
}