// This is the LaurieMoo! game created in Programming 2 of my freshman year of college. 
// This project serves no external purpose, as it is simply a game.
// The documentation for the methods used are found in the RandomMooValue file, above each method.
// This was coded in IntelliJ IDEA.

package sample;
                                                            // im using java 11.0.5, and im using libraries from a javafx 11.0.2 SDK
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicInteger;

public class Main extends Application {

    AtomicInteger turns = new AtomicInteger();

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));   // enables use of 'root' for GUI
        primaryStage.setTitle("MP1");                                                 // Sets the title of the GUI page
        primaryStage.setScene(new Scene(root, 500, 450));               // Sets the dimensions for the GUI page
        primaryStage.show();                                                          // Now that the GUI page has been made, this line displays it to the user


        // Button instantiations --
        Button randomButton = (Button) root.lookup("#randomButton"); // the root.lookup part tells the program to look for the name given
        Button showValueButton = (Button) root.lookup("#showValueButton");

        // Textfield instantiations --
        TextField secretValueTextField = (TextField) root.lookup("#secretValueTextField");
        TextField guessTextField = (TextField) root.lookup("#guessTextField");

        // Label instantiations --
        Label correctGuessLabel = (Label) root.lookup("#correctGuessLabel");
        Label bigMooLabel = (Label) root.lookup("#bigMooLabel");
        Label littleMooLabel = (Label) root.lookup("#littleMooLabel");
        Label laurieMOOLabel = (Label) root.lookup(("#laurieMOOLabel"));

        // Other instantiations --
        RandomMooValue mv = new RandomMooValue(); // created an object for the code to use, enabling access to methods within the object's class



        
        // Button action events
        randomButton.setOnAction(event -> {
            mv.setSecretValue(); // this code explains what this button does, which is to calculate and set the secret value
            secretValueTextField.setText("");
            laurieMOOLabel.setText("");
            correctGuessLabel.setText("");
            guessTextField.setText("");
            bigMooLabel.setText("");
            littleMooLabel.setText("");
        });

        showValueButton.setOnAction(actionEvent -> { // this button reveals the secretvalue, if the user chooses to do so
            secretValueTextField.setText(mv.secretvalue);
        });


        // Textfield action events
        secretValueTextField.setOnAction(event -> { // this text field is coded so the user can input their own secret value
            if (!mv.setSecretValue(Integer.parseInt(secretValueTextField.getText()))){
                laurieMOOLabel.setText("try again");
            }
            else { // the value is only set if it a viable value to be guessed. Numbers only!
                mv.setSecretValue(Integer.parseInt(secretValueTextField.getText()));
                }
            secretValueTextField.setText("");
            laurieMOOLabel.setText("");
            correctGuessLabel.setText("");
            guessTextField.setText("");
            bigMooLabel.setText("");
            littleMooLabel.setText("");
        });

        guessTextField.setOnAction(event ->
        {
            try {
                if (mv.isValid(guessTextField.getText())){ // this works if the value given as a guess is viable
                    if (mv.isCorrectGuess(Integer.parseInt(guessTextField.getText()))) { // checks for whether or not the guess input was right
                        correctGuessLabel.setText("YES!");
                        laurieMOOLabel.setText("LaurieMOO!!!!!");
                        turns.set(0); // resets the tries the user has, effectively restarting the program for another game
                        secretValueTextField.setText((mv.getSecretValue()) + ""); // shows the value at the end of a game
                    }
                    else {
                        correctGuessLabel.setText("no....");
                        turns.getAndIncrement();
                        if (turns.get() > 9){
                            laurieMOOLabel.setText("Boo hoo -- no LaurieMOO."); // the user loses if they cannot guess correctly within the number of tries available
                            turns.set(0);
                            secretValueTextField.setText((mv.getSecretValue()) + ""); // shows the value at the end of a game
                        }
                    }
                    mv.resetSecretArray();
                    bigMooLabel.setText((mv.getBigMooCount(Integer.parseInt(guessTextField.getText()))) + "");                 // shows the user the number of big moos for the most recent guess
                    littleMooLabel.setText((mv.getLittleMooCount(Integer.parseInt(guessTextField.getText()))) + "");           // shows the user the number of little moos for the most recent guess
                }
                else {
                    laurieMOOLabel.setText("four digits needed. try again");
                    correctGuessLabel.setText("");
                    guessTextField.setText("");
                    bigMooLabel.setText("");
                    littleMooLabel.setText("");
                }
            }
            catch (Exception e) { // this catches any weird inputs that were not viable at all. Serves as a fail-safe so the program wont run errors
                laurieMOOLabel.setText("INTEGERS ONLY. try again");
                correctGuessLabel.setText("");
                guessTextField.setText("");
            }
        });
    }

    public static void main(String[] args) {
        launch(args);

    }
}

