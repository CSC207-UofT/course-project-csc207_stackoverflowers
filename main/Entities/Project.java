package Entities;
import java.util.HashMap;
import java.util.Objects;
import java.io.File;  // Import the File class
import java.util.Scanner; // Import the Scanner class to read text files

// The project in this game.
public class Project {
    /*
    Instance variables:
     * name: the name of this project. Note the name has to be the same as the same the PROJECT_NAME constants in GamePrompts.
     * description: the description of this project
     * length: the length of this project, which is 2 (months) for regular projects and 1 (month) for final projects
     * teamSize: the number of interns required to finish this project. By default, team size is b/n 2 - 6, however,
     for the final project, team size is 1
     * skillsCompatibilities: a dictionary that maps a skill with a number b/n 0.00 and 1.00, which indicates the skill's
      compatability with this project (so the higher the percentage associated with a particular skill, the more this project
      requires this skill)
     * isFinal: a boolean value that indicates whether this project is a final project (as comparing to normal projects)
     * nameToDscpPromptFile: a map whose key is the project name, and the associated value is an array (of length 3) in
     which the first element is the corresponding project description, the second element is the corresponding project
     prompt, and the third element is the corresponding projcomp file name.
     */
    private String name;
    private String description;
    private int length;
    private int teamSize;
    private HashMap<String, Integer> skillsCompatibilities;
    private boolean isFinal;
    private HashMap<String, String[]> nameToDscpPromptFile;

    /**
     * Construct a project.
     * Note that argument </projectName> has to be the same as one of the PROJECT_NAME constants in GamePrompts.
     */
    public Project(String projectName) {
        this.name = projectName;

        this.length = 2;

        // If we were to randomize team size b/n 2-6:
        //Random random = new Random();
        //this.teamSize = random.nextInt(6 + 1 - 2) + 2; //random.nextInt(max + 1 - min) + min
        this.teamSize = 3;

        this.isFinal = false;

        initializeNameToDscpPromptFile();

        this.description = this.nameToDscpPromptFile.get(this.name)[0];

        initializeSkillsCompatibilities(this.nameToDscpPromptFile.get(this.name)[2]);

        if (Objects.equals(this.name, gamePrompts.FINAL_PROJECT1_NAME) ||
           (Objects.equals(this.name, gamePrompts.FINAL_PROJECT2_NAME) ||
           (Objects.equals(this.name, gamePrompts.FINAL_PROJECT3_NAME) {
               makeProjectFinal();
        }

    /**
     * A helper method that initializes </nameToDscpPromptFile> in the constructor.
     */
    private void initializeNameToDscpPromptFile() {
        GamePrompts gamePrompts = new GamePrompts();
        HashMap<String, String[]> map = new HashMap<>();
        map.put(gamePrompts.PROJECT1_NAME, new String[]{gamePrompts.PROJECT1_DESCRIPTION, gamePrompts.PROJECT1_PROMPT, "proj1comp.txt"});
        map.put(gamePrompts.PROJECT2_NAME, new String[]{gamePrompts.PROJECT2_DESCRIPTION, gamePrompts.PROJECT2_PROMPT, "proj2comp.txt"});
        map.put(gamePrompts.PROJECT3_NAME, new String[]{gamePrompts.PROJECT3_DESCRIPTION, gamePrompts.PROJECT3_PROMPT, "proj3comp.txt"});
        map.put(gamePrompts.PROJECT4_NAME, new String[]{gamePrompts.PROJECT4_DESCRIPTION, gamePrompts.PROJECT4_PROMPT, "proj4comp.txt"});
        map.put(gamePrompts.PROJECT5_NAME, new String[]{gamePrompts.PROJECT5_DESCRIPTION, gamePrompts.PROJECT5_PROMPT, "proj5comp.txt"});
        map.put(gamePrompts.PROJECT6_NAME, new String[]{gamePrompts.PROJECT6_DESCRIPTION, gamePrompts.PROJECT6_PROMPT, "proj6comp.txt"});
        map.put(gamePrompts.PROJECT7_NAME, new String[]{gamePrompts.PROJECT7_DESCRIPTION, gamePrompts.PROJECT7_PROMPT, "proj7comp.txt"});
        map.put(gamePrompts.PROJECT8_NAME, new String[]{gamePrompts.PROJECT8_DESCRIPTION, gamePrompts.PROJECT8_PROMPT, "proj8comp.txt"});
        map.put(gamePrompts.FINAL_PROJECT1_NAME, new String[]{gamePrompts.FINAL_PROJECT1_DESCRIPTION, gamePrompts.FINAL_PROJECT1_PROMPT, "finalproj1comp.txt"});
        map.put(gamePrompts.FINAL_PROJECT2_NAME, new String[]{gamePrompts.FINAL_PROJECT2_DESCRIPTION, gamePrompts.FINAL_PROJECT2_PROMPT, "finalproj2comp.txt"});
        map.put(gamePrompts.FINAL_PROJECT3_NAME, new String[]{gamePrompts.FINAL_PROJECT3_DESCRIPTION, gamePrompts.FINAL_PROJECT3_PROMPT, "finalproj3comp.txt"});
        this.nameToDscpPromptFile = map;
    }

    /**
     * A helper method that reads the skillsCompatibilities files and initializes </skillsCompatibilities>  in the constructor.
     */
    private void initializeSkillsCompatibilities(String file_name) {
        HashMap<String, Integer> map = new HashMap<>();
        File file = new File(file_name);
        Scanner myReader = new Scanner(file);
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            String skill = line.split(": ")[0];
            Integer score = Integer.valueOf(line.split(": ")[1]);
            map.put(skill, score);
        this.skillsCompatibilities = map;
    }

    /**
     * A helper method that makes the project final in the constructor.
     */
    private void makeProjectFinal() {
        this.isFinal = true;
        this.teamSize = 1;
        this.length = 1;
    }

    /**
     * Return whether if this project is final.
     */
    public boolean isFinal() {
        return isFinal;
    }

    /**
     * Return the string representation of the project.
     */
    public String projectToString(){
        GamePrompts gamePrompts = new GamePrompts();
        String prompt = this.nameToDscpPromptFile.get(this.name)[1];
        return gamePrompts.prompt;
    }

    // Belows are the getters for all the instance variables.
    public String getProjectName() {
        return name;
    }

    public String getProjectDescription() {
        return description;
    }

    public int getLength() {
        return length;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public HashMap<String, Integer> getSkillsCompatibilities() {
        return skillsCompatibilities;
    }
}