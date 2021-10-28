package ControllersPresenters;


import UseCases.MonthMaker;

import java.util.Objects;

public class MonthLevel {
    /* This class is the controller that manages all things that happen within a month.
    It is responsible for getting the first prompt of the month, and also displaying the choices available to the reader.
    * @param currentMonthMaker the current MonthMaker that corresponds to this MonthLevel that will be asked to do the specific stuff
    * @param currentPlayerInput the current input that this MonthLevel needs to decipher to know which method it should call next
     */
    private final MonthMaker currentMonthMaker;
    private final MonthPresenter currentMonthPresenter;
    private String currentPlayerInput;

    public MonthLevel(String playerInput){
        currentMonthMaker = new MonthMaker();
        currentMonthPresenter = new MonthPresenter();
        currentPlayerInput = playerInput;
    }

    public String getStartOfMonthPrompt(){
        return currentMonthMaker.startOfMonthPrompt();
    }

    public String getOutput(String input){
        // takes in the player's input and then uses the needed method to be used for the output, then asks MonthPresenter to use those stuff for a formatted output
        //TODO: finish implementing this body
        currentPlayerInput = input;
        String wanted = "";
        if (Objects.equals(currentPlayerInput, "check project info")){
            wanted = checkProjectInfo(); //the same should happen for checkInternInfo, assignInternToProject(), removeInternFromProject and other commands!! (If too many if statements, USE DESIGN PATTERN TO REFACTOR!!)
        }
        return formatOutput(wanted);
    }

    private String checkProjectInfo() {
        return currentMonthMaker.getProjectInfo();
    }

    private String checkInternInfo() {
        return "";
    }

    private String formatOutput(String sth) {
    //TODO: method formatOutput() takes in a input passes it to MonthMaker, then passes that result to MonthPresenter for formatted output
        return currentMonthPresenter.formatOutput(sth);
    }




}