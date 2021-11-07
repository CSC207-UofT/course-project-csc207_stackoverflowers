package Entities;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Objects;
import java.io.File;  // Import the File class
import java.util.Scanner; // Import the Scanner class to read text files

// The project in this game.
public class Project {
    /*
    Instance variables:
     * projectName: the name of this project. Note the name has to be the same as the same the PROJECT_NAME constants in GamePrompts.
     * projectDescription: the description of this project
     * length: the length of this project, which is 2 (months) for regular projects and 1 (month) for final projects
     * teamSize: the number of interns required to finish this project. By default, team size is b/n 2 - 6, however,
     for the final project, team size is 1
     * skillsCompatibilities: a dictionary that maps a skill with a percentage b/n 30% - 100%, which indicates the skill's
      compatability with this project (so the higher the percentage associated with a particular skill, the more this project
      requires this skill)
     * isFinal: a boolean value that indicates whether this project is a final project (as comparing to normal projects)
     */
    private String projectName;
    private String projectDescription;
    private int length;
    private int teamSize;
    private final HashMap<String, Integer> skillsCompatibilities;
    private boolean isFinal;


    /**
     * Construct a project.
     * Note that </projectName> has to be the same as one of the PROJECT_NAME constants in GamePrompts.
     */
    public Project(String projectName) throws FileNotFoundException {
        this.projectName = projectName;
        // If we were to randomize team size b/n 2-6
        //Random random = new Random();
        //this.teamSize = random.nextInt(6 + 1 - 2) + 2; //random.nextInt(max + 1 - min) + min
        this.teamSize = 3;
        this.isFinal = false;
        this.length = 2;
        this.skillsCompatibilities = new HashMap<>();
        GamePrompts gamePrompts = new GamePrompts();

        if (Objects.equals(this.projectName, gamePrompts.PROJECT1_NAME)) {
            this.projectDescription = gamePrompts.PROJECT1_DESCRIPTION;
            skillsCompatibilitiesHelper("proj1comp.txt");
            }
        else if (Objects.equals(this.projectName, gamePrompts.PROJECT2_NAME)) {
            this.projectDescription = gamePrompts.PROJECT2_DESCRIPTION;
            skillsCompatibilitiesHelper("proj2comp.txt");
        }
        else if (Objects.equals(this.projectName, gamePrompts.PROJECT3_NAME)) {
            this.projectDescription = gamePrompts.PROJECT3_DESCRIPTION;
            skillsCompatibilitiesHelper("proj3comp.txt");
        }
        else if (Objects.equals(this.projectName, gamePrompts.PROJECT4_NAME)) {
            this.projectDescription = gamePrompts.PROJECT4_DESCRIPTION;
            skillsCompatibilitiesHelper("proj4comp.txt");
        }
        else if (Objects.equals(this.projectName, gamePrompts.PROJECT5_NAME)) {
            this.projectDescription = gamePrompts.PROJECT5_DESCRIPTION;
            skillsCompatibilitiesHelper("proj5comp.txt");
        }
        else if (Objects.equals(this.projectName, gamePrompts.PROJECT6_NAME)) {
            this.projectDescription = gamePrompts.PROJECT6_DESCRIPTION;
            skillsCompatibilitiesHelper("proj6comp.txt");
        }
        else if (Objects.equals(this.projectName, gamePrompts.PROJECT7_NAME)) {
            this.projectDescription = gamePrompts.PROJECT7_DESCRIPTION;
            skillsCompatibilitiesHelper("proj7comp.txt");
        }
        else if (Objects.equals(this.projectName, gamePrompts.PROJECT8_NAME)) {
            this.projectDescription = gamePrompts.PROJECT8_DESCRIPTION;
            skillsCompatibilitiesHelper("proj8comp.txt");
        }
        else if (Objects.equals(this.projectName, gamePrompts.FINAL_PROJECT1_PROMPT)) {
            this.projectDescription = gamePrompts.FINAL_PROJECT1_DESCRIPTION;
            makeProjectFinal();
            skillsCompatibilitiesHelper("finalproj1comp.txt");
        }
        else if (Objects.equals(this.projectName, gamePrompts.FINAL_PROJECT2_PROMPT)) {
            this.projectDescription = gamePrompts.FINAL_PROJECT2_DESCRIPTION;
            makeProjectFinal();
            skillsCompatibilitiesHelper("finalproj2comp.txt");
        }
        else if (Objects.equals(this.projectName, gamePrompts.FINAL_PROJECT3_PROMPT)) {
            this.projectDescription = gamePrompts.FINAL_PROJECT3_DESCRIPTION;
            makeProjectFinal();
            skillsCompatibilitiesHelper("finalproj3comp.txt");
        }
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
     * A helper method that reads the skillsCompatibilities files and initializes </skillsCompatibilities>.
     * @return
     */
    private void skillsCompatibilitiesHelper(String file_name) throws FileNotFoundException {
        File file = new File(file_name);
        Scanner myReader = new Scanner(file);
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            String skill = line.split(": ")[0];
            Integer score = Integer.valueOf(line.split(": ")[1]);
            this.skillsCompatibilities.put(skill, score);
        }
        }



    /**
     * Return whether if this project is final.
     */
    public boolean isFinal(){return this.isFinal;}

    // Belows are the getters and setters for all the instance variables.
    public String getProjectName(){
        return this.projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public int getLength() {
        return length;
    }
    // we prolly don't want a setter for length?

    public int getTeamSize() {
        return teamSize;
    }
    // we prolly don't want a setter for teamSize?

    public HashMap<String, Integer> getSkillsCompatibilities() {
        return skillsCompatibilities;
    }
    // we prolly don't want a setter for skillsCompatibilities?

    public String projectToString() {
        // TODO: Implement this method. See Intern internToString
        return "Turns a project into a string is not implemented yet.";
    }
}