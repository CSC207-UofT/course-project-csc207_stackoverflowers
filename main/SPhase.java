import ControllersPresenters.GameManager;
import Entities.Exceptions;
import Entities.GamePrompts;

import java.io.IOException;
import java.util.Scanner;
public class SPhase {
    /* This is the user interface (similar to the J-shell from week 2)
    - should only have a main method for beginning the game
    - waits for input and passes it off to the ControllersPresenters.GameManager (controller),
    - print the output received from the ControllersPresenters.GameManager
    all of this will be in the main method !!
     */
    public static void main(String[] args) throws Exception {
        GameManager currentGame = null;
        try {
           currentGame = new GameManager();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to HRSimulator! To start the game, " +
                "please enter your name.\n" +
                "To load an already existing game under your name, please type 'load <name>'.");
        while (GameManager.isRunning()) {
            String playerInput = in.nextLine();
            try {
                assert currentGame != null;
                String output = currentGame.getOutput(playerInput);
                System.out.println(output);
            }
            catch(IOException e){
                if (e.getMessage().contains(Exceptions.INVALID_NAME_CONTENT)||
                        e.getMessage().contains(Exceptions.INVALID_NAME_EMPTY) ||
                        e.getMessage().contains(Exceptions.INVALID_NAME_SPACE)){
                    System.out.println("Welcome to HRSimulator! To start the game, " +
                            "please enter your name.\n" +
                            "To load an already existing game under your name, please type 'load <name>'.");
                }
                System.out.println(e.toString());
            }
            catch (Exception e){
                System.out.println(e.toString());
            }
        }
    }
}
