import java.util.*;

/* add the new interns to a list of new employees that the player can access with their skills
HRSystem would extend Intern class?
method updateInternList which takes in an ArrayList and puts it as the intern_list
intern_list is a private instance
method makeInternToPrompt which returns the interns in the intern_list as a formatted string (Can use method in intern
which formats each single intern into a string)

    - method fireIntern

 */
// the attribute: internList, a list of interns.
public class HRSystem {

    //initialize the private intern_list
    //initialize the private player_name

    private ArrayList<Intern> internList;
    private String player_name;

    //write method getInternList

    public ArrayList<Intern> getInternList() {

        return internList;
    }

    //write the constructor (should take in nothing)

    public HRSystem() {

        this.internList = new ArrayList<>();
    }

    //write method updateInternList

    public void updateInternList(ArrayList<Intern> interns) {

        this.internList.addAll(interns);
    }

    //write method makeInternsToPrompt

    // for each intern in internList, convert intern to a string and print
    public String makeInternsToPrompt() {
        StringBuilder res = new StringBuilder();
        for (Intern i : this.internList) {
            res.append(i.internToString());
        }
        return res.toString();
    }
    public void update_player_name(String name){

        this.player_name = name;
    }

    public void fireIntern(HiredIntern intern){
        this.internList.remove(intern);
    }
}
