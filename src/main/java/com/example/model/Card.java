package com.example.model;

public class Card {

    private String imagePath;
    private boolean flipped;
    private boolean matched;

    public Card(String imagePath) {
        this.imagePath = imagePath;
        this.flipped = false;
        this.matched = false;
    }

    public String getImagePath() {
        return imagePath;
    }

    public boolean isFlipped() {
        return flipped;
    }

    public void setFlipped(boolean flipped) {
        this.flipped = flipped;
    }

    public boolean isMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }
}