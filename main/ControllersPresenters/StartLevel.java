package ControllersPresenters;

import UseCases.GameMaker;

public class StartLevel extends Level{
    private GameMaker currentGameMaker;

    public StartLevel(GameMaker currentGameMaker ){
        this.currentGameMaker = currentGameMaker;
    }
    @Override
    public String getOutputString(String input) throws Exception {
        try{
            endLevel(); //end the level if everything is fine
            return currentGameMaker.firstPrompt(input);
        }
        catch(Exception e){
            getIntoLevel(); //if the name is not fine, stay in level.
            throw e; //still throw the same exception so that the message will be displayed by SPhase.
        }
    }

}
