## Java Implementation of the card game "War"

This program is built and designed using Java with Spring and JavaScript to create an interactive card game application. 
This application allows users to play multiple rounds of the classic game of "War" against an automated opponent, managing 
card decks and continuing gameplay until a winner is determined. From the main interface, a user launches directly into the game,
with rounds progressing automatically until the conclusion. During gameplay, the application ensures fair card distribution 
and enforces standard 'War' rules seamlessly.

## What I Learned

- How to implement the Spring framework.
- How to use Spring annotations "@"
- How to use an enhanced switch
- How to use a Ternary operator

## Example use cases
#Enhanced switch
 return switch (value) {
            // enhanced switch vs traditional switch. Takes numeric value and moves to string name
            case 14 -> "ace";
            case 13 -> "king";
            case 12 -> "queen";
            case 11 -> "jack";
            default -> String.valueOf(value); // default will return cards that already have their numbers 2-10
        };
Breakdown:
- Replaces : and break with ->
- Eliminates need for repetative break statements

#Ternary Operator
lastPlayerOneCard != null ? lastPlayerOneCard.toString() : "none"
Breakdown: 
- Condition = lastPlayerOneCard != null
- ? = if true do this (if)
- : = if false do this (else)

