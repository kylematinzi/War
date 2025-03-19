package com.wargame.war;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class runs the game. Each player will be dealt half of the deck at the beginning of the game. That dealing will
 * be each players' current hand. All cards the player wins will be put into a separate stack "pile". When a player's
 * hand runs out they will pick up their stack, shuffle, and use it as their new hand. When a player runs out of cards
 * the opposing player wins.
 */
public class Game {

    private Player playerOne = new Player(); // Player one
    private Player playerTwo = new Player(); // Player two
    private ArrayList<Card> currentHandOne = new ArrayList<>(); // Player one's current hand
    private ArrayList<Card> currentHandTwo = new ArrayList<>(); // Player two's current hand
    private ArrayList<Card> stackOne = new ArrayList<>(); // Player one's stack of cards won from player two
    private ArrayList<Card> stackTwo = new ArrayList<>(); // Player two's stack of cards won from player one
    private boolean playerOneWin = false; // True if player one is the winner
    private boolean playerTwoWin = false; // True if player two is the winner
    private String winner = ""; // Winners name
    private Card lastPlayerOneCard;
    private Card lastPlayerTwoCard;
    private String lastResult;

    /**
     * Constructor. This method asks for two player names, will initialize the players, and deal the deck between the
     * two calling the dealCards method. Player names may be what ever characters a person decides.
     *
     * @param playerOneName - Player ones name
     * @param playerTwoName - Player twos name
     */
    public Game(String playerOneName, String playerTwoName) {
        playerOne.setName(playerOneName);
        playerTwo.setName(playerTwoName);
        dealCards();
    }

    /**
     * Method deals 26 cards to each player. First it creates a new deck from the Deck class and then shuffles the deck.
     * It then deals the first 26 cars of the deck to player ones hand and the last 26 to player twos hand.
     */
    public void dealCards() {
        Deck deck = new Deck();
        deck.shuffle();
        for (int i = 0; i < 26; i++) {
            currentHandOne.add(deck.get(i));
        }
        for (int i = 26; i < 52; i++) {
            currentHandTwo.add(deck.get(i));
        }
    }

    /**
     * Method used in the case that the players cards from playRound are evaluated to be equal. Pulls four cards from
     * each player and compares them one by one to see who has the higher card. Whoever has the higher card wins and
     * receives all 4 cards from the opposing player. If all cards are equal goToWar is called recursively to run
     * another round of war. If at any time a player does not have enough cards to continue they lose.
     *
     * @param cardsInPlay - list of cards currently in play.
     */
    private void goToWar(ArrayList<Card> cardsInPlay) {
        // Check if players have enough cards for war.
        // If not enough cards other player wins.
        if (currentHandOne.size() < 4) {
            if (stackOne.isEmpty()) {
                playerTwoWin = true;
                return;
            }
            grabStack(currentHandOne, stackOne);
        }
        if (currentHandTwo.size() < 4) {
            if (stackTwo.isEmpty()) {
                playerOneWin = true;
                return;
            }
            grabStack(currentHandTwo, stackTwo);
        }

        // Put down 3 cards from each player.
        for (int i = 0; i < 3 && !currentHandOne.isEmpty() && !currentHandTwo.isEmpty(); i++) {
            cardsInPlay.add(currentHandOne.remove(0));
            cardsInPlay.add(currentHandTwo.remove(0));
        }

        // Check to see if the hands have cards. Play the war card. Top card.
        if (!currentHandOne.isEmpty() && !currentHandTwo.isEmpty()) {
            Card warCardOne = currentHandOne.remove(0);
            Card warCardTwo = currentHandTwo.remove(0);
            cardsInPlay.add(warCardOne);
            cardsInPlay.add(warCardTwo);

            System.out.println("War cards: " + warCardOne + " vs " + warCardTwo);

            // Check value of each card. Higher card wins. If cards are equal move to next card.
            if (warCardOne.getValue() > warCardTwo.getValue()) {
                stackOne.addAll(cardsInPlay);
                System.out.println(playerOne.getName() + " wins the war");
            } else if (warCardTwo.getValue() > warCardOne.getValue()) {
                stackTwo.addAll(cardsInPlay);
                System.out.println(playerTwo.getName() + " wins the war");
            } else {
                System.out.println("Another war!");
                goToWar(cardsInPlay); // Recursive call for double war.
            }
        }
    }

    /**
     * Method picks up the players stack of cards they have won in previous rounds from the other player.
     *
     * @param hand  - Current hand of the selected player
     * @param stack - Current stack of the selected player
     */
    private void grabStack(ArrayList<Card> hand, ArrayList<Card> stack) {
        // add all cards from players stack to their hand.
        hand.addAll(stack);
        // shuffle the hand.
        Collections.shuffle(hand);
        // clear the stack.
        stack.clear();
    }


    /**
     * This will play one round of "war". It looks at each player's top card and compares them. The card that is of a
     * higher value wins the round. In the case of a draw, the players go to war calling the "gotToWar" method. If a
     * player runs out of cards to play the other player wins.
     * @return - The result of the round.
     */
    public RoundResult playSingleRound() {
        if (playerOneWin || playerTwoWin) {
            return new RoundResult(
                    lastPlayerOneCard.toString(),
                    lastPlayerTwoCard.toString(),
                    lastResult,
                    true,
                    winner
            );
        }

        if(currentHandOne.isEmpty()) {
            if (stackOne.isEmpty()) {
                playerTwoWin = true;
                winner = playerTwo.getName();
                return new RoundResult(
                        // Simpler way to write if else statement. Ternary operator.
                        // Condition: lastPlayerOne card != null
                        // ? = if true do this (if)
                        // : = if false do this (else)
                        lastPlayerOneCard != null ? lastPlayerOneCard.toString() : "none",
                        lastPlayerTwoCard != null ? lastPlayerTwoCard.toString() : "none",
                        playerTwo.getName() + " wins!",
                        true,
                        winner
                );
            }
            grabStack(currentHandOne, stackOne);
        }

        if(currentHandTwo.isEmpty()) {
            if (stackTwo.isEmpty()) {
                playerOneWin = true;
                winner = playerOne.getName();
                return new RoundResult(
                        lastPlayerOneCard != null ? lastPlayerOneCard.toString() : "none",
                        lastPlayerTwoCard != null ? lastPlayerTwoCard.toString() : "none",
                        playerOne.getName() + " wins!",
                        true,
                        winner
                );
            }
            grabStack(currentHandTwo, stackTwo);
        }

        // play single round
        Card cardOne = currentHandOne.remove(0);
        Card cardTwo = currentHandTwo.remove(0);
        ArrayList<Card> cardsInPlay = new ArrayList<>();
        cardsInPlay.add(cardOne);
        cardsInPlay.add(cardTwo);

        String result;
        if (cardOne.getValue() > cardTwo.getValue()) {
            stackOne.addAll(cardsInPlay);
            result = playerOne.getName() + " wins the round";
        } else if (cardTwo.getValue() > cardOne.getValue()) {
            stackTwo.addAll(cardsInPlay);
            result = playerTwo.getName() + " wins the round";
        } else {
            result = "War!";
            goToWar(cardsInPlay);
            result = lastResult;
        }

        lastPlayerOneCard = cardOne;
        lastPlayerTwoCard = cardTwo;
        lastResult = result;

        // display the result of the round played
        return new RoundResult(
                cardOne.toString(),
                cardTwo.toString(),
                result,
                playerOneWin || playerTwoWin,
                winner
        );
    }

    /**
     * Nested class being used to display who the winner of a round is. This class puts together all the pertinent
     * details of a single round.
     */
    public static class RoundResult {
        public String playerOneCard;
        public String playerTwoCard;
        public String result;
        public String winner;
        public boolean gameOver;

        /**
         * Class constructor
         * @param playerOneCard - Player one's card
         * @param playerTwoCard - Player two's card
         * @param result - Result of the round
         * @param gameOver - Is game over True || False
         * @param winner - Who wins the round
         */
        public RoundResult(String playerOneCard, String playerTwoCard, String result, boolean gameOver, String winner) {
            this.playerOneCard = playerOneCard;
            this.playerTwoCard = playerTwoCard;
            this.result = result;
            this.gameOver = gameOver;
            this.winner = winner;
        }
    }
}
