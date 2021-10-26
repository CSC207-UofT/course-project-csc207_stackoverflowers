import java.util.HashMap;

public class HiredIntern extends Intern {
    /*
    this class is for the HiredIntern
    - method updateSkill
    -
     */
    private String hiredInternName;
    private int hiredInternAge;
    private HashMap<String, Integer> hiredInternSkills;

    /**
     * Construct a hiredIntern, giving them the given hiredInternName,
     * hiredInternAge and hiredInternSkills.
     *
     * @param hiredInternName   The name of this Intern
     * @param hiredInternAge    The age of this Intern
     * @param hiredInternSkills A map of skills where the key is the name of the skill and the value is the percentage.
     */
    public HiredIntern(String hiredInternName, int hiredInternAge, HashMap<String, Integer> hiredInternSkills) {
        super(hiredInternName, hiredInternAge, hiredInternSkills);
    }

    /**
     * Return the name of this HiredIntern
     */

    /**
     * Return the age of this HiredIntern
     */
    public int getInternAge() {
        return this.hiredInternAge;
    }

    /**
     * Return the skills of this HiredIntern
     */
    public HashMap<String, Integer> getInternSkills() {
        return this.hiredInternSkills;
    }

    /**
     * Return a String of the HiredIntern's given information.
     */
    public String internToString() {
        String info = "Name: " + this.hiredInternName + "; age: " + this.hiredInternAge + "; skills: ";
        StringBuilder skills = new StringBuilder();

        for (String skill : this.hiredInternSkills.keySet()) {
            int percentage = this.hiredInternSkills.get(skill);
            skills.append(skill).append(" (").append(percentage).append(")");
        }

        return info + skills + "\n";

    }

    /**
     * Update a HiredIntern's skills after completion of a project.
     */

    public void updateSkill(String NewSkill){
        // by updating a skill we can: remove a skill, add a new skill, or change the percentage of an
        // existing skill.
        this.hiredInternSkills
    }


}