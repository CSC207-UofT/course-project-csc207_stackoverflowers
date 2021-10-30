package ControllersPresenters;

import UseCases.GameMaker;

import java.io.FileNotFoundException;

public class GameManager {
    /* this class is the controller class so it starts the game , waits for input etc
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
        catch(FileNotFoundException e){
            System.out.println("There is no file found to generate Interns from.");
            //Is this how we do the try catch?
        }
        //TODO: catch the exception since this is the highest in the hierarchy
        currentGameMaker.generateProjects();
        isRunning = true; // So that the game is running
    }

    public String getOutput(String playerInput){
        //This method checks the current status  of the game, and then asks for the desired
        // output from that phase.
        switch (currentStatus) {
            case Start -> {
                updateStatus();
                return firstPrompt(playerInput);
            }
            case Interview -> {
                updateStatus();
                return ((InterviewLevel) currentLevel).getInterviewOutput();
            }
            case Month, FinalMonth -> {
                updateStatus();
                return ((MonthLevel) currentLevel).getOutputString(playerInput);
            }
            case Report, FinalReport -> {
                updateStatus();
                return ((ReportLevel) currentLevel).getReport();
            }
            case End -> {
                isRunning = false; //return the last prompt and end the game.
                return endingPrompt();
            }
        }
        return "NOT FINISHED IMPLEMENTING YET";
    }
    public String firstPrompt(String playerInput) {
        return this.currentGameMaker.firstPrompt(playerInput);
    }

    private String endingPrompt() {
        return this.currentGameMaker.endPrompt();

    }


    private void updateStatus() {
        if (currentStatus == statusOfGame.Start){
                currentStatus = statusOfGame.Interview;
        }
        if (currentLevel.levelEnded()){
            switch (currentStatus){
                case Start:
                    currentStatus = statusOfGame.Interview;
                    currentLevel = new InterviewLevel();
                case Interview:
                    currentStatus = statusOfGame.Month;
                    currentLevel = new MonthLevel(currentMonth);
                case Month:
                    currentStatus = statusOfGame.Report;
                    currentLevel = new ReportLevel(currentMonth);
                    break;
                case Report:
                    currentMonth++;
                    if (currentMonth < 4){
                        currentStatus = statusOfGame.Month;
                    }
                    else{
                        currentStatus = statusOfGame.FinalMonth;
                    }
                case FinalMonth:
                    currentStatus = statusOfGame.FinalReport;
                case FinalReport:
                    currentStatus = statusOfGame.End;
            }
        }
    }
}
