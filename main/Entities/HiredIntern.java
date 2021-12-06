package Entities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

public class  HiredIntern extends Intern implements Serializable {
    // class is for an Entities.Intern that has passed the interview and is hired.
    // hired intern will be able to take part in projects, have update_skill method and can get fired.

    private boolean upgraded;

    /**
     * Construct a hiredIntern, nothing different from Intern.
     */
    public HiredIntern(String hiredInternName, int hiredInternAge, HashMap<String, Double> hiredInternSkills) {
        super(hiredInternName, hiredInternAge, hiredInternSkills);
    }

    public void updateInternSkills(String skillToUpgrade) {
        internSkills.put(skillToUpgrade, internSkills.get(skillToUpgrade) + 5);
    }
}