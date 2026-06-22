package com.example.model;

/**
 * Represents a single card used in the memory game.
 * <p>
 * A card holds the text to display on its face and two state flags: whether it is
 * currently flipped (face-up) and whether it has been matched with its pair.
 * </p>
 */
public class Card {

    /** Text displayed on the card face. */
    private String textDisplay;

    /** True when the card is currently revealed (face-up). */
    private boolean flipped;

    /** True when the card has been matched and should remain revealed. */
    private boolean matched;

    /** Unique identifier for the card. */
    private String id;

    /**
     * Creates a new card with the given text. The card is
     * initially not flipped and not matched.
     *
     * @param textDisplay the text to display on the card face
     * @param id the unique identifier for the card
     */
    public Card(String textDisplay, String id) {
        this.textDisplay = textDisplay;
        this.id = id;
        this.flipped = false;
        this.matched = false;
    }

    /**
     * Creates a new card using the same value for text and id.
     * This is convenient when the visible symbol also identifies the pair.
     *
     * @param textDisplay the text to display on the card face and the pair id
     */
    public Card(String textDisplay) {
        this(textDisplay, textDisplay);
    }

    /**
     * Returns the text to display on the card face.
     *
     * @return the text to display on the card face
     */
    public String getTextDisplay() {
        return textDisplay;
    }

    /**
     * Returns the unique identifier for the card.
     *
     * @return the unique identifier for the card
     */
    public String getId() {
        return id;
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