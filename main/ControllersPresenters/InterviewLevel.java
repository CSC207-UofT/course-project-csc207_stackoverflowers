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
    @Override
    public String getOutputString(String input) throws Exception{
        if (levelStarted()){
            getIntoLevel();
            return getStartOfInterviewPrompt();
        }
        if (levelEnded()){
            return getEndOfInterviewPrompt();
        }
        if (currentInterviewMaker.getChoiceOptions().contains(input) & (! input.isBlank())) {
            //if the current InterviewIntern has said their last response (i.e. at the end of the interview):
            StringBuilder res = new StringBuilder();
            String internsResponse = currentInterviewMaker.displayInternChoiceResponse(input);
            res.append(GamePrompts.INTERN_RESPONSE_PROMPT);
            res.append(internsResponse);
            currentInterviewMaker.updateInternTree(0);
            if (!this.currentInterviewMaker.checkInternsLastResponse()){
                res.append(GamePrompts.NEXT_CHOICE_PROMPT);
                res.append(currentInterviewMaker.getChoiceOptions());
            }else{
                res.append(GamePrompts.HIRE_INTERN);
            }
            return res.toString();
        }
        if (Objects.equals(input, "yes") || (Objects.equals(input, "no")) & (! input.isBlank())){
            //hired the intern/ or not
            //return "successfully/ don't hired intern"
            if (Objects.equals(input, "yes")){
                this.currentInterviewMaker.hireIntern();
                //check if there are any interns left to interview haveInterviewsLeft()
                //if there are more interns to interview, update the current interviewing intern, prompt the player from the
                // next interviewee's output
                if (this.currentInterviewMaker.haveInterviewsLeft()){
                    this.currentInterviewMaker.updateInterviewIntern();
                    return GamePrompts.HIRED_INTERN + GamePrompts.NEXT_INTERVIEW_INTERN_PROMPT +
                            this.currentInterviewMaker.getInterviewInternInfo();
                }
                // if there are no more interns to interview, endLevel() and return hired successful or not and the
                // names of all interns they have hired in this interview period.
                if(! this.currentInterviewMaker.haveInterviewsLeft()){
                    updateLevelStatus();
                    return GamePrompts.HIRED_INTERN + GamePrompts.CHOSEN_INTERNS_TO_HIRE +
                            this.currentInterviewMaker.getHiredInternString();
                }
            }
            if (Objects.equals(input, "no")){
                //check if there are any interns left to interview haveInterviewsLeft()
                //if there are more interns to interview, update the current interviewing intern, prompt the player from the
                // next interviewee's output
                if (this.currentInterviewMaker.haveInterviewsLeft()){
                    this.currentInterviewMaker.updateInterviewIntern();
                    return GamePrompts.NOT_HIRED_INTERN + GamePrompts.NEXT_INTERVIEW_INTERN_PROMPT +
                            this.currentInterviewMaker.getInterviewInternInfo();
                }
                // if there are no more interns to interview, endLevel() and return hired successful or not and the
                // names of all interns they have hired in this interview period.
                if(! this.currentInterviewMaker.haveInterviewsLeft()){
                    updateLevelStatus();
                    return GamePrompts.NOT_HIRED_INTERN + GamePrompts.CHOSEN_INTERNS_TO_HIRE +
                            this.currentInterviewMaker.getHiredInternString();
                }
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
