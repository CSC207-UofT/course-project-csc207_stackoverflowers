package UseCases;

import Entities.HiredIntern;
import Entities.Intern;
import Entities.InterviewIntern;
import Entities.Project;

import java.io.Serializable;
import java.util.*;

/* add the new interns to a list of new employees that the player can access with their skills
UseCases.HRSystem would extend Entities.Intern class?
method updateInternList which takes in an ArrayList and puts it as the intern_list
intern_list is a private instance
method makeInternToPrompt which returns the interns in the intern_list as a formatted string (Can use method in intern
which formats each single intern into a string)


 */

public class HRSystem implements Serializable {
    public static final int FINAL_MONTH = 5;

    private final ArrayList<Intern> internList;

    private final ArrayList<HiredIntern> hiredInternList;

    private final ArrayList<InterviewIntern> interviewInternList;

    private final ArrayList<Project> projectList;


    private final HashMap<Integer, ArrayList<Project>> monthToProject;
    // stores the month as a key and a list of projects for that month as a value

    private final HashMap<Project, ArrayList<HiredIntern>> projectToInterns;

    private Object playerInternResponseChoice;
    // stores a project as a key and a list of HiredInterns assigned to that project as a value

    private String playerName;

    private String playerResponse;

    private InterviewIntern playerInternChoice;


    /**
     * The constructor makes a new HRSystem for the current phase, and stores attributes of intern
     * (both Hired and Interview) and Project to use it when needed.
     */
    public HRSystem() {
        this.internList = new ArrayList<>();
        this.hiredInternList = new ArrayList<>();
        this.interviewInternList = new ArrayList<>();
        this.projectList = new ArrayList<>();
        this.monthToProject = new HashMap<>();
        this.projectToInterns = new HashMap<>();
    }


    /**
     * This method gets the list of Entities.Intern.
     * @return an ArrayList of Entities.Intern
     */
    public ArrayList<Intern> getInternList() {
        return this.internList;
    }

    /**
     * This method gets the list of Entities.HiredIntern
     * @return an ArrayList of Entities.HiredIntern
     */
    public ArrayList<HiredIntern> getHiredInternList(){
        return this.hiredInternList;
    }

    /**
     * This method gets the list of Entities.InterviewIntern
     * @return an ArrayList of Entities.InterviewIntern
     */
    public ArrayList<InterviewIntern> getInterviewInternList(){
        return this.interviewInternList;
    }

    /**
     * This method updates the list of Entities.Intern
     * @param interns the ArrayList of Entities.Intern to add to the current list of interns.
     */
    public void updateInternList(ArrayList<Intern> interns) {
        this.internList.addAll(interns);
    }

    /**
     * This method updates the list of Entities.HiredIntern
     * @param hiredInterns the ArrayList of Entities.HiredIntern to add to the current list of HiredInterns.
     */
    public void updateHiredInternList(ArrayList<HiredIntern> hiredInterns){
        this.hiredInternList.addAll(hiredInterns);
    }

    /**
     * This method updates the list of Entities.InterviewIntern
     * @param interviewInterns the ArrayList of Entities.HiredIntern to add to the current list of HiredInterns.
     */
    public void updateInterviewInternList(ArrayList<InterviewIntern> interviewInterns){
        this.interviewInternList.addAll(interviewInterns);
    }


    /**
     * This method updates the list of Entities.Project
     * @param projects the ArrayList of Entities.Project to be added to the current list of projects.
     */
    public void updateProjectList(ArrayList<Project> projects){
        this.projectList.addAll(projects);
    }

    /**
     * This method updates the HashMap containing each month as its key and an ArrayList of Entities.Project as a
     * value pair.
     * @param month the Integer indicating the month of the current game.
     * @param projects the ArrayList of Entities.Project that is a value of each month in the HashMap.
     */
    public void updateMonthToProject(Integer month, ArrayList<Project> projects){
        this.monthToProject.put(month, projects);
    }

    /**
     * This method updates the HashMap containing an Entities.Project as a key and has an ArrayList of
     * Entities.HiredInterns as a value pair.
     * @param project the Entities.Project that is associated with a list of HiredInterns
     * @param hiredInterns the ArrayList of Entities.HiredInterns that is a value of each project in the HashMap.
     */
    public void updateProjectToInterns(Project project, ArrayList<HiredIntern> hiredInterns){
        this.projectToInterns.put(project, hiredInterns);
    }

    /**
     * This method gets only the names of all interns separated by "|".
     * @return a String of all intern names
     */
    public String getInternNames() {
        StringBuilder res = new StringBuilder();
        for (Intern i : this.internList) {
            res.append(i.getInternName());
            res.append("|");
        }
        return res.toString();
    }

    /**
     * This method gets only the names of all Entities.HiredInterns separated by "|".
     * @return a String of all Entities.HiredIntern names
     */
    public String getHiredInternNames() {
        StringBuilder result = new StringBuilder();
        for (HiredIntern i : this.hiredInternList) {
            result.append(i.getInternName());
            result.append("|");

        }
        return result.toString();
    }

    /**
     * This method gets only the names of all Entities.InterviewInterns separated by "|".
     * @return a String of all Entities.InterviewIntern names
     */
    public String getInterviewInternNames() {
        StringBuilder result = new StringBuilder();
        for (InterviewIntern i : this.interviewInternList) {
            result.append(i.getInternName());
            result.append("|");

        }
        return result.toString();
    }


    /**
     * This method gets a String representation of all intern info.
     * @return a String of intern information for every intern in internList
     */
    public String makeInternsToString() {
        StringBuilder res = new StringBuilder();
        for (Intern i : this.internList) {
            res.append(i.internToString());
        }
        return res.toString();
    }

    /**
     * This method gets a String representation of all Entities.HiredIntern info.
     * @return a String of intern information for all Entities.HiredIntern in hiredInternList
     */
    public String makeHiredInternsToString() {
        StringBuilder result = new StringBuilder();
        for (HiredIntern i : this.hiredInternList) {
            result.append(i.internToString());
        }
        return result.toString();
    }

    /**
     * This method creates a String representation of all projects in Entities.Project per a given month level.
     * @param currentMonth an Integer representation for the current month in the current game.
     * @return a String of project information for all projects done in the given month.
     */
    public String makeProjectsToString(int currentMonth) {
        ArrayList<Project> monthlyProjList = this.monthToProject.get(currentMonth);
        StringBuilder res = new StringBuilder();
        for (Project proj : monthlyProjList ) {
            res.append(proj.projectToString());
        }
        return res.toString();
    }

    /**
     * This method gets only the name of projects in Entities.Project per a given month.
     * @param currentMonth an Integer representation for the current month in the current game.
     * @return a String representation of project name for each project in Entities.Project for the given month.
     */
    public String getProjectName(int currentMonth) {
        ArrayList<Project> monthlyProjList = this.monthToProject.get(currentMonth);
        StringBuilder res = new StringBuilder();
        for (Project proj : monthlyProjList){
            res.append(proj.getName());
        }
        return res.toString();

    }

    /**
     * This method returns the list of all projects in the current month.
     * @param currentMonth an Integer representation for the current month in the current game.
     * @return an ArrayList of all projects in Entities.Project for the given month.
     */
    public ArrayList<Project> getProject(int currentMonth){
        return this.monthToProject.get(currentMonth);
    }


    /**
     * This method returns a String representation of each project in Entities.Project and the assigned interns
     * from Entities.HiredInterns per a given month.
     * @param currentMonth an Integer representation for the current month in the current game.
     * @return a String representing each project and the assigned interns to a project.
     */
    public String makeAssignmentToString(int currentMonth) {

        // get the projects for the month
        ArrayList<Project> monthlyProjList = this.monthToProject.get(currentMonth);
        StringBuilder res = new StringBuilder();

        for (Project proj : monthlyProjList) {

            //append each project from the project list for the current month to res
            res.append(proj.getName());

            //append the interns associated to that project to res
            res.append(" Interns in project: ");
            res.append(projectToInterns.get(proj));
        }

        return res.toString();

    }


    /**
     * This method displays the Entities.Intern current skill point in String format.
     * @param currentMonth the given month that an intern skill is being checked.
     * @return a String representation of the Entities.Intern upgraded skills point.
     */
    //not sure how to finish this method? what exactly is the upgrade we're asking for? ask Jacob, same with other two
    // upgrade methods
    public String makeUpgradeToString(int currentMonth) {
        //a method that takes the upgrade and returns a list of skills point the intern have now.
        ArrayList<Project> monthlyProjList = this.monthToProject.get(currentMonth);
        StringBuilder result = new StringBuilder();
        for (Project p : monthlyProjList){
            result.append(p.getName());
            result.append("    Interns in project: ");
            this.monthToProject.get(p);
            //TODO: finish this method so that it displays a list of skills point the intern have now
        }

        return result.toString();
    }


    /**
     * This method updates a Player's name.
     * @param name the name of a current Player.
     */
    public void updatePlayerName(String name){
        this.playerName = name;
    }

    /**
     * This method gets the Player's name information.
     * @return a String representation of the current Player's name.
     */
    public String getPlayerName(){
        return playerName;
    }

    /**
     * This method updates the players choice to hire an intern or not.
     * @param res a String representation of the current Player's choice.
     */
    public String updatePlayerResponse(String res){
        return this.playerResponse = res;
    }

    /**
     * This method gets the Player's response to hire an intern.
     * @return a String representation of the current Player's response.
     */
    public String getPlayerResponse(){
        return this.playerResponse;
    }

    /**
     * This method fire's an intern during the interview process or after completion of a project.
     * @param intern the Entities.Intern to be fired.
     */
    public void fireIntern(Intern intern) {
        if (intern instanceof HiredIntern) {
            this.hiredInternList.remove(intern);
        } else {
            this.internList.remove(intern);
        }
    }

    /**
     * This method hires an intern into the company.
     * @param intern the Entities.Intern to be hired.
     */
    public void hireIntern(Intern intern){
        this.hiredInternList.add((HiredIntern) intern);
    }


    /**
     * This method returns if an Entities.Intern has been hired to the company.
     * @param intern the Entities.Intern to check hired status.
     * @return true if the Entities.Intern has been hired or false otherwise.
     */
    public Boolean isHired(Intern intern){
        return this.hiredInternList.contains((HiredIntern) intern);
    }


    public void updatePlayerInternChoice(InterviewIntern chosenIntern){
        this.playerInternChoice = chosenIntern;
    }

    public InterviewIntern getPlayerInternChoice(){
        return this.playerInternChoice;
    }

    public String choicesToString(){
        ArrayList<ResponseTree> questionChoices = new ArrayList<ResponseTree>();
        for (InterviewIntern intern : interviewInternList){
            questionChoices.addAll(intern.getResponseTree().getChildren());
        }
        StringBuilder res = new StringBuilder();

        for ( ResponseTree choice : questionChoices){
            res.append(choice.getChildren().get(0));
        }

        return res.toString();
    }

    public ArrayList<Object> getChoices(InterviewIntern intern){
        ArrayList<ResponseTree> questionChoices = new ArrayList<>(intern.getResponseTree().getChildren());

        ArrayList<Object> options = new ArrayList<>();

        for (ResponseTree choice : questionChoices){
            options.add(choice.getChildren().get(0));
        }
        return options;

    }

    public void updatePlayerInternResponseChoice(Object option){
        this.playerInternResponseChoice = option;
    }

    public Object getPlayerInternResponseChoice(Object object){
        return this.playerInternResponseChoice;
    }

    public String getInternChoiceResponse(Object playerChoice, InterviewIntern intern){

        ArrayList<ResponseTree> questionChoices = new ArrayList<>(intern.getResponseTree().getChildren());
        StringBuilder res = new StringBuilder();

        for ( ResponseTree choice : questionChoices){
            if (choice.getChildren().get(0).equals(playerChoice)){
                res.append(choice.getChildren().get(1));
            }
        }

        return res.toString();
    }


    /**
     * This method assigns a given Entities.Intern to a given Entities.Project.
     * @param internName the name of the Entities.Intern to be assigned.
     * @param projectName the name of the Entities.Project assigned to an Entities.Intern.
     * @return true if an assignment was successful and false if the given Entities.Intern is already assigned to
     * another project, or they are not hired.
     */
    public boolean assignInternToProject(String internName, String projectName) {
        //Should return true if assignment was successful.
        //Else, should poi return false if Intern is already been assigned to another project, or if they are not hired.
        ArrayList<HiredIntern> inter = new ArrayList<HiredIntern>();
        for (Intern i : this.internList) {
            for (Project p : this.projectList) {
                if ((i.getInternName().equals(internName)) & (p.getName().equals(projectName)) ){
                    inter.add((HiredIntern) i);
                    this.projectToInterns.put(p, inter);
                    return true;
                }
                if ((this.projectToInterns.get(p).contains((HiredIntern) i)) || (!(isHired(i)))){
                    return false;
                }
            }
        }
        return false;
    }

    //TODO: finish new method
    public boolean assignInternToUpgrade(String internName) {
        //TODO: implement this method
        //Should return false if Intern's specific skill is already max, or if they do not exist.
        return false;
    }

    /**
     * This method removes a given Entities.HiredIntern from an assigned Entities.Project.
     * @param internName the name of the Entities.HiredIntern to be removed.
     * @param projectName the name of the Entities.Project to be removed.
     * @return true if this Entities.HiredIntern has been removed from an assignment or false if Intern is not in the
     * project yet or in another project, or if Intern is not hired.
     */
    public boolean removeInternFromProject(String internName, String projectName) {
        for (Intern i : this.internList) {
            for (Project p : this.projectList) {
                if ((i.getInternName().equals(internName)) & (p.getName().equals(projectName)) ){
                    this.projectToInterns.get(p).remove((HiredIntern) i);
                    return true;
                }
                if (! (this.projectToInterns.get(p).contains((HiredIntern) i)) || (!(isHired(i)))){
                    return false;
                }
            }
        }
        return false;

    }

    /**
     * This method checks if all Entities.HiredIntern have been assigned to an Entities.Project per a
     * given month.
     * @param currentMonth the Integer representation of the month being played.
     * @return true if all Entities.HiredIntern have been assigned and false otherwise.
     */
    public boolean internsAllAssigned(int currentMonth) {
        ArrayList<Project> monthlyProjList = this.monthToProject.get(currentMonth);
        boolean assigned = false;
        for (HiredIntern i : this.hiredInternList) {
            for (Project proj : monthlyProjList) {
                if (this.projectToInterns.get(proj).contains(i)) {
                    assigned = true;
                }
            }
        }
        return assigned;

    }


    //TODO: finish this new method
    public boolean internUpgraded(int currentMonth) {
        //a method that checks if a intern have been upgraded.
        // returns true when a intern have been upgraded.
        //TODO: implement this method
        return false;
    }


    /**
     * This method adds the chosen final project to the current game's Entities.Project list.
     * @param finalProjForGame the final Entities.Project to be added.
     */
    public void updateFinalProject(ArrayList<Project> finalProjForGame) {
        this.projectList.addAll(finalProjForGame);
    }
}
