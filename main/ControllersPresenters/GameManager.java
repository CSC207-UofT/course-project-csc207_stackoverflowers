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
    private Level currentLevel;
    private int currentMonth = 1;
    enum statusOfGame {Start, Month, Report, FinalMonth, FinalReport, End}

    public GameManager(){
        this.currentGameMaker = new GameMaker();
        this.currentStatus = statusOfGame.Start;
        this.currentLevel = new Level();
        //TODO: ask UseCases.GameMaker to generate the Interns and Projects needed for the current game.

    }
    public String firstPrompt(String playerInput) {
        return this.currentGameMaker.firstPrompt(playerInput);
    }
    public String getOutput(String playerInput){
        //TODO: finish implementing this method.
        // checks the currentStatus of the game, and ask corresponding GameMaker/Levels for the output.
        //TODO: this method should also know which month we are in.
        switch (currentStatus){
            case Start:
                return firstPrompt(playerInput);
            case Month:
                return ((MonthLevel)currentLevel).getOutputString(playerInput);}
        updateStatus();
        return "NOT FINISHED IMPLEMENTING YET";
    }

    private void updateStatus() {
        if (currentLevel.levelEnded()){
            switch (currentStatus){
                case Month:
                    currentMonth ++;
                    currentStatus = statusOfGame.Report;
                    break;
                case FinalMonth:
                    currentStatus = statusOfGame.FinalReport;
                //TODO: finish implementing this method
            }
        }
    }
    // TODO: method that takes in the player's input and returns the output in the right phase
}
