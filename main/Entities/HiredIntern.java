package Entities;

import java.util.HashMap;

public class  HiredIntern extends Intern {
    // class is for an Entities.Intern that has passed the interview and is hired.
    // hired intern will be able to take part in projects, have update_skill method and can get fired.

    //TODO: Implement method UpdateSkill

    private String hiredInternName;
    private int hiredInternAge;
    private HashMap<String, Integer> hiredInternSkills;

    /**
     * Construct a hiredIntern, giving them the given hiredInternName,
     * hiredInternAge and hiredInternSkills.
     *
     * @param hiredInternName   The name of this Entities.Intern
     * @param hiredInternAge    The age of this Entities.Intern
     * @param hiredInternSkills A map of skills where the key is the name of the skill and the value is the percentage.
     */
    public HiredIntern(String hiredInternName, int hiredInternAge, HashMap<String, Integer> hiredInternSkills) {
        super(hiredInternName, hiredInternAge, hiredInternSkills);
    }

    /**
     * Update a Entities.HiredIntern's skills after completion of a project.
     */

    public void updateSkill(String NewSkill){
        // by updating a skill we can: remove a skill, add a new skill, or change the percentage of an
        // existing skill.
        // this.hiredInternSkills
    }


}