package com.wargame.war;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class to run the War application. Through this application I am trying to become more familiar with the Spring Boot
 * framework. I'm new to using spring boot annotations and will be using inline comments with each tag to understand what it is I'm doing
 * and how these work. Dependencies can be found in the "pom.xml" file.
 * Areas for improvement:
 * 1. Display the war happening. As of now "war" is just run through the back end and the round winner is shown with both cards still being the same value.
 * 2. The initial images are both aces. Should update with the back of a playing card.
 * 3. Add user input to allow the players to choose their names.
 * 4. Add "Play again" button at the completion of each game.
 * 5. Keep track of each players amount of wins game vs game.
 * 6. Update the UI with better look.
 */
@SpringBootApplication
// @SpringBootApplication annotation: Lets Spring Boot know this is the main class for this application. It tells Spring Boot
// to configure the application and scan for components.
public class WarApplication {

	public static void main(String[] args) {
		SpringApplication.run(WarApplication.class, args);
	}



}
