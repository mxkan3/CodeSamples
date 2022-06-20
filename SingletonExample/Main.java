package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        RockPaperScissors gameInstance = RockPaperScissors.getSingletonInstance();
        System.out.println("Welcome to rock-paper-scissors. Would you like to play a round? (Y/N)\n");
        Scanner reader = new Scanner(System.in); // makes a new scanner object to enable input reading
        String input = reader.nextLine().trim().toUpperCase(); // sets a variable to store the input that is now read in

        while (true) {
            if (!input.trim().toUpperCase().equals("Y") && !input.trim().toUpperCase().equals("N")) {
                System.out.println("Type Y or N, you pleb.");
                input = reader.nextLine().trim().toUpperCase(); // reads input again if invalid

            }
            else {
                reader.close();
                break;
            }
        }
        if(input.trim().toUpperCase().equals("N")) {
                System.out.println("\nGoodbye!\n\n");
                return;
        }

        //TODO: fix code. figure out why nextLine() isn't working in player 1. Is java throwing a fit because I'm using more than one scanner?
        gameInstance.player1(); // player 1's turn. asks for their choice, and stores it
        gameInstance.player2(); // same as player 1, but for player 2 instead
        gameInstance.gameHistoryLog(gameInstance.gameOutcome(gameInstance.player1choice, gameInstance.player2choice)); // figures out who won, shows the outcome, and stores it in the game's history
        gameInstance.goAgainChecker(); // prompts for another game
        gameInstance.play(gameInstance.goAgain); // runs everything all over again if another game is desired
    }
}
