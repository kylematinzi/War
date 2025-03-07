package com.wargame.war;

/**
 * This class defines a card.
 */
public class Card {
    private final String suit;
    private final int value;
    private final String rank;

    /**
     * Card constructor
     * @param suit - card suit.
     * @param value - number on the card
     */
    public Card(String suit, int value) {
        this.suit = suit;
        this.value = value;
        this.rank = getRankFromValue(value);
    }

    /**
     * Method converts the value of the face cards to a readable string. Ace through Jack.
     * @param value - numeric value of given face card
     * @return - readable string of the rank
     */
    private String getRankFromValue(int value) {
        return switch (value) {
            // enhanced switch vs traditional switch. Takes numeric value and moves to string name
            case 14 -> "ace";
            case 13 -> "king";
            case 12 -> "queen";
            case 11 -> "jack";
            default -> String.valueOf(value); // default will return cards that already have their numbers 2-10
        };
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
     * Will return a string that matches the cards filename format. Card files are labeled as "ace_of_spades".
     * All lowercase.
     * @return -
     */
    @Override
    public String toString() {
        return rank + "_of_" + suit.toLowerCase();
    }

    /**
     * Will return a readable version of a card.
     * @return - User readable card description.
     */
    public String toReadableString() {
        String readableRank = rank.substring(0, 1).toUpperCase() + rank.substring(1);
        return readableRank+" of " + suit;
    }
}
