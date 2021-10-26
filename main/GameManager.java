public class GameManager {
    /* this class is the controller class so it starts the game , waits for input etc
    for reference check JShell and JShellState in week 2 resources on quercus
     */
    /*
    try - catch?
     */
    private GameMaker currentGameMaker;
    public GameManager(){
        this.currentGameMaker = new GameMaker();
        //TODO: instantiate and store the MonthLevel, InterviewLevel and ReportLevel.
        //TODO: ask GameMaker to generate the Interns and Project needed for the current game.

    }
    public String firstPrompt(String playerInput) {
        return this.currentGameMaker.firstPrompt(playerInput);
    }
    // TODO: method that takes in the player's input and returns the output in the right phase
}
