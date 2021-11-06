package ControllersPresenters;

import Entities.Exceptions;
import UseCases.HRSystem;
import UseCases.MonthMaker;

import java.util.Objects;

public class MonthLevel extends Level {
    /* This class is the controller that manages all things that happen within a month.
    It is responsible for getting the first prompt of the month, and also displaying the choices available to the reader.
    * @param currentMonthMaker the current MonthMaker that corresponds to this MonthLevel that will be asked to do the specific stuff
     */
    private final MonthMaker currentMonthMaker;

    //constructor of this class
    public MonthLevel(int currentMonth, HRSystem currentHRSystem){
        currentMonthMaker = new MonthMaker(currentHRSystem, currentMonth);
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
        if (finishedAssigning()){
            return currentMonthMaker.confirmChoice();
        }

        if (Objects.equals(input, "check project info")){
            return checkProjectInfo(); //the same should happen for checkInternInfo, assignInternToProject(),
            // removeInternFromProject and other commands!! (If too many if statements, USE DESIGN PATTERN TO REFACTOR?)
        }
        if (Objects.equals(input, "check interns info")){
            return checkInternsInfo();
        }
        if (Objects.equals(input, "check assign")){
            return checkAssigningInfo();
        }
        if (input.contains("" +
                "assign intern to project")) {
            return assignInternToProject(input);
        }
        if (input.contains("remove intern from project")){
            return removeInternFromProject(input);
        }
        else{throw new Exception(Exceptions.INVALID_COMMAND);}
    }

    private String removeInternFromProject(String input) throws Exception {
        String[] inputs = input.split(" ");
        String intern = inputs[3];
        String project = inputs[4];
        return currentMonthMaker.removeInternFromProject(intern, project);
    }

    private String assignInternToProject(String input) throws Exception {
        String[] inputs = input.split(" ");
        String intern = inputs[3];
        String project = inputs[4];
        return currentMonthMaker.assignInternToProject(intern, project);
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
