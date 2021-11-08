package UseCases;

import Entities.GamePrompts;
import Entities.Intern;
import Entities.InterviewIntern;
import Entities.Project;

import java.io.Serializable;
import java.io.*;
import java.util.*;

public class GameGenerators implements Serializable {
    private final GamePrompts prompts;
    private final HRSystem currentHRSystem;


    public GameGenerators(){
        this.prompts = new GamePrompts();
        this.currentHRSystem = new HRSystem();
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
     * Generates an ArrayList of new random interns/interviewees.
     * @param numInterns the number of interns that will be generated.
     * @return an ArrayList of randomly generated Interns.
     */
    public void generateInterns(int numInterns) throws FileNotFoundException {
        ArrayList<String> nameList = generateInfo("UseCases/names.txt");
        ArrayList<String> skillList = generateInfo("UseCases/skills.txt");
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
     * Generates a random list of projects that player will be interacting with during the game.
     * Creates an ArrayList of Projects.
     * updates current HR system to reflect the projects that will be used for this game.
     * @param proj the number of projects that will be generated.
     * @return an ArrayList of projects.
     */
    public void generateProjects(int proj) throws FileNotFoundException {
        // take the project prompts from GamePrompts and outputs a list
        ArrayList<String> projList = new ArrayList<>();
        ArrayList<Project> projForGame = new ArrayList<Project>();
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
        currentHRSystem.updateProjectList(projForGame);
    }
    /**
     * Generates a random final project that player will be interacting with during the game.
     * updates current HR system to reflect the final project that will be used for this game.
     * @return an ArrayList containing one final project.
     */
    public void generateFinalProject() throws FileNotFoundException {
        // take the final projects in gamePrompts and pick a final project for this game.
        ArrayList<String> finalProjects = new ArrayList<>();
        ArrayList<Project> finalProjForGame = new ArrayList<>();
        finalProjects.add(prompts.FINAL_PROJECT1_NAME);
        finalProjects.add(prompts.FINAL_PROJECT2_NAME);
        finalProjects.add(prompts.FINAL_PROJECT3_NAME);
        Collections.shuffle(finalProjects);
        String finalProjName = finalProjects.get(0);
        finalProjects.remove(finalProjName);
        Project finalProject = new Project(finalProjName);
        finalProjForGame.add(finalProject);
        currentHRSystem.updateFinalProject(finalProjForGame);
    }

    /**
     * Helper function for generateInterns.
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
     * @return a HashMap of Skills and the percentage of the skill
     */
    private HashMap<String, Integer> generateUniqueSkillMap(ArrayList<String> skillList){
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






}
