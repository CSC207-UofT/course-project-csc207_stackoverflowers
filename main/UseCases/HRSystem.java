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

    private ArrayList<HiredIntern> hiredInternList;

    private ArrayList<InterviewIntern> interviewInternList;

    private ArrayList<Project> projectList;

    private HashMap<Integer, ArrayList<Project>> monthToProject;
    // stores the month as a key and a list of projects for that month as a value

    private final HashMap<Project, ArrayList<HiredIntern>> projectToInterns;

    // stores a project as a key and a list of HiredInterns assigned to that project as a value

    private String playerName;

    private String playerResponse;


    /**
     * The constructor makes a new HRSystem for the current phase, and stores attributes of intern
     * (both Hired and Interview) and Project to use it when needed.
     */
    public HRSystem() {
        this.hiredInternList = new ArrayList<>();
        this.interviewInternList = new ArrayList<>();
        this.projectList = new ArrayList<>();
        this.monthToProject = new HashMap<>();
        this.projectToInterns = new HashMap<>();
    }

    /**
     * This method gets the list of Entities.HiredIntern
     *
     * @return an ArrayList of Entities.HiredIntern
     */
    public ArrayList<HiredIntern> getHiredInternList() {
        return this.hiredInternList;
    }

    /**
     * This method gets the list of Entities.InterviewIntern
     *
     * @return an ArrayList of Entities.InterviewIntern
     */
    public ArrayList<InterviewIntern> getInterviewInternList() {
        return this.interviewInternList;
    }

    /**
     * This method updates the list of Entities.HiredIntern
     *
     * @param hiredInterns the ArrayList of Entities.HiredIntern to add to the current list of HiredInterns.
     */
    public void updateHiredInternList(ArrayList<HiredIntern> hiredInterns) {
        this.hiredInternList.addAll(hiredInterns);
    }

    /**
     * This method updates the list of Entities.InterviewIntern
     *
     * @param interviewInterns the ArrayList of Entities.HiredIntern to add to the current list of HiredInterns.
     */
    public void updateInterviewInternList(ArrayList<InterviewIntern> interviewInterns) {
        this.interviewInternList = interviewInterns;
    }

    /**
     * This method updates the list of Entities.Project
     *
     * @param projects the ArrayList of Entities.Project to be added to the current list of projects.
     */
    public void updateProjectList(ArrayList<Project> projects) {
        projectList = projects;
        makeMonthToProject();//Fills monthToProject with the values needed.
        makeProjectToIntern();//Fills the projectToIntern with keys but no values yet.
    }

    private void makeProjectToIntern(){
        for (Project p: projectList){
            projectToInterns.put(p, new ArrayList<>());
        }
    }

    private void makeMonthToProject() {
        HashMap<Integer, ArrayList<Project>> monthToProject = new HashMap<>();
        ArrayList<Project> month1and2 = new ArrayList<>(projectList.subList(0, 1));
        ArrayList<Project> month3and4 = new ArrayList<>(projectList.subList(2, 4));
        ArrayList<Project> month5and6 = new ArrayList<>(projectList.subList(4, 4));
        monthToProject.put(1, month1and2);
        monthToProject.put(2, month1and2);
        monthToProject.put(3, month3and4);
        monthToProject.put(4, month3and4);
        monthToProject.put(5, month5and6);
        monthToProject.put(6, month5and6);
        this.monthToProject = monthToProject;
    }

    /**
     * This method gets only the names of all interns separated by "|".
     *
     * @return a String of all intern names
     */
    public String getHiredInternsNames() {
        StringBuilder res = new StringBuilder();
        for (Intern i : this.hiredInternList) {
            res.append(i.getInternName());
            res.append("|");
        }
        return res.toString();
    }


    /**
     * This method gets a String representation of all intern info.
     *
     * @return a String of intern information for every intern in internList
     */
    public String makeInterviewInternsToString() {
        StringBuilder res = new StringBuilder();
        for (Intern i : this.interviewInternList) {
            res.append(i.internToString());
        }
        return res.toString();
    }

    /**
     * This method gets a String representation of all Entities.HiredIntern info.
     *
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
     *
     * @param currentMonth an Integer representation for the current month in the current game.
     * @return a String of project information for all projects done in the given month.
     */
    public String makeProjectsToString(int currentMonth) {
        List<Project> monthlyProjList = this.monthToProject.get(currentMonth);
        StringBuilder res = new StringBuilder();
        for (Project proj : monthlyProjList) {
            res.append("\n");
            res.append(proj.projectToString());
        }
        return res.toString();
    }

    /**
     * This method gets only the name of projects in Entities.Project per a given month.
     *
     * @param currentMonth an Integer representation for the current month in the current game.
     * @return a String representation of project name for each project in Entities.Project for the given month.
     */
    public String getProjectNames(int currentMonth) {
        List<Project> monthlyProjList = this.monthToProject.get(currentMonth);
        StringBuilder res = new StringBuilder();
        for (Project proj : monthlyProjList) {
            res.append(proj.getName());
        }
        return res.toString();

    }

    /**
     * This method returns the list of all projects in the current month.
     *
     * @param currentMonth an Integer representation for the current month in the current game.
     * @return an ArrayList of all projects in Entities.Project for the given month.
     */
    public List<Project> getProjects(int currentMonth) {
        return this.monthToProject.get(currentMonth);
    }

    /**
     * This method returns a String representation of each project in Entities.Project and the assigned interns
     * from Entities.HiredInterns per a given month.
     *
     * @param currentMonth an Integer representation for the current month in the current game.
     * @return a String representing each project and the assigned interns to a project.
     */
    public String makeAssignmentToString(int currentMonth) {
        // get the projects for the month
        List<Project> currentProjects = this.monthToProject.get(currentMonth);
        StringBuilder res = new StringBuilder();
        for (Project project : currentProjects) {
            //append each project from the project list for the current month to res
            res.append(project.getName());
            //append the interns associated to that project to res
            res.append(" Interns in project: ");
            for (Intern i: projectToInterns.get(project)){
                res.append(i.internToString());
            }
        }
        return res.toString();

    }


    /**
     * This method displays the Entities.Intern current skill point in String format.
     *
     * @param currentMonth the given month that an intern skill is being checked.
     * @return a String representation of the Entities.Intern upgraded skills point.
     */
    public String makeUpgradeToString(int currentMonth) {
        //a method that takes the upgrade and returns a list of skills point the intern have now.
        List<Project> monthlyProjList = this.monthToProject.get(currentMonth);
        StringBuilder result = new StringBuilder();
        for (Project p : monthlyProjList) {
            result.append(p.getName());
            result.append("    Interns in project: ");
            ArrayList<HiredIntern> internsInProject = this.projectToInterns.get(p);
            for (HiredIntern h :internsInProject){
                result.append(h.internToString());
            }
        }
        return result.toString();
    }


    /**
     * This method updates a Player's name.
     *
     * @param name the name of a current Player.
     */
    public void updatePlayerName(String name) {
        this.playerName = name;
    }

    /**
     * This method gets the Player's name information.
     *
     * @return a String representation of the current Player's name.
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * This method updates the players choice to hire an intern or not.
     *
     * @param res a String representation of the current Player's choice.
     */
    public void updatePlayerResponse(String res) {
        this.playerResponse = res;
    }

    /**
     * This method gets the Player's response to hire an intern.
     *
     * @return a String representation of the current Player's response.
     */
    public String getPlayerResponse() {
        return this.playerResponse;
    }


    /**
     * This method hires an intern into the company.
     *
     * @param intern the Entities.Intern to be hired.
     */
    public void hireIntern(Intern intern) {
        HiredIntern hiredVersion = new HiredIntern(intern.getInternName(), intern.getInternAge(),
                intern.getInternSkills());
        this.hiredInternList.add(hiredVersion);
    }


    /**
     * This method returns if an Entities.Intern has been hired to the company.
     *
     * @param intern the Entities.Intern to check hired status.
     * @return true if the Entities.Intern has been hired or false otherwise.
     */
    public Boolean isHired(Intern intern) {
        return this.hiredInternList.contains((HiredIntern) intern);
    }


    public String choicesToString(InterviewIntern intern) {
        StringBuilder res = new StringBuilder();
        for (ResponseTree<ArrayList<String>> children: intern.getResponseTree().getChildren()){
            String qA = (String) children.getData().get(0);
            res.append(qA);
            res.append("\n");
        }
        return res.toString();
    }

    public String getInternChoiceResponse(String input, InterviewIntern intern) {
        //TODO: Update this method, for each intern in interviewInternList the input based on that intern
        StringBuilder res = new StringBuilder();
        for (ResponseTree<ArrayList<String>> children : intern.getResponseTree().getChildren()){
            if (children.getData().get(0).contains(input)) {
                res.append(children.getData().get(1));
            }
        }
        return res.toString();

    }


    /**
     * This method assigns a given Entities.Intern to a given Entities.Project.
     *
     * @param internName  the name of the Entities.Intern to be assigned.
     * @param projectName the name of the Entities.Project assigned to an Entities.Intern.
     * @return true if an assignment was successful and false if the given Entities.Intern is already assigned to
     * another project, or they are not hired.
     */
    public boolean assignInternToProject(String internName, String projectName) {
        //Should return true if assignment was successful.
        //Else, should poi return false if Intern is already been assigned to another project, or if they are not hired.
        HiredIntern beingAssigned = null;
        for (HiredIntern i : hiredInternList){
            if (i.getInternName().equals(internName)){
                beingAssigned = i;
            }
        }
        if (beingAssigned == null){
            return false;
        }
        for (Project p : this.projectList) {
            if ((p.getName().equals(projectName))) {
                ArrayList<HiredIntern> internsBefore = projectToInterns.get(p);
                internsBefore.add(beingAssigned);
                this.projectToInterns.put(p, internsBefore);
                return true;
            }
        }
        return false;
    }

    //TODO: Ask Jacob for javadoc.
    /**
     * Please finish javadoc
     * @param internName doc
     * @param currentMonth doc
     * @return doc
     */
    public boolean upgradeInternSkill(String internName, int currentMonth, String skillToUpgrade) {
        //Now only returns false if an intern doesn't exist.
        for (HiredIntern i : this.hiredInternList) {
            if (i.getInternName().equals(internName)){
                if (!checkSkillSpace(skillToUpgrade, internName)){
                    return false;
                }
                i.updateInternSkills(skillToUpgrade);
                i.updateUpgraded(currentMonth);
                return true;
            }
        }
        return false;
    }

    private boolean checkSkillSpace(String skillToUpgrade,String internName) {
        for (HiredIntern i : this.hiredInternList) {
            if (i.getInternName().equals(internName) & i.getInternSkills().containsKey(skillToUpgrade)){
                if (i.getInternSkills().get(skillToUpgrade) >= 100) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * This method removes a given Entities.HiredIntern from an assigned Entities.Project.
     *
     * @param internName  the name of the Entities.HiredIntern to be removed.
     * @param projectName the name of the Entities.Project to be removed.
     * @return true if this Entities.HiredIntern has been removed from an assignment or false if Intern is not in the
     * project yet or in another project, or if Intern is not hired.
     */
    public boolean removeInternFromProject(String internName, String projectName) {
        for (Project p : this.projectList) {
            if (p.getName().equals(projectName)) {
                for (HiredIntern i : this.hiredInternList){
                    if (i.getInternName().equals(internName)){
                        this.projectToInterns.get(p).remove(i);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * This method checks if all Entities.HiredIntern have been assigned to an Entities.Project per a
     * given month.
     *
     * @param currentMonth the Integer representation of the month being played.
     * @return true if all Entities.HiredIntern have been assigned and false otherwise.
     */
    public boolean internsAllAssigned(int currentMonth) {
        List<Project> monthlyProjList = this.monthToProject.get(currentMonth);
        for (Project p : monthlyProjList){
            if (p.getTeamSize() != projectToInterns.get(p).size()){
                return false;
            }
        }
        return true;
    }

    //TODO: ask jacob for javadoc
    /**
     * please finish javadoc
     * @param currentMonth doc
     * @return doc
     */
    public boolean internUpgraded(int currentMonth) {
        for(HiredIntern i: hiredInternList){
            if (i.getUpgradedIn() == currentMonth){
                return true;
            }
        }
        //a method that checks if an intern have been upgraded for this current month.
        // returns true when a intern has been upgraded for this month.
        return false;
    }

    /**
     * This method adds the chosen final project to the current game's Entities.Project list.
     *
     * @param finalProjForGame the final Entities.Project to be added.
     */
    public void updateFinalProject(ArrayList<Project> finalProjForGame) {
        this.projectList.addAll(finalProjForGame);
    }
}
