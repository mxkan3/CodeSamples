package com.company;

import java.util.Scanner;

public class RockPaperScissors { // singleton class:     only one instance of the class, accessible via a accessor method; method is globally accessible.
    // NOTE: unsure if it works with idiot input. im not sure if .equals() covers for that or not

    // Fields

    private static RockPaperScissors gameInstance; // the only instance for this class is here. All other instances are set to this, since the object is made within the class.
    public String player1choice = "";
    public String player2choice = "";
    public int player1Wins = 0;
    public int player2Wins = 0;
    public int gameTies = 0;
    public boolean goAgain;


    // Constructors

    /**RockPaperScissors
     * RockPaperScissors object default constructor
     */
    private RockPaperScissors(){}


    // Methods

    /**getSingletonInstance
     * Returns an object of this class. This accessor is public and static because its the only way to get the object. The static nature also prevents other
     * objects to be made, routing the class's usage through here. The single access point also helps control the usage of this class, so there is no unknown data flow.
     * @return the only possible RockPaperScissors instance object
     */
    public static RockPaperScissors getSingletonInstance(){
        if(gameInstance == null) {
            gameInstance = new RockPaperScissors(); // if its null, make a new one
        }
        return gameInstance;
    }

    /**player1
     *  prompts the console for player one's input, and returns it if viable
     * @return player one's input
     */
    public String player1() {
        System.out.println("Player 1, your turn! Type rock, paper, or scissors.\n");
        Scanner reader = new Scanner(System.in); // makes a new scanner object to enable input reading
        String input = reader.nextLine().trim().toUpperCase(); // sets a variable to store the input that is now read in

        while (true){
            if (!input.trim().toUpperCase().equals("ROCK") && !input.trim().toUpperCase().equals("PAPER") && !input.trim().toUpperCase().equals("SCISSORS")){
                System.out.println("Type rock, paper, or scissors, you pleb.\n");
                input = reader.nextLine().trim().toUpperCase(); // reads input again if it wasn't viable
            }
            else {
                reader.close(); // closes scanner
                break;
            }
        }

        player1choice = input;
        return player1choice;
    }

    /**player2
     * prompts the console for player two's input, and returns it if viable
     * @return player two's input
     */
    public String player2() {
        System.out.println("Player 2, your turn! Type rock, paper, or scissors.\n");
        Scanner reader = new Scanner(System.in); // makes a new scanner object to enable input reading
        String input = reader.nextLine().trim().toUpperCase(); // sets a variable to store the input that is now read in

        while (true){
            if (!input.trim().toUpperCase().equals("ROCK") && !input.trim().toUpperCase().equals("PAPER") && !input.trim().toUpperCase().equals("SCISSORS")){
                System.out.println("Type rock, paper, or scissors, you pleb.\n");
                input = reader.nextLine().trim().toUpperCase(); // reads input again if it wasn't viable
            }
            else {
                reader.close(); // closes scanner
                break;
            }
        }

        player2choice = input;
        return player2choice;
    }

    /**gameOutcome
     * takes the choices from player one and two, and decides who won the game. the result of who won is returned.
     * @param player1 the choice made by player 1
     * @param player2 the choice made by player 2
     * @return the winner of the game
     */
    public String gameOutcome(String player1, String player2) {
        if (player1.equals(player2)) return "TIE";
        switch (player1) {
            case "ROCK":{
                if(player2.equals("SCISSORS")) {
                    return "playerOneWin";
                }
                else
                    return "playerTwoWin"; }
            case "PAPER":{
                if(player2.equals("ROCK")) {
                    return "playerOneWin";
                }
                else
                    return "playerTwoWin"; }
            case "SCISSORS": {
                if(player2.equals("PAPER")) {
                    return "playerOneWin";
                }
                else
                    return "playerTwoWin"; }
            default:
                return "ERROR";
        }
    }

    /**gameHistoryLog
     * logs the outcome of the game in the RockPaperScissors instance fields, and prints out the current game history.
     * @param gameOutcome is the outcome of the game: playerOneWin, playerTwoWin, or TIE.
     */
    public void gameHistoryLog(String gameOutcome) {
        System.out.println("Game history:\n");
        switch (gameOutcome){
            case "playerOneWin": player1Wins++; break;
            case "playerTwoWin": player2Wins++; break;
            case "TIE": gameTies++; break;
            default:
                System.out.println("ERROR"); break;
        }
        System.out.println("Player 1 wins: " +  player1Wins + "\n Player 2 wins: " + player2Wins + "\n Ties: " + gameTies + "\n");
    }

    /**goAgainChecker
     * prompts the console/user for if they want to play again, and stores the result in the respective RockPaperScissors instance fields
     */
    public void goAgainChecker() {
        System.out.println("Would you like to play another round? (Y/N)\n");
        Scanner reader = new Scanner(System.in); // makes a new scanner object to enable input reading
        String input = reader.nextLine().trim().toUpperCase(); // sets a variable to store the input that is now read in

        while (true) {
            if (!input.trim().toUpperCase().equals("Y") && !input.trim().toUpperCase().equals("N")) {
                System.out.println("Type Y or N, you pleb.\n");
                input = reader.nextLine(); // reads input again if it wasn't viable
            }
            else {reader.close(); break;}
        }
            if(input.trim().toUpperCase().equals("N")) {
                System.out.println("\nGoodbye!\n\n");
                goAgain = false;
            }
            goAgain = true;
    }

    /**play
     * runs all other methods, starting the game over again. Only runs if the user answered the prompt for another game as such
     * @param go_Again true if the user wished to play another round, false otherwise. If false, play will not execute
     */
    public void play(boolean go_Again) {
        if (go_Again) {
            gameHistoryLog(gameOutcome(player1(), player2()));
            goAgainChecker();
            play(goAgain);
        }
    }
}
