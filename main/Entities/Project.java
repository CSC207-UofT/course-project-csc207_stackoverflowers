package Entities;

import java.util.ArrayList;
import java.util.Random;

// The project in this game.
public class Project {
    /*
    Instance variables:
     * projectName: the name of this project
     * projectDescription: the description of this project
     * length: the length of this project, which is always 2 (months)
     * teamSize: the number of interns required to finish this project. By default, team size is b/n 2 - 6, however,
     for the final project, team size is 1
     * skillsCompatability: a dictionary that maps a skill with a percentage b/n 30% - 100%, which indicates the skill's
      compatability with this project (so the higher the percentage associated with a particular skill, the more this project
      requires this skill)
     * isFinal: a boolean value that indicates whether this project is a final project (as comparing to normal projects)
     */
    private String projectName;
    private String projectDescription;
    private final int length;
    private int teamSize;
    private ArrayList<String> skillsCompatability;
    private boolean isFinal;


    public Project(String projectName, String projectDescription) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.length = 2;

        // Rn I'm just randomly generating an int b/n 2 - 6 (inclusive) (6 bc we have 6 interns in total right??) for
        // the team size. This is for normal projects; for the final project, the team size has to be 1.
        Random random = new Random();
        this.teamSize = random.nextInt(6 + 1 - 2) + 2; //random.nextInt(max + 1 - min) + min
        this.isFinal = false;
    }
    /* I'm not sure how to initialize skillsCompatability. It should be a map that's like: {teamwork: 80%, leadership: 90%,
    communication: 60%, ...}, including all the skills specified in the skills txt file, correct? However, the percentage
    associate with each skill cannot be completely randomly generated, since, like we've talked about in vc, each project
     has some 'theme' skills. E.g., project <crossCulturalExchange> requires multilingualism & communication, and so its
     <skillsCompatability> would be smth like {teamwork: 80%, leadership: 70%, communication: 100%, multilingualism: 100% ...}

     So I think we need to:
     1) decide on a list of project (names)
     2) decide on a list of skills
     3) for each project, decide which skills it requires (we might need an instance variable like <skillsRequired> to store this?)
     4) for <skillsCompatability> of each project, those skilled that are in <skillsRequired> will be associated with 100%,
     and other skills can have percentages that are randomly generated b/n, say, 30% - 80%?

     What do you think? :D
     */

    /**
     * Make the project final.
     * Note that by default the project isn't final.
     */
    public void makeProjectFinal() {
        this.isFinal = true;
        this.teamSize = 1;
    }

    /**
     * Return whether if this project is final.
     */
    public boolean isFinal() {
        return isFinal;
    }

    // Belows are the getters and setters for all the instance variables.
    public String getProjectName() {
        return projectName;
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
    // we prolly don't want a getter for teamSize?

    public ArrayList<String> getSkillsCompatability() {
        return skillsCompatability;
    }
    // we prolly don't want a getter for skillsCompatability?
