package com.example.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Builds the set of cards used in a memory round.
 */
public final class Deck {

    public static List<Card> createMathDeck() {
        List<Card> cards = new ArrayList<>();

        addPair(cards, "1", "√144", "12");
        addPair(cards, "2", "8 × 8", "64");
        addPair(cards, "3", "13 + 19", "32");
        addPair(cards, "4", "81 ÷ 9", "9");
        addPair(cards, "5", "17 × 2", "34");
        addPair(cards, "6", "90 − 27", "63");
        addPair(cards, "7", "11 × 7", "77");
        addPair(cards, "8", "56 ÷ 7", "8");

        Collections.shuffle(cards);
        return cards;
    }

    public static List<Card> createSEnglishDeck() {
        List<Card> cards = new ArrayList<>();

        addPair(cards, "1", "Gato", "Cat");
        addPair(cards, "2", "Perro", "Dog");
        addPair(cards, "3", "Luna", "Moon");
        addPair(cards, "4", "Sol", "Sun");
        addPair(cards, "5", "Rojo", "Red");
        addPair(cards, "6", "Azul", "Blue");
        addPair(cards, "7", "Fuego", "Fire");
        addPair(cards, "8", "Agua", "Water");

        Collections.shuffle(cards);
        return cards;
    }

    public static List<Card> createScienceDeck() {
        List<Card> cards = new ArrayList<>();

        addPair(cards, "1", "H ", "Hidrogeno");
        addPair(cards, "2", "O", "Oxigeno");
        addPair(cards, "3", "Na", "Sodio");
        addPair(cards, "4", "K", "Potasio");
        addPair(cards, "5", "Fe", "Hierro");
        addPair(cards, "6", "Au", "Oro");
        addPair(cards, "7", "Ag", "Plata");
        addPair(cards, "8", "Ci", "Cloro");

        Collections.shuffle(cards);
        return cards;
    }

    private static void addPair(List<Card> cards, String id, String firstText, String secondText) {
        cards.add(new Card(firstText, id));
        cards.add(new Card(secondText, id));
    }
}