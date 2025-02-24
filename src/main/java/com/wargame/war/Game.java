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

    /**
     * Constructor. This method asks for two player names, will initialize the players, and deal the deck between the
     * two calling the dealCards method. Player names may be what ever characters a person decides.
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
     * Method plays the game. As long as playerOneWin and PlayerTwoWin are false the game will continue. Checks to see
     * if player's current hands are empty. If yes, the player adds their stack to their hand. If the players stack is
     * empty along with their hand, they lose. If not empty the stack is added to the player's hand, and the stack is
     * cleared. playRound is called to start comparing the cards of each player. CheckWinner will be called when either
     * playerOneWin or playerTwoWin is true.
     */
    public void playGame() {
        while (!playerOneWin && !playerTwoWin) {
            // Check if hands are empty and need to pick up the stack.
            if (currentHandOne.isEmpty()) {
                if (stackOne.isEmpty()) {
                    playerTwoWin = true;
                    break;
                }
                // add the stack to player hand. Clear the stack.
                currentHandOne.addAll(stackOne);
                Collections.shuffle(currentHandOne);
                stackOne.clear();
            }
            if (currentHandTwo.isEmpty()) {
                if (stackTwo.isEmpty()) {
                    playerOneWin = true;
                    break;
                }
                // add the stack to player hand. Clear the stack.
                currentHandTwo.addAll(stackTwo);
                Collections.shuffle(currentHandTwo);
                stackTwo.clear();
            }
            playRound();
        }
        // See who the winner is.
        checkWinner();
    }

    /**
     * Method plays a round of war. Each round consists of comparing one of player one's cards vs one of player two's
     * cards. Whosoever card is of higher value wins and collects the other players card. If cards are equal the
     * gotToWar method will be called.
     */
    private void playRound() {
        // Get the first card from each player's hand.
        // Remove the card from the player's hand.
        Card cardOne = currentHandOne.remove(0);
        Card cardTwo = currentHandTwo.remove(0);
        // Add the card to a separate list to track which cards are in play.
        ArrayList<Card> cardsInPlay = new ArrayList<>();
        cardsInPlay.add(cardOne);
        cardsInPlay.add(cardTwo);

        System.out.println(playerOne.getName() + " plays " + cardOne + " vs " +
                playerTwo.getName() + " plays " + cardTwo);

        // Compare the cards
        if (cardOne.getValue() > cardTwo.getValue()) {
            stackOne.addAll(cardsInPlay);
            System.out.println(playerOne.getName() + " wins the round");
        } else if (cardTwo.getValue() > cardOne.getValue()) {
            stackTwo.addAll(cardsInPlay);
            System.out.println(playerTwo.getName() + " wins the round");
        } else {
            // If cards are equal go to war.
            System.out.println("War!");
            goToWar(cardsInPlay);
        }
    }

    /**
     * Method used in the case that the players cards from playRound are evaluated to be equal. Pulls four cards from
     * each player and compares them one by one to see who has the higher card. Whoever has the higher card wins and
     * receives all 4 cards from the opposing player. If all cards are equal goToWar is called recursively to run
     * another round of war. If at any time a player does not have enough cards to continue they lose.
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
     * @param hand - Current hand of the selected player
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
     * Method will display who the winner of the game is based on the playerOneWin / playerTwoWin variables.
     */
    private void checkWinner() {
        if (playerOneWin) {
            winner = "The winner is: " + playerOne.getName();
        } else if (playerTwoWin) {
            winner = "The winner is: " + playerTwo.getName();
        }
        System.out.println(winner);
    }
}
