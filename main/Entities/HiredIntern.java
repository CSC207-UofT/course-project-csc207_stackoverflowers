package Entities;

import java.util.HashMap;

public class  HiredIntern extends Intern {
    // class is for an Entities.Intern that has passed the interview and is hired.
    // hired intern will be able to take part in projects, have update_skill method and can get fired.

    private String hiredInternName;
    private int hiredInternAge;
    private HashMap<String, Double> hiredInternSkills;

    /**
     * Construct a hiredIntern, giving them the given hiredInternName,
     * hiredInternAge and hiredInternSkills.
     *  @param hiredInternName   The name of this Entities.Intern
     * @param hiredInternAge    The age of this Entities.Intern
     * @param hiredInternSkills A map of skills where the key is the name of the skill and the value is the percentage.
     */
    public HiredIntern(String hiredInternName, int hiredInternAge, HashMap<String, Double> hiredInternSkills) {
        super(hiredInternName, hiredInternAge, hiredInternSkills);
    }


}