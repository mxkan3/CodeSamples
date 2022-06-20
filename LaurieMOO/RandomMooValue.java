package sample;

public class RandomMooValue {

    // Constructors

    /**RandomMooValue
     * RandomMooValue object default constructor
     */
    public RandomMooValue(){// Creates a new RandomMooValue object containing a secret value to be guessed

    }



    // Variables

    public String secretvalue; // holds the value to be guessed
    public boolean[] secretarray = {false, false, false, false}; // holds a truth value for each number in the guessed value that incremented BigMoo




    // Methods

    /**getBigMooCount
     *The number of digits in the user's guess that exactly (i.e., same position) matches digits in the secret value.
     * @param guess - The number that the user guessed.
     * @return a number 0-4 representing how many digits the user guessed correctly by position.
     */
    public int getBigMooCount(int guess) {
        int bigMoo = 0;
        String temp = String.valueOf(guess);
        for (int i = 0; i < temp.length(); i++) {
            if (temp.charAt(i) == secretvalue.charAt(i)) {
                bigMoo++;
                secretarray[i] = true;
            }
        }
        return bigMoo;
    }

    /**resetSecretArray
     *resets the secretarray to all false
     */
    public void resetSecretArray(){
        secretarray = new boolean[] {false, false, false, false};
    }

    /**getLittleMooCount
     *The number of digits in the user's guess that exactly (i.e., same position) matches digits in the secret value.
     * @param guess - The number that the user guessed.
     * @return a number 0-4 representing how many of the guessed digits match, but are in different
     * positions. Note that a guessed number cannot have any one digit generate both a "MOO!"
     * and a "moo." as a result.
     */
    public int getLittleMooCount(int guess){
        int littleMoo = 0;

        boolean[] actualUsed = new boolean[] {secretarray[0], secretarray[1], secretarray[2], secretarray[3]};
        boolean[] guessUsed = new boolean[] {secretarray[0], secretarray[1], secretarray[2], secretarray[3]};
        String temp = String.valueOf(guess);

        for (int i = 0; i < temp.length(); i++) {
            for (int j = 0; j < secretvalue.length(); j++) {
                if (i != j && !guessUsed[i] && !actualUsed[j] && temp.charAt(i) == secretvalue.charAt(j)) {   // if its the same value for one of the values...
                    guessUsed[i] = true;
                    actualUsed[j] = true;
                    littleMoo++;
                }
            }
        }
        return littleMoo;
    }

    /**getSecretValue
     *Access the secret value that the user is trying to guess, primarily to show the user after running out of guesses.
     * @return the secret value that the user is/was attempting to guess.
     */
    public int getSecretValue(){
        return Integer.parseInt(secretvalue);
    }

    /**isCorrectGuess
     *Determines if the user correctly guessed the secret value.
     * @param guess - The number that the user guessed.
     * @return true if the guess is correct, false otherwise.
     */
    public boolean isCorrectGuess(int guess){
        String temp = Integer.toString(guess);
        for (int i = 0; i < 4; i++) {
            if (!(temp.charAt(i) == secretvalue.charAt(i))){
                return false;
            }
        }
        return true;
    }

    /**setSecretValue
     * Generates a new secret value to be guessed in a game of LaurieMOO
     * @return true
     */
    public boolean setSecretValue(){
        int digit1, digit2, digit3, digit4;
        digit1 = (int) (Math.random() * 10);
        digit2 = (int) (Math.random() * 10);
        digit3 = (int) (Math.random() * 10);
        digit4 = (int) (Math.random() * 10);
        secretvalue = String.valueOf(digit1) + digit2 + digit3 + digit4;
        return true;
    }

    /**setSecretValue
     *Generates a new secret value to be guessed in a game of LaurieMOO.
     * @param n - The number that will be set as the secret value, if it is within the inclusive
     * range of 0000-9999.
     * @return true if the secret value was reset; false if the passed value was outside of the
     * allowed range of values.
     */
    public boolean setSecretValue(int n){
        Integer i = n;
        String temp = i + "";
        if (temp.length() == 4) {
            secretvalue = temp;
            return true;
        }
        return false;
    }

    /**isValid
     * validates user input
     * @param n the user;s input
     * @return true if valid, false otherwise
     */
    public boolean isValid(String n){
        return n.length() == 4;
    }

}
