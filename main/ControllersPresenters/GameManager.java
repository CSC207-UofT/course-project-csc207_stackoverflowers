package ControllersPresenters;

import Entities.Exceptions;
import UseCases.GameGenerators;
import UseCases.GameMaker;

import java.util.Objects;

public class GameManager {
    /* this class is the controller class for the entire game. It starts the game , waits for input etc
    for reference check JShell and JShellState in week 2 resources on quercus
     */
    /*
    try - catch?
     */
    private GameMaker currentGameMaker;
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
        GameGenerators currentGameGenerators = new GameGenerators(currentGameMaker.getCurrentHRSystem(),
                currentGameMaker.getCurrentPMSystem());
        this.currentStatus = statusOfGame.Start;
        this.currentLevel = new StartLevel(currentGameMaker);
        //ask GameMaker to generate the Interns and Projects needed for the current game.
        currentGameGenerators.generateInterns(10);
        currentGameGenerators.generateProjects(4);
        currentGameGenerators.generateFinalProject();
        isRunning = true;
    }

    public String getOutput(String playerInput) throws Exception {
        //This method checks the current status  of the game, and then asks for the desired
        // output from that phase.
        if (GameMaker.getUniversalCommands().contains(playerInput.split(" ")[0])) {
            return universalCommand(playerInput);
        }
        statusOfGame statusBefore = currentStatus;
        updateStatus();
        switch (statusBefore) {
            case Start:
            case Interview:
            case Month:
            case FinalMonth:
            case Report:
            case FinalReport:
                return currentLevel.getOutputString(playerInput);
            case End:
                isRunning = false; //return the last prompt and end the game.
                return endingPrompt();
        }
        throw new Exception(Exceptions.UNIVERSAL_COMMAND_NOT_FOUND);
    }

    private String endingPrompt() {
        return this.currentGameMaker.endPrompt();
    }

    private String universalCommand(String playerInput) throws Exception {
        if (Objects.equals(playerInput.strip(), "save")) {
            return currentGameMaker.save(currentMonth, currentStatus.toString());
        }
        if (playerInput.strip().equals("quit")) {
            statusOfGame formerStatus = currentStatus;
            currentStatus = statusOfGame.End;
            return currentGameMaker.quit(currentMonth, formerStatus.toString());
        }
        if (Objects.equals(playerInput.split(" ")[0], "load")) {
            if (currentStatus == statusOfGame.Start) {
                //Only load a new game if the current input has just started.
                try {
                    currentGameMaker = currentGameMaker.load(playerInput.split(" ")[1]);
                }catch (Exception e){
                    throw new Exception("Can't find file to load");
                }
                currentMonth = currentGameMaker.getCurrentMonth();
                currentStatus = statusOfGame.valueOf(currentGameMaker.getCurrentStatus());
                return getOutput(currentGameMaker.getCurrentHRSystem().getPlayerName());
            } else {
                throw new Exception(Exceptions.NOT_LOADING_AT_START);
            }
        }
        throw new Exception(Exceptions.UNIVERSAL_COMMAND_NOT_FOUND);
    }

    private void updateStatus() {
        if (currentLevel.levelEnded()) {
            switch (currentStatus) {
                case Start:
                    currentStatus = statusOfGame.Interview;
                    currentLevel = new InterviewLevel(currentGameMaker.getCurrentHRSystem());
                    break;
                case Interview:
                    currentStatus = statusOfGame.Month;
                    currentLevel = new MonthLevel(currentMonth, currentGameMaker.getCurrentHRSystem(),
                            currentGameMaker.getCurrentPMSystem());
                    break;
                case Month:
                    currentStatus = statusOfGame.Report;
                    currentLevel = new ReportLevel(currentMonth, currentGameMaker.getCurrentHRSystem(),
                            currentGameMaker.getCurrentPMSystem());
                    break;
                case Report:
                    currentMonth++;
                    if (currentMonth == 2 | currentMonth == 4) {
                        //if the current month is now 2 or 4, we now will display the projectReport,
                        // with no monthLevel in between
                        currentLevel = new ReportLevel(currentMonth, currentGameMaker.getCurrentHRSystem(),
                                currentGameMaker.getCurrentPMSystem());
                        break;
                    }
                    if (currentMonth == 3){
                        currentStatus = statusOfGame.Month;
                    } else {
                        currentStatus = statusOfGame.FinalMonth;
                    }
                    currentLevel = new MonthLevel(currentMonth, currentGameMaker.getCurrentHRSystem(),
                            currentGameMaker.getCurrentPMSystem());
                    break;
                case FinalMonth:
                    currentStatus = statusOfGame.FinalReport;
                    currentLevel = new ReportLevel(currentMonth, currentGameMaker.getCurrentHRSystem(),
                            currentGameMaker.getCurrentPMSystem());
                    break;
                case FinalReport:
                    currentStatus = statusOfGame.End;
                    break;
            }
        }
    }
}
