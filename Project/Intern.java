import java.util.HashMap;
import java.util.Map;

/*
the constructor
method get skills (private) -> returns the skills of the interns from a text file -> (create text file)
we need to store the new interns to input into the HRSystem -> method generateinternfile
- method getInternInfo

 */
 */

 */
    // the attributes:
    /* internName: the name of the interns
    internAge: the age of the intern
    internSkills: a map of skills where the key is the skill and the value is the percentage.
     */
public class Intern {


        String internName;
        int internAge;
        Map<String, Integer> internSkills;


        public Intern(String internName, Integer internAge) {
            this.internName = internName;
            this.internAge = internAge;
            Map<String, Integer> internSkills = new HashMap<>();
        }
    }