import java.util.HashMap;
import java.util.Map;


public class Intern {
    /* the constructor method get skills (private) -> returns the skills of the interns from a text file -> (create text file)
    we need to store the new interns to input into the HRSystem -> method generateInternFile
    - method getInternInfo

    */
    private String internName;
    private int internAge;
    private HashMap<String, Integer> internSkills;

    /**
     * Construct an Intern, giving them the given internName,
     * internAge, and internMoney.
     *
     * @param internName The name of this Intern
     * @param internAge The age of this Intern
     * @param internSkills A map of skills where the key is the name of the skill and the value is the percentage.
     */
    public Intern(String internName, int internAge, HashMap<String, Integer> internSkills) {
        this.internName = internName;
        this.internAge = internAge;
        this.internSkills = internSkills;
    }

    /**
     * Return the name of this Intern
     */
    public String getInternName() { return this.internName; }

    /**
     * Return the age of this Intern
     */
    public int getInternAge() { return this.internAge; }

    /**
     * Return the skills of this Intern
     */
    public HashMap<String, Integer> getInternSkills() { return this.internSkills; }

    /**
     * Return a String of the Intern's given information.
     */
    public String internToString() {
        StringBuilder information = new StringBuilder("Name: " + this.internName + "\n");
        information.append("Age: ").append(this.internAge).append("\n");
        information.append("Skills:\n");


        for (String skill : this.internSkills.keySet()) {
            int percentage = this.internSkills.get(skill);
            information.append("-").append(skill).append(": ").append(percentage).append("\n");
        }

        return information.toString();
    }
}