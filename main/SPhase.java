import java.util.Scanner;
public class SPhase {
    /* This is the user interface (similar to the Jshell from week 2)
    //TODO:
    - should only have a main method for beginning the game
    - waits for input and passes it off to the GameManager (controller),
    - print the output received from the GameManager
    all of this will be in the main method !!

     */
    public static void main(String[] args) {
        GamePrompts prompts = new GamePrompts();
        GameManager currentGame = new GameManager();
        //should use a while loop here instead, and let GameManager be in charge of ending the game
        Scanner in = new Scanner(System.in);
        System.out.println(prompts.ASK_FOR_NAME);
        String playerInput = in.nextLine();
        String output = currentGame.firstPrompt(playerInput);
        System.out.println(output);
    }
}
