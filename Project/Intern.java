import java.util.HashMap;
import java.util.Map;


public class Intern {
    /*
the constructor
method get skills (private) -> returns the skills of the interns from a text file -> (create text file)
we need to store the new interns to input into the HRSystem -> method generateinternfile
- method getInternInfo

 */
    // the attributes:
    /* internName: the name of the intern
    internAge: the age of the intern
    internSkills: a map of skills where the key is the name of the skill and the value is the percentage.
     */
        private String internName;
        private int internAge;
        private HashMap<String, Integer> internSkills;

        public Intern(String internName, Integer internAge, HashMap<String, Integer> skills) {
            this.internName = internName;
            this.internAge = internAge;
            this.internSkills = skills;
        }
        //TODO: Add get method for intern name, age and skills.
    }