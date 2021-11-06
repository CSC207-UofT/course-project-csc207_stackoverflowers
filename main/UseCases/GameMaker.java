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

    /**
     * Add the list of interns to UseCases.HRSystem.
     *
     * @param newInterns A list of interns constructed in the UseCases.GameMaker constructor
     */
    public void addInternToList(ArrayList<Intern> newInterns){
        this.currentHRSystem.updateInternList(newInterns);
    }

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

    /**
     * Generates an ArrayList of new random interns/interviewees.
     * @param numInterns the number of interns that will be generated.
     * @return an ArrayList of randomly generated Interns.
     */
    public void generateInterns(int numInterns) throws FileNotFoundException {
        ArrayList<String> nameList = generateInternsInfo("UseCases/names.txt");
        ArrayList<String> skillList = generateInternsInfo("UseCases/skills.txt");
        Random random = new Random();
        ArrayList<Intern> internList = new ArrayList<>();
        for (int i = 1; i <= numInterns; i++) {
            String name = nameList.get(random.nextInt(nameList.size()));
            nameList.remove(name);
            HashMap<String, Integer> skillMap = generateUniqueSkillMap(skillList);
            int age = random.nextInt(45) + 20;
            Intern interviewee = new InterviewIntern(name, age, skillMap);
            internList.add(interviewee);
        }
        addInternToList(internList);
    }

    /**
     * Helper function for generateInterns.
     * Converts a text file into an ArrayList, line by line.
     * @param fileName the name of the file that is going to be converted
     * @return an ArrayList of Entities.Intern names or Entities.Intern skills
     */
    private ArrayList<String> generateInternsInfo(String fileName) throws FileNotFoundException {
        Scanner s = new Scanner(new File(fileName));
        ArrayList<String> infoList = new ArrayList<>();
        while (s.hasNext()){
            infoList.add(s.next());
        }
        s.close();
        return infoList;
    }

    /**
     * Helper method for generateInterns.
     * Creates a HashMap with three random skills and their random respective percentages
     * @param skillList an ArrayList of skills.
     * @return a HashMap of Skills and the percentage of the skill
     */
    private HashMap<String, Integer> generateUniqueSkillMap(ArrayList<String> skillList) throws FileNotFoundException{
        Random random = new Random();
        HashMap<String, Integer> skillMap = new HashMap<>();
        while (skillMap.size() < 3) {
            String skill = skillList.get(random.nextInt(skillList.size()));
            if (!skillMap.containsKey(skill)) {
                skillMap.put(skill, random.nextInt(101));
            }
        }
        return skillMap;
    }

    // TODO: method generateProjects() (Generates and stores the projects in UseCases.HRSystem) (change return type)
    public void generateProjects() {
        // take the project prompts from GamePrompts and outputs a list
        ArrayList<String> projects = new ArrayList<>();
        ArrayList<String> projForGame = new ArrayList<>();
        projects.add(prompts.PROJECT1_PROMPT);
        projects.add(prompts.PROJECT2_PROMPT);
        projects.add(prompts.PROJECT3_PROMPT);
        projects.add(prompts.PROJECT4_PROMPT);
        projects.add(prompts.PROJECT5_PROMPT);
        projects.add(prompts.PROJECT6_PROMPT);
        projects.add(prompts.PROJECT7_PROMPT);
        projects.add(prompts.PROJECT8_PROMPT);
        Collections.shuffle(projects);
        for (int i = 0; i < 5; i++) {
            projForGame.add(projects.get(i));
        }
        currentHRSystem.updateProjectList(projForGame);

    }

    public void generateFinalProject(){
        // take the final projects in gamePrompts and pick a final project for this game.
        ArrayList<String> finalProjects = new ArrayList<>();
        ArrayList<String> finalProjForGame = new ArrayList<>();
        finalProjects.add(prompts.FINAL_PROJECT1_PROMPT);
        finalProjects.add(prompts.FINAL_PROJECT2_PROMPT);
        finalProjects.add(prompts.FINAL_PROJECT3_PROMPT);
        Collections.shuffle(finalProjects);
        finalProjForGame.add(finalProjects.get(0));
        currentHRSystem.updateFinalProject(finalProjForGame);

    }

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