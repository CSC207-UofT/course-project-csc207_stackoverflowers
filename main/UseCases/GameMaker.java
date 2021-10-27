package UseCases;

import Entities.GamePrompts;
import Entities.HRSystem;
import Entities.Intern;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GameMaker {
    private final HRSystem sys;
    private final GamePrompts prompts;

    /*
    - random intern generator - interns in HR
    - method for tree is also implement
    - in charge of anything starting the game (before the user can do anything)
     */


    /**
     * Construct a game maker.
     */
    public GameMaker() {
        this.sys = new HRSystem();
        this.prompts = new GamePrompts();
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
    /**
     * Add the list of interns to Entities.HRSystem.
     *
     * @param newInterns A list of interns constructed in the UseCases.GameMaker constructor
     */
    public void addInternToList(ArrayList<Intern> newInterns){
        this.sys.updateInternList(newInterns);
    }

    /**
     * Return a list of interns.
     */
    public List<Intern> returnNewInterns() {
        return this.sys.getInternList();
    }

    /**
     * Return the first display prompt after the player enters their name.
     *
     *  @param playerInput the input the player enters (their name)
     */
    public String firstPrompt(String playerInput){
        this.sys.updatePlayerName(playerInput);
        String re = "";
        re += this.prompts.FIRST_PROMPT_BEFORE_NAME;
        re += playerInput;
        re += this.prompts.FIRST_PROMPT_AFTER_NAME;
        re += this.sys.makeInternsToPrompt();
        return re;
    }

    /**
     * A helper method for creating an intern.
     */
    private Intern helperMakeInternOneSkill(String name, int age, String skillName, int skillLevel){
        HashMap<String, Integer> skills = new HashMap<>();
        skills.put(skillName, skillLevel);
        return new Intern(name, age, skills);
    }

    //TODO: method generateInterns() (Generates and stored the interns in Entities.HRSystem)

    /**
     * Generates an ArrayList of new random interns/interviewees.
     * @param numInterns the number of interns that will be generated.
     * @return an ArrayList of randomly generated Interns.
     */
    public ArrayList<Intern> generateInterns(int numInterns) throws FileNotFoundException {
        ArrayList<String> nameList = generateInternsHelper("UseCases/names.txt");
        ArrayList<String> skillList = generateInternsHelper("UseCases/skills.txt");
        Random random = new Random();
        ArrayList<Intern> internList = new ArrayList<>();
        for (int i = 1; i <= numInterns; i++) {
            String name = nameList.get(random.nextInt(nameList.size()));
            nameList.remove(name);
            HashMap<String, Integer> skillMap = generateUniqueSkillMap(skillList);
            int age = random.nextInt(45) + 20;
            Intern interviewee = new Intern(name, age, skillMap);
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
    private ArrayList<String> generateInternsHelper(String fileName) throws FileNotFoundException {
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

    // TODO: method generateProjects() (Generates and stores the projects in Entities.HRSystem) (change return type)
    public void generateProjects() {

    }

    /**
     * This method will assign one of the reponses from our response trees to an intern
     */
    public void assignResponseToIntern(){

    }
}
