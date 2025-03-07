package com.wargame.war;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WarController {
    // Store instance of the game
    private Game game;

    public WarController() {
        // setting some default player names. Will allow players to choose name in the future.
        this.game = new Game("Player 1", "Player 2");
    }

    @RequestMapping("/")
    public String war() {
        return "war"; // must match war.html in templates. This is what's being called on
    }

    @GetMapping("/playRound")
    @ResponseBody
    public Game.RoundResult playRound(){
        return game.playSingleRound();
    }
}
