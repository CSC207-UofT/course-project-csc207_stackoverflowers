package UseCases;

import Entities.GamePrompts;
import Entities.Intern;
import Entities.InterviewIntern;

import java.io.*;
import java.util.*;

public class GameMaker implements Serializable {
    private final HRSystem currentHRSystem;
    private final GamePrompts prompts;
    private static ArrayList<String> universalCommands = null;
    /*
    - random intern generator - interns in HR
    - method for tree is also implement
    - in charge of anything starting the game (before the user can do anything)
     */


    /**
     * Construct a game maker.
     */
    public GameMaker() {
        this.currentHRSystem = new HRSystem();
        this.prompts = new GamePrompts();
        universalCommands = new ArrayList<String>(Arrays.asList("save", "quit", "load"));
    }

    public HRSystem getCurrentHRSystem() {
        return currentHRSystem;
    }
    public static ArrayList<String> getUniversalCommands(){return universalCommands;}

    //TODO: this method below is never used. Can I delete it?
    /**
     * Return a list of interns.
     */
    public List<Intern> returnNewInterns() {
        return this.currentHRSystem.getInternList();
    }

    /**
     * Return the first display prompt after the player enters their name.
     *
     *  @param playerInput the input the player enters (their name)
     */
    public String firstPrompt(String playerInput){
        this.currentHRSystem.updatePlayerName(playerInput);
        String re = "";
        re += GamePrompts.FIRST_PROMPT_BEFORE_NAME;
        re += playerInput;
        re += GamePrompts.FIRST_PROMPT_AFTER_NAME;
        re += this.currentHRSystem.makeInternsToString();
        re += GamePrompts.ASK_FOR_INTERVIEWEE_NAME;
        return re;
    }

    public String endPrompt() {
        return GamePrompts.END_PROMPT;
    }

    /**
     * A helper method for creating an intern. (ONLY FOR DEMO)

     private Intern helperMakeInternOneSkill(String name, int age, String skillName, int skillLevel){
        HashMap<String, Integer> skills = new HashMap<>();
        skills.put(skillName, skillLevel);
        return new InterviewIntern(name, age, skills);
        }
     */

    // TODO: method generateInternResponses() (Generates a tree of the intern's possible responses)
    // TODO: change return type; may also change this into a separate class later
    /**
     * This method will generate a tree of the intern's possible responses.
     * Their responses will be based on the intern's attributes (ex. skills, age)
     */
    public void generateInternResponses() {

    }
    // TODO: method assignResponseToIntern() (Assigns response trees for each intern)
    /**
     * This method will assign one of the responses from our response trees to an intern
     */
    public void assignResponseToIntern(){

    }

    /**
     * Saves the current state of the game under a file that is the player's name.
     */
    private void saveGame(String name) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(name);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(this);
        out.close();
    }
    
    private void quitGame() throws IOException {
        saveGame(currentHRSystem.getPlayerName());
    }
    
    /**
     * Loads the previous saved state of the game (discuss later, still unsure)
     * @param playerName
     * @return
     */
    public GameMaker load(String playerName) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(playerName);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        GameMaker loadGameMaker = (GameMaker) in.readObject();
        return null;
    }
    
    public String save() throws IOException {
        saveGame(currentHRSystem.getPlayerName());
        return GamePrompts.GAME_SAVED_SUCCESSFUL + currentHRSystem.getPlayerName();
    }
    
    public String quit() throws IOException{
        quitGame();
        return GamePrompts.INFORM_QUIT_GAME + currentHRSystem.getPlayerName();
    }
        //throw exception if input not one of these two... but how would they get here in the first place XD

}