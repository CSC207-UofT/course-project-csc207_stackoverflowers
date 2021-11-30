package UseCases;

import Entities.GamePrompts;
import Entities.Intern;
import Entities.InterviewIntern;
import Entities.Project;

import java.io.Serializable;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class GameGenerators implements Serializable {
    private final HRSystem currentHRSystem;
    private final PMSystem currentPMSystem;

    public GameGenerators(HRSystem currentHRSystem, PMSystem currentPMSystem){

        this.currentHRSystem = currentHRSystem;
        this.currentPMSystem = currentPMSystem;
    }

    /**
     * Add the list of interns to UseCases.HRSystem.
     * @param newInterns A list of interns constructed in the UseCases.GameMaker constructor
     */
    private void addInternToList(ArrayList<InterviewIntern> newInterns){
        this.currentHRSystem.updateInterviewInternList(newInterns);
    }

    /**
     * Randomly generates an ArrayList of interns/interviewees, and adds it to UseCases.HRSystem
     * @param numInterns the number of interns that will be generated.
     */
    public void generateInterns(int numInterns) throws FileNotFoundException {
        ArrayList<String> nameList = generateInfo("Resources/names.txt");
        ArrayList<String> skillList = generateInfo("Resources/skills.txt");
        Random random = new Random();
        ArrayList<InterviewIntern> internList = new ArrayList<>();
        for (int i = 1; i <= numInterns; i++) {
            String name = nameList.get(random.nextInt(nameList.size()));
            nameList.remove(name);
            HashMap<String, Double> skillMap = generateUniqueSkillMap(skillList);
            int age = random.nextInt(45) + 20;
            InterviewIntern interviewee = new InterviewIntern(name, age, skillMap);
            ResponseTreeMaker forThisIntern = new ResponseTreeMaker(interviewee);
            forThisIntern.assignResponseToIntern();
            internList.add(interviewee);
        }
        addInternToList(internList);
    }

    /**
     * Randomly generates an ArrayList of projects that player will be interacting with during the game,
     * and updates the current HR system to reflect the projects that will be used for this game.
     * @param proj the number of projects that will be generated.
     */
    public void generateProjects(int proj) throws FileNotFoundException {
        // take the project prompts from GamePrompts and outputs a list
        ArrayList<String> projList = new ArrayList<>();
        ArrayList<Project> projForGame = new ArrayList<>();
        Random random = new Random();
        projList.add(GamePrompts.PROJECT1_NAME);
        projList.add(GamePrompts.PROJECT2_NAME);
        projList.add(GamePrompts.PROJECT3_NAME);
        projList.add(GamePrompts.PROJECT4_NAME);
        projList.add(GamePrompts.PROJECT5_NAME);
        projList.add(GamePrompts.PROJECT6_NAME);
        projList.add(GamePrompts.PROJECT7_NAME);
        projList.add(GamePrompts.PROJECT8_NAME);
        for (int i = 1; i <= proj; i++) {
            String projName = projList.get(random.nextInt(projList.size()));
            projList.remove(projName);
            Project project = new Project(projName);
            projForGame.add(project);
        }
        currentPMSystem.updateProjectList(projForGame);
    }

    /**
     * Generates an ArrayList containing one randomly generated final project that player will be interacting with
     * during the game, and updates the current HR system to reflect the final project that will be used for this game.
     */
    public void generateFinalProject() throws FileNotFoundException {
        // take the final projects in gamePrompts and pick a final project for this game.
        ArrayList<String> finalProjects = new ArrayList<>();
        ArrayList<Project> finalProjForGame = new ArrayList<>();
        finalProjects.add(GamePrompts.FINAL_PROJECT1_NAME);
        finalProjects.add(GamePrompts.FINAL_PROJECT2_NAME);
        finalProjects.add(GamePrompts.FINAL_PROJECT3_NAME);
        Collections.shuffle(finalProjects);
        String finalProjName = finalProjects.get(0);
        finalProjects.remove(finalProjName);
        Project finalProject = new Project(finalProjName);
        finalProjForGame.add(finalProject);
        currentPMSystem.updateFinalProject(finalProjForGame);
    }

    /**
     * Helper method for generateInterns.
     * Converts a text file into an ArrayList, line by line.
     * @param fileName the name of the file that is going to be converted
     * @return an ArrayList of Entities.Intern names or Entities.Intern skills
     */
    private ArrayList<String> generateInfo(String fileName) throws FileNotFoundException {
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
     * @return a HashMap of Skills and the score (0.00 - 1.00) of the skill
     */

    private HashMap<String, Double> generateUniqueSkillMap(ArrayList<String> skillList){
        Random random = new Random();
        HashMap<String, Double> skillMap = new HashMap<>();
        double d = 100.00 * random.nextDouble();
        BigDecimal bd = new BigDecimal(d).setScale(2, RoundingMode.UP);
        while (skillMap.size() < 3) {
            String skill = skillList.get(random.nextInt(skillList.size()));
            if (!skillMap.containsKey(skill)) {
                skillMap.put(skill, bd.doubleValue());
            }
        }
        return skillMap;
    }
}
}
