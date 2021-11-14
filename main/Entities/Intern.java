package Entities;

import UseCases.ResponseTree;

import java.io.Serializable;
import java.util.HashMap;


abstract public class Intern implements Serializable {
    /* the constructor method get skills (private) -> returns the skills of the interns from a text file -> (create text file)
    we need to store the new interns to input into the UseCases.HRSystem -> method generateInternFile
    - method getInternInfo

    */
    private final String internName;
    private final int internAge;
    private HashMap<String, Double> internSkills;
    private int upgradedIn;

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
        upgradedIn = 0;
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

    public int getUpgradedIn() {
        return upgradedIn;
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
            double percentage = this.internSkills.get(skill);
            skills.append(skill).append(": (").append(percentage).append(") ");
        }

        return info + skills + "\n";

    }

    public abstract void updateInternSkills(String skilToUpgrade);

    public void updateUpgraded(int currentMonth){
        upgradedIn = currentMonth;
    };
}