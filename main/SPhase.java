import ControllersPresenters.GameManager;
import Entities.GamePrompts;

import java.util.Scanner;
public class SPhase {
    /* This is the user interface (similar to the J-shell from week 2)
    //TODO:
    - should only have a main method for beginning the game
    - waits for input and passes it off to the ControllersPresenters.GameManager (controller),
    - print the output received from the ControllersPresenters.GameManager
    all of this will be in the main method !!

     */
    public static void main(String[] args) {
        GameManager currentGame = null;
        try {
            currentGame = new GameManager();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        GamePrompts prompts = new GamePrompts();

        Scanner in = new Scanner(System.in);
        System.out.println(prompts.ASK_FOR_NAME);
        while (GameManager.isRunning()) {
            String playerInput = in.nextLine();
            try {
                assert currentGame != null;
                String output = currentGame.getOutput(playerInput);
                System.out.println(output);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
