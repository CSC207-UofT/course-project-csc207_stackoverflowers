package ControllersPresenters;

import UseCases.InterviewMaker;
// import ControllersPresenters.InterviewPresenter;

public class InterviewLevel extends Level{

    //TODO: Declare private instance attributes for InterviewMaker, InterviewPresenter and playerInput
    /*
     * private InterviewMaker currentInterviewMaker;
     * private InterviewPresenter currentInterviewPresenter;
     * private String playerInput;
     */

    //TODO: Initialize the constructor
    /**
     * The constructor makes a new InterviewLevel for the current phase
     */
    public void InterviewMaker(){

    }

    //TODO: Implement method getStartOfInterviewPrompt
    public void getStartOfInterviewPrompt(){

    }

    //TODO: Implement method getChoiceOptions
    /**
     * Get the Interns choiceOptions from UseCases.InterviewMaker
     */
    public void getChoiceOptions(){

    }


    //TODO: Implement method getPlayerChoice
    /**
     * Get the player's choice from storePlayerChoice in UseCases.InterviewMaker
     */
    public void getPlayerChoice(){

    }


    //TODO: Implement method getInternChoiceResponse
    /**
     * Get the Interns choice response from internChoiceResponse in UseCases.InterviewMaker
     */
    public void getInternChoiceResponse(){

    }


    //TODO: Implement method getHiringResponse
    /**
     * Get the player's choice to hire this intern as well aß the hiring prompts
     * from internToHire in UseCases.InterviewMaker
     */
    public void getHiringResponse(){

    }


    //TODO: Implement method formatInterview
    /**
     * This method takes in an input, passes it to InterviewMaker, then to InterviewPresenter for a formatted output.
     */
    public void formatInterview(){

    }


    //TODO: Implement method getInterviewOutput
    /**
     * Takes in the player's input and uses the necessary methods defined above needed for a specific output in each
     * step of the interview process, asking InterviewPresenter to format this output using the formatInterview method.
     */
    public String getInterviewOutput(){
        return "The method getInterviewOutput is not implemented yet.";
    }


    //TODO: Implement method getEndOfInterviewPrompt
    public void getEndOfInterviewPrompt() {
    }

    //TODO: Implement method updateLevelStatus
    //This method will update the inherited private instance levelEnded so that we can
    // check when the level ended.
    public void updateLevelStatus(){
        endLevel();//Method in Level that can be used since we can't access the levelStatus as an private instance.
    }
}
