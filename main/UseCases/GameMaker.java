package UseCases;

import Entities.GamePrompts;

import java.io.*;
import java.util.*;

public class GameMaker implements Serializable {
    private final HRSystem currentHRSystem;
    private final PMSystem currentPMSystem;
    private String currentStatus;
    private int currentMonth;
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
        this.currentPMSystem = new PMSystem(this.currentHRSystem);
        universalCommands = new ArrayList<>(Arrays.asList("save", "quit", "load"));
    }

    public HRSystem getCurrentHRSystem() {
        return currentHRSystem;
    }

    public PMSystem getCurrentPMSystem(){
        return currentPMSystem;
    }

    public static ArrayList<String> getUniversalCommands(){return universalCommands;}

    public int getCurrentMonth(){return currentMonth;}

    /**
     * Return the first display prompt after the player enters their name.
     *
     *  @param playerInput the input the player enters (their name)
     */
    public String firstPrompt(String playerInput) throws Exception {
        this.currentHRSystem.updatePlayerName(playerInput);
        String re = "";
        re += GamePrompts.FIRST_PROMPT_BEFORE_NAME;
        re += playerInput;
        re += GamePrompts.FIRST_PROMPT_AFTER_NAME;
        re += this.currentHRSystem.makeInterviewInternsToString();
        re += GamePrompts.FIRST_PROMPT_AFTER_INTERNS;
        return re;
    }


    public String endPrompt() {
        return GamePrompts.END_PROMPT;
    }


    /**
     * Saves the current state of the game under a file that is the player's name.
     */
    private void saveGame(String name) throws IOException {
        HashMap<String, Integer> savedNamesToTimes = getSavedNamesToTimes();
        String fileName;
        if (savedNamesToTimes.containsKey(name)){
            fileName = name + "_" +savedNamesToTimes.get(name).toString() + ".hrSimulator";
        }
        else{
            fileName = name + "_0.hrSimulator";}
        FileOutputStream fileOut = new FileOutputStream("Save/" + fileName);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(this);
        out.close();
    }

    private HashMap<String, Integer> getSavedNamesToTimes() {
        File folder = new File("Save");
        List<String> savedFileNames = new ArrayList<>();
        File[] listOfSavedGames = folder.listFiles();
        assert listOfSavedGames != null;
        for (File file: listOfSavedGames){
            if (file.isFile()){
                savedFileNames.add(file.getName()); //Add all the file names to the list of savedFileNames
            }
        }
        HashMap<String, Integer> savedNamesToTimes = new HashMap<>();
        for (String fileName: savedFileNames ){
            String[] name = fileName.split("_", 2);
            String Name = name[0];
            if(savedNamesToTimes.containsKey(Name)){
                savedNamesToTimes.put(Name, savedNamesToTimes.get(Name) + 1); //Add the names and the
            }else{
                savedNamesToTimes.put(Name, 1);
            }
        }
        return savedNamesToTimes;
    }

    private void quitGame() throws IOException {
        saveGame(currentHRSystem.getPlayerName());
    }
    
    /**
     * Loads the previous saved state of the game (discuss later, still unsure)
     * @return returns the loaded GameMaker to GameManager.
     */
    public GameMaker load(String name) throws IOException, ClassNotFoundException {
        String fileName = name + ".hrSimulator";
        FileInputStream fileIn = new FileInputStream("Save/" + fileName);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        return (GameMaker) in.readObject();
    }
    
    public String save(int currentMonth, String currentStatus) throws IOException {
        setCurrentStatus(currentStatus);
        setCurrentMonth(currentMonth);
        saveGame(currentHRSystem.getPlayerName());
        return GamePrompts.GAME_SAVED_SUCCESSFUL + currentHRSystem.getPlayerName();
    }

    private void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }
    public String getCurrentStatus(){
        return currentStatus;
    }

    public String quit(int currentMonth, String currentStatus) throws IOException{
        setCurrentMonth(currentMonth);
        setCurrentStatus(currentStatus);
        quitGame();
        return GamePrompts.INFORM_QUIT_GAME + currentHRSystem.getPlayerName();
    }

    public void setCurrentMonth(int currentMonth) {
        this.currentMonth = currentMonth;
    }
    //throw exception if input not one of these two... but how would they get here in the first place XD

}