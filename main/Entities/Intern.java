package Entities;

import java.util.HashMap;


abstract public class Intern{
    /* the constructor method get skills (private) -> returns the skills of the interns from a text file -> (create text file)
    we need to store the new interns to input into the UseCases.HRSystem -> method generateInternFile
    - method getInternInfo

    */
    private final String internName;
    private final int internAge;
    private final HashMap<String, Double> internSkills;

    /**
     * Construct an Entities.Intern, giving them the given internName,
     * internAge, and internMoney.
     *
     * @param internName The name of this Entities.Intern
     * @param internAge The age of this Entities.Intern
     * @param internSkills A map of skills where the key is the name of the skill and the value is the percentage.
     */
    public Intern(String internName, int internAge, HashMap<String, Double> internSkills) {
        this.internName = internName;
        this.internAge = internAge;
        this.internSkills = internSkills;
    }

    /**
     * Return the name of this Entities.Intern
     */
    public String getInternName() {

        return this.internName;
    }

    /**
     * Return the age of this Entities.Intern
     */
    public int getInternAge() {

        return this.internAge;
    }

    /**
     * Return the skills of this Entities.Intern
     */
    public HashMap<String, Double> getInternSkills() {
        return this.internSkills;
    }

    /**
     * Return a String of the Entities.Intern's given information.
     */
    public String internToString() {
        String info = "Name: " + this.internName + "; age: " + this.internAge + "; skills: ";
        StringBuilder skills =  new StringBuilder();

        for (String skill : this.internSkills.keySet()) {
            Double percentage = this.internSkills.get(skill);
            skills.append(skill).append(" (").append(percentage).append(")");
        }

        return info + skills + "\n";

    }

}