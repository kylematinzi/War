package com.wargame.war;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class is used to create a deck of 52 cards. It will call on the Card class to create all cards and sort them
 * into an arraylist to create a deck. Values range from 1 to 14. Aces are high at 14.
 */
public class Deck {

    private ArrayList<Card> deck = new ArrayList<>();

    /**
     * Method creates a deck by adding 14 of suits of each card to the deck.
     */
    public Deck() {
        for(int x = 0; x < 14; x++) {
            deck.add(new Card("Heart", x+1));
        }
        for(int x = 0; x < 14; x++) {
            deck.add(new Card("Spade", x+1));
        }
        for(int x = 0; x < 14; x++) {
            deck.add(new Card("Diamond", x+1));
        }
        for(int x = 0; x < 14; x++) {
            deck.add(new Card("Club", x+1));
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
