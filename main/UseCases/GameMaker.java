package UseCases;

import Entities.GamePrompts;
import Entities.Intern;
import Entities.InterviewIntern;

import java.lang.reflect.Array;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class GameMaker {
    private final HRSystem currentHRSystem;
    private final GamePrompts prompts;
    private final ArrayList<String> commands = new ArrayList<String>();

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
        this.commands.add("quit");
        this.commands.add("save");
        //TODO: also stores the three levels of the game

        /*
        Entities.Intern Farzana = helperMakeInternOneSkill("Farzana Rahman", 20, "Teamwork", 98 );
        Entities.Intern Maggie = helperMakeInternOneSkill("Maggie Huang", 20, "Leadership", 82);
        Entities.Intern Mary = helperMakeInternOneSkill("Mary Yijia Li", 19, "Efficiency", 99 );
        ArrayList<Entities.Intern> newInterns = new ArrayList<>();
        newInterns.add(Farzana);
        newInterns.add(Maggie);
        newInterns.add(Mary);
        this.addInternToList(newInterns);

         */
    }

    public HRSystem getCurrentHRSystem() {
        return currentHRSystem;
    }

    public ArrayList<String> getCommands() {
        return commands;
    }

    /**
     * Add the list of interns to UseCases.HRSystem.
     *
     * @param newInterns A list of interns constructed in the UseCases.GameMaker constructor
     */
    public void addInternToList(ArrayList<Intern> newInterns){
        this.currentHRSystem.updateInternList(newInterns);
    }

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
        re += this.prompts.FIRST_PROMPT_BEFORE_NAME;
        re += playerInput;
        re += this.prompts.FIRST_PROMPT_AFTER_NAME;
        re += this.currentHRSystem.makeInternsToPrompt();
        re += this.prompts.ASK_FOR_INTERVIEWEE_NAME;
        return re;
    }


    //TODO: method EndPrompt,
    // which is very similar to firstPrompt()
    // can access the player's name through HRSystem instead of needing an input
    public String endPrompt() {
        return "This endPrompt is not finished implementing yet";
    }

    /**
     * A helper method for creating an intern. (ONLY FOR DEMO)

     private Intern helperMakeInternOneSkill(String name, int age, String skillName, int skillLevel){
        HashMap<String, Integer> skills = new HashMap<>();
        skills.put(skillName, skillLevel);
        return new InterviewIntern(name, age, skills);
        }
     */

    //TODO: method generateInterns() (Generates and stored the interns in UseCases.HRSystem)
    /**
     * Generates an ArrayList of new random interns/interviewees.
     * @param numInterns the number of interns that will be generated.
     * @return an ArrayList of randomly generated Interns.
     */
    public ArrayList<Intern> generateInterns(int numInterns) throws FileNotFoundException {
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
        return internList;
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
    public ArrayList<String> generateProjects() {
        // take the project prompts from GamePrompts and outputs a list
        ArrayList<String> projects = new ArrayList<>();
        ArrayList<String> projForGame = new ArrayList<>();
        projects.add(prompts.PROJECT_PROMPT1);
        projects.add(prompts.PROJECT_PROMPT2);
        projects.add(prompts.PROJECT_PROMPT3);
        projects.add(prompts.PROJECT_PROMPT4);
        projects.add(prompts.PROJECT_PROMPT5);
        projects.add(prompts.PROJECT_PROMPT6);
        projects.add(prompts.PROJECT_PROMPT7);
        projects.add(prompts.PROJECT_PROMPT8);
        Collections.shuffle(projects);
        for (int i = 0; i < 5; i++) {
            projForGame.add(projects.get(i));
        }
        return projForGame;

    }

    public ArrayList<String> generateFinalProject(){
        // take the final projects in gameprompts and pick a final project for this game.
        ArrayList<String> finalProjects = new ArrayList<>();
        ArrayList<String> finalProjForGame = new ArrayList<>();
        finalProjects.add(prompts.FINAL_PROJECT1);
        finalProjects.add(prompts.FINAL_PROJECT2);
        finalProjects.add(prompts.FINAL_PROJECT3);
        Collections.shuffle(finalProjects);
        finalProjForGame.add(finalProjects.get(0));
        return finalProjForGame;

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

    // TODO: method saveGame()
    /**
     * Saves the current state of the game (discuss later, still unsure)
     */
    public void saveGame() {}

    //TODO: method quitGame()
    public void quitGame(){}

    // TODO: method loadGame()
    /**
     * Loads the previous saved state of the game (discuss later, still unsure)
     */
    public void loadGame() {
    }

    public String universalCommand(String playerInput) {
        if (playerInput == "save"){
            saveGame();
            return "Game's save function not implemented yet";
        }
        else if (playerInput == "quit"){
            quitGame();
            return "Game's quit function not implemented yet";
        }
        //throw exception if input not one of these two... but how would they get here in the first place XD
    }
}