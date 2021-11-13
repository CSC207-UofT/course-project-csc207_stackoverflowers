package ControllersPresenters;

import Entities.Exceptions;
import Entities.GamePrompts;
import UseCases.HRSystem;
import UseCases.InterviewMaker;

import java.util.Objects;

public class InterviewLevel extends Level{

     private final InterviewMaker currentInterviewMaker;

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
            //if the current InterviewIntern has not yet said their last response:
            if (! this.currentInterviewMaker.checkInternsLastResponse(input)){
                return GamePrompts.INTERN_RESPONSE_PROMPT +
                        currentInterviewMaker.displayInternChoiceResponse(input) +
                        GamePrompts.NEXT_CHOICE_PROMPT +
                        currentInterviewMaker.getChoiceOptions();
            }

            //if the current InterviewIntern has said their last response (i.e. at the end of the interview):
            if (this.currentInterviewMaker.checkInternsLastResponse(input)){
                return GamePrompts.HIRE_INTERN + this.currentInterviewMaker.internToHire(input);
            }

        }
        if (Objects.equals(input, "yes") || (Objects.equals(input, "no"))){
            //hired the intern/ or not
            //return "successfully/ don't hired intern"
            if (Objects.equals(input, "yes")){
                this.currentInterviewMaker.hireIntern();
                return GamePrompts.HIRED_INTERN;
            }
            if (Objects.equals(input, "no")){
                return GamePrompts.NOT_HIRED_INTERN;
            }

            //check if there are any interns left to interview haveInterviewsLeft()
            //if there are more interns to interview, update the current interviewing intern, prompt the player from the
            // next interviewee's output
            if (this.currentInterviewMaker.haveInterviewsLeft()){
                this.currentInterviewMaker.updateInterviewIntern();
                return GamePrompts.NEXT_INTERVIEW_INTERN_PROMPT + this.currentInterviewMaker.getInterviewInternInfo();
            }
            // if there are no more interns to interview, endLevel() and return hired successful or not
            if(! this.currentInterviewMaker.haveInterviewsLeft()){
                updateLevelStatus();
                return GamePrompts.CHOSEN_INTERNS_TO_HIRE + this.currentInterviewMaker.getHiredInternString();
            }

        }
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
