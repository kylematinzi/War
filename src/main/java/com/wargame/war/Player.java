package com.wargame.war;

/**
 * This class is used to create a player.
 */
public class Player {
    private String name;

    public Player() {
    }

    /**
     * Gets the player's name.
     * @return - player name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the players name.
     * @param name - player name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * String of the players name.
     * @return - player name.
     */
    public String toString() {
        return name;
    }
}
