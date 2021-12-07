package UseCases;

import Entities.HiredIntern;
import Entities.Intern;
import Entities.Project;

import java.io.Serializable;
import java.util.*;

/**
 * A manager class managing all methods pertaining to project, as well as project assignment
 */
public class PMSystem implements Serializable {

    private final HRSystem currentHRSystem;

    private ArrayList<Project> projectList;

    private HashMap<Integer, ArrayList<Project>> monthToProject;
    // stores the month as a key and a list of projects for that month as a value

    private final HashMap<Project, ArrayList<HiredIntern>> projectToInterns;

    private int currentMonth;
    // stores a project as a key and a list of HiredInterns assigned to that project as a value

    /**
     * The constructor makes a new PMSystem for the current phase, and stores attributes of intern
     * (both Hired and Interview) and Project to use it when needed.
     */
    public PMSystem(HRSystem currentHRSystem) {
        this.projectList = new ArrayList<>();
        this.currentHRSystem = currentHRSystem;
        this.monthToProject = new HashMap<>();
        this.projectToInterns = new HashMap<>();
    }

    public void setCurrentMonth(int currentMonth) {
        this.currentMonth = currentMonth;
    }

    /**
     * This method updates the list of Entities.Project
     *
     * @param projects the ArrayList of Entities.Project to be added to the current list of projects.
     */
    public void updateProjectList(ArrayList<Project> projects) {
        projectList = projects;
        makeMonthToProject(); //Fills monthToProject with the values needed.
        makeProjectToIntern(); //Fills the projectToIntern with keys but no values yet.
    }

    /**
     * This method initializes the private variable, hashmap ProjectToIntern.
     */
    private void makeProjectToIntern(){
        for (Project p: projectList){
            projectToInterns.put(p, new ArrayList<>());
        }
    }

    /**
     * This method initializes and populates the private variable hashmap MonthToProject based on the projects
     * from the ArrayList projectList.
     */
    private void makeMonthToProject() {
        HashMap<Integer, ArrayList<Project>> monthToProject = new HashMap<>();
        ArrayList<Project> month1and2 = new ArrayList<>(projectList.subList(0, 2));
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
     * This method creates a String representation of all projects in Entities.Project per a given month level.
     *
     * @param currentMonth an Integer representation for the current month in the current game.
     * @return a String of project information for all projects done in the given month.
     */
    public String makeProjectsToString(int currentMonth) {
        setCurrentMonth(currentMonth);
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
            res.append("|");
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
     * This method returns the HashMap projectToInterns and the values stored in it.
     * @return the HashMap of the private variable projectToInterns.
     */
    public HashMap<Project, ArrayList<HiredIntern>> getProjectToInterns(){
        return this.projectToInterns;
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
            res.append("\nInterns in project: \n ");
            for (Intern i: projectToInterns.get(project)){
                res.append(i.internToString());
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
        HiredIntern beingAssigned = null;

        for (HiredIntern i : this.currentHRSystem.getHiredInternList()){
            if (i.getInternName().equals(internName)){
                beingAssigned = i;
                break;
            }
        }
        if (beingAssigned == null){
            return false;//if the intern is not hired, return false
        }
        if (checkAlreadyAssigned(beingAssigned)){
            return false;//If the intern has already been assigned to another project, return false
        }

        for (Project p : this.projectList) {
            // check if the interns in projectsToInterns is full according to each project's size
            if ((p.getName().equals(projectName)) & (this.projectToInterns.get(p).size() != p.getTeamSize()) ) {
                ArrayList<HiredIntern> internsBefore = projectToInterns.get(p);
                internsBefore.add(beingAssigned);
                this.projectToInterns.put(p, internsBefore);
                return true;
            }
        }
        return false;
    }

    /**
     * This is a helper method that checks if a certain intern has already been assigned to a project.
     * @param beingAssigned the HiredIntern that is being checked for assigning status.
     * @return true if this HiredIntern has been assigned from the hashmap projectToInterns and false otherwise.
     */
    private boolean checkAlreadyAssigned(HiredIntern beingAssigned) {
        for (Project proj: getProjects(currentMonth)){
            if (projectToInterns.get(proj).contains(beingAssigned)){
                return true;
            }
        }
        return false;
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
                for (HiredIntern i : this.currentHRSystem.getHiredInternList()){
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
     * This method adds the chosen final project to the current game's Entities.Project list.
     *
     * @param finalProjForGame the final Entities.Project to be added.
     */
    public void updateFinalProject(ArrayList<Project> finalProjForGame) {
        this.projectList.addAll(finalProjForGame);
    }

    /**
     * This method displays the Entities.Intern current skill point in String format.
     *
     * @param currentMonth the given month that an intern skill is being checked.
     * @return a String representation of the Entities.Intern upgraded skills point.
     */
    public String makeUpgradeToString(int currentMonth) {
        //a method that takes the upgrade and returns a list of skills point the intern have now.
        List<Project> monthlyProjList =  this.monthToProject.get(currentMonth);
        StringBuilder result = new StringBuilder();
        for (Project p : monthlyProjList) {
            result.append(p.getName());
            result.append("    Interns in project: ");
            ArrayList<HiredIntern> internsInProject = this.projectToInterns.get(p);
            for (HiredIntern h : internsInProject) {
                result.append(h.internToString());
            }
        }
        return result.toString();
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

    /**
     * This method returns a string representation of Entities.HiredInterns associated to a given project, Project.
     * @param project the desired project to obtain the names of HiredInterns from.
     * @return a string representation of the HiredInterns associated to the given project.
     */
    public String getInternNamesProject(Project project) {
        StringBuilder res = new StringBuilder();
        for (Intern i : this.projectToInterns.get(project)) {
            res.append(i.getInternName());
            res.append("|");
        }
        return res.toString();
    }

}
