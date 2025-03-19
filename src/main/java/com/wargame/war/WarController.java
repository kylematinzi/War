package com.wargame.war;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
// @Controller annotation: This annotation designates this class as the controller and handles incoming HTTP requests.
// It is detected when the main class is called through the @SpringBootApplication annotation. Another way to put it is
// it handles web requests and creates endpoints to control how the application interacts with users.
public class WarController {
    // Store instance of the game
    private Game game;

    public WarController() {
        // setting some default player names. Will allow players to choose name in the future.
        this.game = new Game("Player 1", "Player 2");
    }

    @RequestMapping("/")
    // @RequestMapping annotation: This annotation specifies the URL pattern to be used "/". Routes HTTP requests to the
    // controller.
    public String war() {
        return "war"; // must match war.html in templates. This is what's being called on
    }

    @GetMapping("/playRound")
    // @GetMapping annotation: This annotation will map to a specific URL. You can use this tag multiple times to render
    // different HTML pages. I could create /users to display a user page or /home to create a home page for the game
    // where users could enter their names before the first round.
    @ResponseBody
    // @ResponseBody annotation: Tells Spring to send a method's return value as raw data vs a rendered page. For example
    // returning "playRound" would try to look for "playRound.html" without @ResponseBody. Where with the annotation it
    // returns the result of "playRound" as plain text.
    public Game.RoundResult playRound(){
        return game.playSingleRound();
    }
}
