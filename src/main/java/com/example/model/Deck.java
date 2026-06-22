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

        addPair(cards, "1", "2", "2");
        addPair(cards, "2", "3", "3");
        addPair(cards, "3", "4", "4");
        addPair(cards, "4", "5", "5");
        addPair(cards, "5", "6", "6");
        addPair(cards, "6", "7", "7");
        addPair(cards, "7", "8", "8");
        addPair(cards, "8", "9", "9");

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

        addPair(cards, "1", "Átomo", "Atom");
        addPair(cards, "2", "Célula", "Cell");
        addPair(cards, "3", "Planeta", "Planet");
        addPair(cards, "4", "Energía", "Energy");
        addPair(cards, "5", "Gravedad", "Gravity");
        addPair(cards, "6", "Fotosíntesis", "Photosynthesis");
        addPair(cards, "7", "Sistema Solar", "Solar System");
        addPair(cards, "8", "Materia", "Matter");

        Collections.shuffle(cards);
        return cards;
    }

    private static void addPair(List<Card> cards, String id, String firstText, String secondText) {
        cards.add(new Card(firstText, id));
        cards.add(new Card(secondText, id));
    }
}