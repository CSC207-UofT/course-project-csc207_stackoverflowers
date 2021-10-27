package Entities;

import java.util.*;

/* add the new interns to a list of new employees that the player can access with their skills
Entities.HRSystem would extend Entities.Intern class?
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
    private ArrayList<Project> projectList;
    private String playerName;

    public HRSystem() {
        this.internList = new ArrayList<>();
    }

    public ArrayList<Intern> getInternList() {
        return internList;
    }



    public void updateInternList(ArrayList<Intern> interns) {

        this.internList.addAll(interns);
    }

    public void updateProjectList(){
        //TODO: finish this method(parameters)
    }

    public String makeInternsToPrompt() {
        StringBuilder res = new StringBuilder();
        for (Intern i : this.internList) {
            res.append(i.internToString());
        }
        return res.toString();
    }

    public void updatePlayerName(String name){
        this.playerName = name;
    }

    public void fireIntern(HiredIntern intern){
        this.internList.remove(intern);
    }
}
