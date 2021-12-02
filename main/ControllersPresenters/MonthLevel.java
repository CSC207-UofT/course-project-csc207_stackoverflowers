package ControllersPresenters;

import Entities.Exceptions;
import UseCases.HRSystem;
import UseCases.PMSystem;
import UseCases.MonthMaker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MonthLevel extends Level {
    /* This class is the controller that manages all things that happen within a month.
    It is responsible for getting the first prompt of the month, and also displaying the choices available to the reader.
    * @param currentMonthMaker the current MonthMaker that corresponds to this MonthLevel that will be asked to do the specific stuff
     */
    private final MonthMaker currentMonthMaker;

    //constructor of this class
    public MonthLevel(int currentMonth, HRSystem currentHRSystem, PMSystem currentPMSystem){
        currentMonthMaker = new MonthMaker(currentHRSystem,currentPMSystem, currentMonth);
    }

    public String getOutputString(String input) throws Exception {
        // takes in the player's input and then uses the needed method to be used for the output,
        // then asks MonthPresenter to use those stuff for a formatted output.
        if (levelStarted()){
            getIntoLevel();
            return getStartOfMonthPrompt();
        }
        if (Objects.equals(input, "confirm all decisions")){
            endLevel();
            return endPrompt();
        }
        if (Objects.equals(input.strip(), "check project info")){
            return checkProjectInfo(); //the same should happen for checkInternInfo, assignInternToProject(),
            // removeInternFromProject and other commands!! (If too many if statements, USE DESIGN PATTERN TO REFACTOR?)
        }
        if (Objects.equals(input.strip(), "check interns info")){
            return checkInternsInfo();
        }
        if (Objects.equals(input.strip(), "check assign")){
            return checkAssigningInfo();
        }
        if (input.contains("assign intern to project")) {
            return assignInternToProject(input);
        }
        if (input.contains("remove intern from project")){
            return removeInternFromProject(input);
        }
        if (finishedAssigning()){
            return currentMonthMaker.confirmChoice();
        }
        else{throw new Exception(Exceptions.INVALID_COMMAND);}
    }

    private String removeInternFromProject(String input) throws Exception {
        if (!input.startsWith("remove intern from project")){
            throw new Exception(Exceptions.INVALID_COMMAND);
        }
        ArrayList<String> internAndProject = parseForAssigning(input);
        return currentMonthMaker.removeInternFromProject(internAndProject.get(0), internAndProject.get(1) );
    }

    private String assignInternToProject(String input) throws Exception {
        if (!input.startsWith("assign intern to project")){
            throw new Exception(Exceptions.INVALID_COMMAND);
        }
        ArrayList<String> internAndProject = parseForAssigning(input);
        return currentMonthMaker.assignInternToProject(internAndProject.get(0), internAndProject.get(1));
    }

    private ArrayList<String> parseForAssigning(String input) throws Exception {
        //helper method that parses the string so that it returns the intern and project involved.
        List<String> inputs = Arrays.asList((input.split(" ")));
        if (inputs.size()< 6 || inputs.size() > 10){
            throw new Exception(Exceptions.INVALID_COMMAND);
        }
        String intern = inputs.get(4);
        String project = String.join(" ", inputs.subList(5, inputs.size()));
        ArrayList<String> internAndProject  = new ArrayList<String>();
        internAndProject.add(intern);
        internAndProject.add(project);
        return internAndProject;
    }

    private String getStartOfMonthPrompt(){
            return currentMonthMaker.startOfMonthPrompt();
    }

    private String endPrompt(){
            return currentMonthMaker.endOfMonthPrompt();
        }

    private boolean finishedAssigning(){
        return currentMonthMaker.checkAllInternsAssigned();
    }

    private String checkProjectInfo() {
        return currentMonthMaker.getProjectInfo();
    }

    private String checkInternsInfo() {
        return currentMonthMaker.getInternsInfo();
    }

    private String checkAssigningInfo() {
        return currentMonthMaker.getAssigningInfo();
    }


}
