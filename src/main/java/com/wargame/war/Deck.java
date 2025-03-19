package com.wargame.war;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class is used to create a deck of 52 cards. It will call on the Card class to create all cards and sort them
 * into an arraylist to create a deck. Values range from 1 to 14. Aces are high at 14.
 */
public class Deck {

    private final ArrayList<Card> deck = new ArrayList<>();

    /**
     * Method creates a deck by adding 14 of suits of each card to the deck.
     */
    public Deck() {
        for(int x = 1; x < 14; x++) {
            deck.add(new Card("Hearts", x+1));
        }
        for(int x = 1; x < 14; x++) {
            deck.add(new Card("Spades", x+1));
        }
        for(int x = 1; x < 14; x++) {
            deck.add(new Card("Diamonds", x+1));
        }
        for(int x = 1; x < 14; x++) {
            deck.add(new Card("Clubs", x+1));
        }
    }

    /**
     * Shuffles the deck.
     */
    public void shuffle() {
        Collections.shuffle(deck);
    }

    /**
     * Gets a single card from the deck.
     * @param i - index of the card in the deck.
     * @return - returns the specific card.
     */
    public Card get(int i) {
        return deck.get(i);
    }
}