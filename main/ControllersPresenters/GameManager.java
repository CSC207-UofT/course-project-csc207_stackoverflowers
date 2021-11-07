package ControllersPresenters;

import Entities.Exceptions;
import UseCases.GameGenerators;
import UseCases.GameMaker;

import java.util.ArrayList;

public class GameManager {
    /* this class is the controller class for the entire game. It starts the game , waits for input etc
    for reference check JShell and JShellState in week 2 resources on quercus
     */
    /*
    try - catch?
     */
    private GameMaker currentGameMaker;
    private GameGenerators currentGameGenerators;
    private statusOfGame currentStatus;
    private Level currentLevel;
    private int currentMonth = 1;
    private static boolean isRunning;

    enum statusOfGame {Start, Interview, Month, Report, FinalMonth, FinalReport, End}

    public static boolean isRunning() {
        return isRunning;
    }

    public GameManager() throws Exception {
        this.currentGameMaker = new GameMaker();
        this.currentGameGenerators = new GameGenerators();
        this.currentStatus = statusOfGame.Start;
        //ask GameMaker to generate the Interns and Projects needed for the current game.
        try {
            currentGameGenerators.generateInterns(10);
            currentGameGenerators.generateProjects(4);
        } finally {
            isRunning = true;

        }
    }

    public String getOutput(String playerInput) throws Exception {
        //This method checks the current status  of the game, and then asks for the desired
        // output from that phase.
        if (GameMaker.getUniversalCommands().contains(playerInput)){
            return universalCommand(playerInput);
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
        throw new Exception(Exceptions.UNIVERSAL_COMMAND_NOT_FOUND);
    }

    public String firstPrompt(String playerInput) {
        updateStatus();
        //Prepare by changing the status of the game into the next level.(From start to interview)
        return this.currentGameMaker.firstPrompt(playerInput);
    }

    private String endingPrompt() {
        return this.currentGameMaker.endPrompt();
    }

    private String universalCommand(String playerInput) throws Exception {
    if(playerInput =="save") {
        return currentGameMaker.save();
    }if(playerInput =="quit"){
        currentStatus = statusOfGame.End;
        return currentGameMaker.quit();
    }if(playerInput.contains("load")){
    if (currentStatus == statusOfGame.Start) {
            String[] loads = playerInput.split(" ");
            currentGameMaker = currentGameMaker.load(loads[1]);
        } else {
            throw new Exception("Load only permitted at start of the game");
        }
    }
    throw new Exception(Exceptions.UNIVERSAL_COMMAND_NOT_FOUND);
    }

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
