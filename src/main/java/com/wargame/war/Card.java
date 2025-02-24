package com.wargame.war;

/**
 * This class defines a card.
 */
public class Card {
    private final String suit;
    private final int value;
    String CardDetails ;

    /**
     * Card constructor
     * @param suit - card suit.
     * @param value - number on the card
     */
    public Card(String suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    /**
     * @return - cards suit.
     */
    public String getSuit() {
        return suit;
    }

    /**
     * @return - cards value.
     */
    public int getValue() {
        return value;
    }

    /**
     * @return - card's suit and value.
     */
    public String toString() {
        return CardDetails = suit + " " + value;
    }
}
