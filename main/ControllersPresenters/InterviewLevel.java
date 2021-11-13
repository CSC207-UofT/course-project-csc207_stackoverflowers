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
        if (Objects.equals(input, currentInterviewMaker.storePlayerChoice(input))) {
            return currentInterviewMaker.getChoiceOptions();
        }

        if (Objects.equals(input, "yes") || (Objects.equals(input, "no"))){
            return this.getHiringResponse(input);

        }
        if (levelEnded()){
            return getEndOfInterviewPrompt();
        }
        else{
            throw new Exception(Exceptions.INVALID_COMMAND);
        }
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
        String[] inputs = input.split("");
        String res = inputs[2];
        return currentInterviewMaker.internToHire(res);
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
