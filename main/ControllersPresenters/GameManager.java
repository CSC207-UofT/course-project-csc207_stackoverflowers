package ControllersPresenters;

import Entities.Exceptions;
import UseCases.GameMaker;

import java.io.FileNotFoundException;

public class GameManager {
    /* this class is the controller class for the entire game. It starts the game , waits for input etc
    for reference check JShell and JShellState in week 2 resources on quercus
     */
    /*
    try - catch?
     */
    private final GameMaker currentGameMaker;
    private statusOfGame currentStatus;
    private Level currentLevel;
    private int currentMonth = 1;
    private static boolean isRunning;

    public static boolean isRunning() {
        return isRunning;
    }

    enum statusOfGame {Start, Interview, Month, Report, FinalMonth, FinalReport, End}

    public GameManager() {
        this.currentGameMaker = new GameMaker();
        this.currentStatus = statusOfGame.Start;
        //ask GameMaker to generate the Interns and Projects needed for the current game.
        try {this.currentGameMaker.generateInterns(10);}
        //TODO: catch the exception and do something with it
        // since this is the highest in the hierarchy? Is this what I should do?
        catch(FileNotFoundException e){
            System.out.println(Exceptions.INTERNS_FILE_NOT_FOUND);
            //Is this how we do the try catch?
        }
        currentGameMaker.generateProjects();
        isRunning = true; // So that the game is running
    }

    public String getOutput(String playerInput){
        //This method checks the current status  of the game, and then asks for the desired
        // output from that phase.
        if (currentGameMaker.getCommands().contains(playerInput)){
            return currentGameMaker.universalCommand(playerInput);
        }
        statusOfGame statusBefore = currentStatus;
        updateStatus();
        switch (statusBefore) {
            case Start:
                return firstPrompt(playerInput);
            case Interview:
                return ((InterviewLevel) currentLevel).getInterviewOutput();
            case Month:
            case FinalMonth:
                return ((MonthLevel) currentLevel).getOutputString(playerInput);
            case Report:
            case FinalReport:
                return ((ReportLevel) currentLevel).getReport();

            case End:
                isRunning = false; //return the last prompt and end the game.
                return endingPrompt();

        }
        return "Not implemented yet";
    }
    public String firstPrompt(String playerInput) {
        updateStatus();//Prepare by changing the status of the game into the next level.(From start to interview)
        return this.currentGameMaker.firstPrompt(playerInput);
    }

    private String endingPrompt() {
        return this.currentGameMaker.endPrompt();
    }
    //TODO: do something with these breaks, they aren't looking very good...
    private void updateStatus() {
        if (currentStatus == statusOfGame.Start){
                currentStatus = statusOfGame.Interview;
                currentLevel = new InterviewLevel(currentGameMaker.getCurrentHRSystem());
        }
        if (currentLevel.levelEnded()){
            switch (currentStatus){
                case Interview:
                    currentStatus = statusOfGame.Month;
                    currentLevel = new MonthLevel(currentMonth, currentGameMaker.getCurrentHRSystem());
                    break;
                case Month:
                    currentStatus = statusOfGame.Report;
                    currentLevel = new ReportLevel(currentMonth, currentGameMaker.getCurrentHRSystem());
                    break;
                case Report:
                    currentMonth++;
                    if (currentMonth < 4){
                        currentStatus = statusOfGame.Month;
                    }
                    else{
                        currentStatus = statusOfGame.FinalMonth;
                    }
                    break;
                case FinalMonth:
                    currentStatus = statusOfGame.FinalReport;
                    break;
                case FinalReport:
                    currentStatus = statusOfGame.End;
                    break;
            }
        }
    }
}
