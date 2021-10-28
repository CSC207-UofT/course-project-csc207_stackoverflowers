package ControllersPresenters;

import UseCases.GameMaker;
import UseCases.ReportMaker;

public class GameManager {
    /* this class is the controller class so it starts the game , waits for input etc
    for reference check JShell and JShellState in week 2 resources on quercus
     */
    /*
    try - catch?
     */
    private GameMaker currentGameMaker;
    private statusOfGame currentStatus;
    private InterviewLevel currentInterviewLevel;
    private MonthLevel currentMonthLevel;
    private ReportLevel currentReportLevel;
    private int currentMonth = 1;
    enum statusOfGame {Start, Month, Report, FinalMonth, FinalReport, End}

    public GameManager(){
        this.currentGameMaker = new GameMaker();
        this.currentStatus = statusOfGame.Start;
        this.currentInterviewLevel = new InterviewLevel();
        this.currentMonthLevel = new MonthLevel();
        this.currentReportLevel = new ReportLevel();

        //TODO: instantiate and store the MonthLevel, InterviewLevel and ReportLevel.
        //TODO: ask UseCases.GameMaker to generate the Interns and Entities.Project needed for the current game.

    }
    public String firstPrompt(String playerInput) {
        return this.currentGameMaker.firstPrompt(playerInput);
    }
    public String getOutput(String playerInput){
        //TODO: finish implementing this method.
        // checks the currentStatus of the game, and ask corresponding GameMaker/Levels for the output.
        //TODO: this megthod should also know which month we are in.
        switch (currentStatus){
            case Start:
                return firstPrompt(playerInput);}
        updateStatus();
    }

    private void updateStatus() {
        if (currentMonthLevel.monthEnded())
    }
    // TODO: method that takes in the player's input and returns the output in the right phase
}
