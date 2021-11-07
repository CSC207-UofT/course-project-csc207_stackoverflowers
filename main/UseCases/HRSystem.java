package UseCases;

import Entities.HiredIntern;
import Entities.Intern;
import Entities.Project;

import java.util.*;

/* add the new interns to a list of new employees that the player can access with their skills
UseCases.HRSystem would extend Entities.Intern class?
method updateInternList which takes in an ArrayList and puts it as the intern_list
intern_list is a private instance
method makeInternToPrompt which returns the interns in the intern_list as a formatted string (Can use method in intern
which formats each single intern into a string)

//TODO: Finish method fireIntern

//TODO: Implement method updateProjectList

// TODO: update final project !!

//TODO: Specify and implement update and get InternList for each type of intern (HiredIntern and InterviewIntern)

 */
// the attribute: internList, a list of interns.
public class HRSystem {
    public static final int FINAL_MONTH = 5;

    //initialize the private intern_list
    //initialize the private player_name

    private ArrayList<Intern> internList;//TODO: Is it better if we used a set....
    // so that we don't have repeating issues (when adding new elements to the set)?
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

    public void updateProjectList(ArrayList<Project> projects){
        //TODO: finish this method(parameters)
        projectList = projects;
    }

    public String makeInternsToString() {
        StringBuilder res = new StringBuilder();
        for (Intern i : this.internList) {
            res.append(i.internToString());
        }
        return res.toString();
    }
    //Overloaded the same method bc I just want the hired interns list as well.
    public String makeInternsToString(boolean hired) {
        StringBuilder result = new StringBuilder();
            for (Intern i : this.internList) {
                if ((i instanceof HiredIntern) & hired){
                    result.append(i.internToString());
                }
        }
        return result.toString();
    }

    public String makeProjectsToString(int currentMonth) {
        //TODO: This method returns only the projects that should be displayed for that particular month,
        // indicated by the given parameter
        return "Returning a month's list of projects is not implemented yet. ";
    }

    public String makeAssignmentToString(int currentMonth) {
        //a method that takes all the assigning so far and returns the List of projects and interns assigned to it.
        StringBuilder result = new StringBuilder();
        for (Project p : projectList){
            result.append(p.getProjectName());
            result.append("    Interns in project: ");
            //TODO: finish this method so that it displays all project names and the intern names that are
            // assigned to this project.
        }
        return result.toString();
    }

    public void updatePlayerName(String name){
        this.playerName = name;
    }

    public String getPlayerName(){
        return playerName;
    }

    public void fireIntern(HiredIntern intern){
        this.internList.remove(intern);
    }

    public boolean assignInternToProject(String internName, String projectName) {
        //TODO: implement this method
        //Should return false if Intern is already been assigned to another project, or if they are not hired.
        return false;
    }

    public boolean removeInternFromProject(String internName, String projectName) {
        //TODO: implement this method
        //Should return false if Intern is not in the project yet or in other project, or if Intern is not hired.
        return false;
    }

    public boolean internsAllAssigned(int currentMonth) {
        //a method that checks if all Hiredinterns have been assigned to a project within that month.
        // returns true when all interns have been assigned.
        //TODO: implement this method
        return false;
    }

    public void updateFinalProject(ArrayList<Project> finalProjForGame) {
        //TODO: implement this method
    }
}
