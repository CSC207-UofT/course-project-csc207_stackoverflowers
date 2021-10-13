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
        GameManager currentGame = new GameManager();
        Scanner in = new Scanner(System.in);
        System.out.println(""); // enter parameter from game prompt
        String playerInput = in.nextLine();
        // TODO: use the method from GameManager described above where you output the BIG prompt to the player


    }
}
