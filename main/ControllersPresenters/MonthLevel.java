package ControllersPresenters;


import Entities.GamePrompts;
import UseCases.MonthMaker;

import java.util.Objects;

public class MonthLevel extends Level {
    /* This class is the controller that manages all things that happen within a month.
    It is responsible for getting the first prompt of the month, and also displaying the choices available to the reader.
    * @param currentMonthMaker the current MonthMaker that corresponds to this MonthLevel that will be asked to do the specific stuff
     */
    private final MonthMaker currentMonthMaker;
    private final MonthPresenter currentMonthPresenter;
    private int currentMonth = 1;
    private final GamePrompts prompts = new GamePrompts();

    //constructor of this class
    public MonthLevel(int currentMonth){
        currentMonthMaker = new MonthMaker();
        currentMonthPresenter = new MonthPresenter();
        this.currentMonth = currentMonth;
    }

    public String getStartOfMonthPrompt(){
        return currentMonthMaker.startOfMonthPrompt();
    }

    public String getOutputString(String input){
        // takes in the player's input and then uses the needed method to be used for the output,
        // then asks MonthPresenter to use those stuff for a formatted output
        //TODO: finish implementing this body
        if (levelStarted()){
            getIntoLevel();
            return getStartOfMonthPrompt();
        }
        String wanted = "";
        if (Objects.equals(input, "check project info")){
            wanted = checkProjectInfo(); //the same should happen for checkInternInfo, assignInternToProject(),
            // removeInternFromProject and other commands!! (If too many if statements, USE DESIGN PATTERN TO REFACTOR?)
        }
        if (input == "check interns info"){
            wanted = checkInternInfo();
        }
        if (input.contains("assign intern to project")) {
            wanted = assignInternToProject(input);
        }
        if (input.contains("remove intern from project")){
            wanted = removeInternFromProject(input);
        }
        if (input == "confirm all decisions"){
            //TODO: MODIFY so that the level ends when we wnat
            endLevel();
        }
        //TODO: adding exceptions if we get the wrong command.
        return formatOutput(wanted);
    }

    private String removeInternFromProject(String input) {
        //TODO: same as assign
        String intern = "";
        String project = "";
        boolean success = false;
        success = currentMonthMaker.removeInternFromProject(intern, project);
        //TODO: Shouldn't let controller MonthLevel be able to reach game prompts! Refactor so that MonthMaker returns
        // the message instead.
        if (success){return prompts.INTERN_ASSIGNING_SUCCESS;}
        return prompts.INTERN_ASSIGNING_FAILURE;
    }

    private String assignInternToProject(String input) {
        //TODO: parse the string, remove command, get the intern and the project Name
        String[] inputs = input.split(" ");
        String intern = inputs[3];
        String project = inputs[4];
        boolean success = false;
        success = currentMonthMaker.assignInternToProject(intern, project);
        if (success){return prompts.INTERN_REMOVING_SUCCESS;}
        return prompts.INTERN_REMOVING_FAILURE;

    }

    private String checkProjectInfo() {
        return currentMonthMaker.getProjectInfo();
    }

    private String checkInternInfo() {
        return currentMonthMaker.getInternInfo();
    }

    private String formatOutput(String wanted) {
        //TODO: method formatOutput() takes in a input passes it to MonthMaker, then passes that result to
        // MonthPresenter for formatted output
        return currentMonthPresenter.displayOutput(wanted);
    }



}
