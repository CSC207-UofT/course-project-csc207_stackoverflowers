package ControllersPresenters;

import Entities.Exceptions;
import Entities.InterviewIntern;
import UseCases.HRSystem;
import UseCases.InterviewMaker;

import java.util.Objects;

public class InterviewLevel extends Level{

     private final InterviewMaker currentInterviewMaker;
     private String playerInput;

    /**
     * The constructor makes a new InterviewLevel for the current phase, and stores an
     * InterviewMaker to use it when needed.
     * @param currentHRSystem the HRsystem being used by InterviewMaker.
     */
    public InterviewLevel(HRSystem currentHRSystem){
        this.currentInterviewMaker = new InterviewMaker(currentHRSystem);
    }

    /**
     * Takes in the player's input and uses the necessary methods defined above needed for a specific output in each
     * step of the interview process, asking InterviewPresenter to format this output using the formatInterview method.
     */
    public String getInterviewOutput(String input) throws Exception{
        if (levelStarted()){
            getIntoLevel();
            return getStartOfInterviewPrompt();
        }
        if (levelEnded()){
            return getEndOfInterviewPrompt();
        }
        if (currentInterviewMaker.getChoiceOptions().contains(input)) {
            //check if this is the last thing the intern says
            //if it's not:
                // return the corresponding interviewee's next response and next choice options to the player
            //if it is:
                //return the interviewee's final response, and prompt player to make hiring decision.
                //this.getHiringResponse(input);
        }
        if (Objects.equals(input, "yes") || (Objects.equals(input, "no"))){
            //hired the intern/ or not
            //return "successfully/ don't hired intern"
            //check if there are any interns left to interview haveInterviewsLeft()
            // if yes: update the current interviewing intern, then also add the next interviewee's next sentenct to output
            // if no: endLevel() and return hired successful or not
            currentInterviewMaker.hireIntern();//the actual hiring

        }
        //If you never change the level status how will it EVER get to end. huh????
        throw new Exception(Exceptions.INVALID_COMMAND);
    }

    /**
     * This returns a prompt from InterviewMaker indicating the start of the interview level.
     * @return a String representation of the starting prompt.
     */
    public String getStartOfInterviewPrompt(){
        return currentInterviewMaker.startOfInterviewPrompt();
    }

    /**
     * Get the Interns choiceOptions from UseCases.InterviewMaker
     */
    public Object storePlayerChoice(String input) throws Exception{
        String[] inputs = input.split("");
        String choice = inputs[1];
        return currentInterviewMaker.storePlayerChoice(choice);
    }


    /**
     * Get the player's choice to hire this intern as well as the hiring prompts
     * from internToHire in UseCases.InterviewMaker
     */
    public String getHiringResponse(String input){
        return currentInterviewMaker.internToHire(input);
    }



    /**
     * This returns a prompt from InterviewMaker indicating the end of the interview level.
     * @return a String representation of the ending prompt.
     */
    public String getEndOfInterviewPrompt() {
        return currentInterviewMaker.endOfInterviewPrompt();
    }


    /**
     * This method updates the inherited private instance levelEnded to check when the interview level has ended.
     */
    public void updateLevelStatus(){
        this.endLevel();
    }

}
