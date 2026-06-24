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

        addPair(cards, "1", "1 + 1", "2");
        addPair(cards, "2", "5 - 2", "3");
        addPair(cards, "3", "2 + 2", "4");
        addPair(cards, "4", "8 - 3", "5");
        addPair(cards, "5", "3 + 3", "6");
        addPair(cards, "6", "10 - 3", "7");
        addPair(cards, "7", "4 + 4", "8");
        addPair(cards, "8", "12 - 3", "9");

        Collections.shuffle(cards);
        return cards;
    }

    public static List<Card> createEnglishDeck() {
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

    public static List<Card> createCultureDeck(){
        List<Card> cards = new ArrayList<>();

        addPair(cards, "1", "Colombia", "Bogotá");
        addPair(cards, "2", "Perú", "Lima");
        addPair(cards, "3", "Japón", "Yen");
        addPair(cards, "4", "Estados Unidos", "Dólar");
        addPair(cards, "5", "Brasil", "Portugués");
        addPair(cards, "6", "Francia", "Francés");
        addPair(cards, "7", "Alemania", "Berlín");
        addPair(cards, "8", "México", "Peso Mexicano");

        Collections.shuffle(cards);
        return cards;
    }

    public static List<Card> createHistoryDeck(){
        List<Card> cards = new ArrayList<>();

        addPair(cards, "1", "Libertador de Colombia", "Simón Bolívar");
        addPair(cards, "2", "Descubrió América", "Cristóbal Colón");
        addPair(cards, "3", "Primer presidente de EE.UU.", "George Washington");
        addPair(cards, "4", "Independencia de Colombia", "1810");
        addPair(cards, "5", "Llegada a la Luna", "1969");
        addPair(cards, "6", "Revolución Francesa", "1789");
        addPair(cards, "7", "Imperio Romano", "Roma");
        addPair(cards, "8", "Antiguo Egipto", "Pirámides");

        Collections.shuffle(cards);
        return cards;
    }

    private static void addPair(List<Card> cards, String id, String firstText, String secondText) {
        cards.add(new Card(firstText, id));
        cards.add(new Card(secondText, id));
    }
}