package ControllersPresenters;

import Entities.Exceptions;
import Entities.GamePrompts;
import Entities.HiredIntern;
import UseCases.HRSystem;
import UseCases.InterviewMaker;

import java.util.Locale;
import java.util.Objects;

public class InterviewLevel extends Level{

    private final InterviewMaker currentInterviewMaker;

    /**
     * The constructor makes a new InterviewLevel for the current phase, and stores an
     * InterviewMaker to use it when needed.
     * @param currentHRSystem the HRSystem being used by InterviewMaker.
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
        if (Objects.equals(input.toLowerCase(), "a") || Objects.equals(input.toLowerCase(), "b") & (! input.isBlank())){
            // replace with choice 'a' or 'b'
            //if the current InterviewIntern has said their last response (i.e. at the end of the interview):
            try {
                StringBuilder res = new StringBuilder();
                String internsResponse = currentInterviewMaker.displayInternChoiceResponse(input);
                res.append(GamePrompts.INTERN_RESPONSE_PROMPT);
                res.append(internsResponse);
                currentInterviewMaker.updateInternTree(0);
                if (!this.currentInterviewMaker.checkInternsLastResponse()) {
                    res.append(GamePrompts.NEXT_CHOICE_PROMPT);
                    res.append(currentInterviewMaker.getChoiceOptions());
                } else {
                    res.append(GamePrompts.HIRE_INTERN);
                    res.append(GamePrompts.HOW_MANY_HIRED).append(this.currentInterviewMaker.getHiredInternList().size());
                }
                return res.toString();
            }
            catch (IndexOutOfBoundsException ex){
                throw new Exception(Exceptions.INVALID_COMMAND);
            }
        }

        if (Objects.equals(input, "yes") || (Objects.equals(input, "no")) & (! input.isBlank())){
            //hired the intern/ or not
            //return "successfully/ don't hired intern"
            if (Objects.equals(input, "yes")){
                StringBuilder res = new StringBuilder();
                if (this.currentInterviewMaker.getHiredInternList().size() < 6){
                    this.currentInterviewMaker.hireIntern();
                    res.append(GamePrompts.HOW_MANY_HIRED);
                    res.append(this.currentInterviewMaker.getHiredInternList().size());
                    res.append("\n");
                    if (this.currentInterviewMaker.haveInterviewsLeft()){
                        this.currentInterviewMaker.updateInterviewIntern();
                        return res + GamePrompts.HIRED_INTERN + GamePrompts.NEXT_INTERVIEW_INTERN_PROMPT +
                                this.currentInterviewMaker.getInterviewInternInfo();
                    }
                    if(! this.currentInterviewMaker.haveInterviewsLeft()){
                        updateLevelStatus();
                        return GamePrompts.HIRED_INTERN + GamePrompts.CHOSEN_INTERNS_TO_HIRE +
                                this.currentInterviewMaker.getHiredInternString() + getEndOfInterviewPrompt();
                    }
                }
                if (this.currentInterviewMaker.getHiredInternList().size() == 6){
                    res.append(GamePrompts.TOO_MANY_HIRED);
                    res.append(GamePrompts.FIRING_PROMPT);
                    res.append(currentInterviewMaker.getHiredInternString());
                    if (this.currentInterviewMaker.haveInterviewsLeft()){
                        return res.toString();
                    }
                    if(! this.currentInterviewMaker.haveInterviewsLeft()){
                        return res.toString();
                    }
                }
            }
            if (Objects.equals(input, "no")){
                //check if there are any interns left to interview haveInterviewsLeft()
                //if there are more interns to interview, update the current interviewing intern, prompt the player from the
                // next interviewee's output
                if (this.currentInterviewMaker.haveInterviewsLeft()){
                    this.currentInterviewMaker.updateInterviewIntern();
                    return GamePrompts.NOT_HIRED_INTERN + GamePrompts.HOW_MANY_HIRED +
                            this.currentInterviewMaker.getHiredInternList().size() + "\n" +
                            GamePrompts.NEXT_INTERVIEW_INTERN_PROMPT +
                            this.currentInterviewMaker.getInterviewInternInfo();
                }
                // if there are no more interns to interview, endLevel() and return hired successful or not and the
                // names of all interns they have hired in this interview period.
                if(! this.currentInterviewMaker.haveInterviewsLeft()){
                    updateLevelStatus();
                    return GamePrompts.NOT_HIRED_INTERN + GamePrompts.CHOSEN_INTERNS_TO_HIRE +
                            this.currentInterviewMaker.getHiredInternString() + getEndOfInterviewPrompt();
                }
            }
        }
        // fire an intern that the player chooses
        // hire the most recently interviewed intern, end the interview if reached number wanted and interview
        // the next intern otherwise.
        StringBuilder res = new StringBuilder();
        for (HiredIntern internToParse : this.currentInterviewMaker.getHiredInternList()){
            if (internToParse.getInternName().trim().equals(input) & (!input.isBlank())){
                String internToFire = internToParse.getInternName();
                this.currentInterviewMaker.hireIntern();
                this.currentInterviewMaker.fireIntern(internToFire);
                //append the current
                res.append(GamePrompts.CONFIRM_FIRING + GamePrompts.HOW_MANY_HIRED)
                        .append(this.currentInterviewMaker.getHiredInternList().size())
                        .append("\n")
                        .append(GamePrompts.INTERVIEWED_TO_HIRE)
                        .append(currentInterviewMaker.currentInterviewInternToString())
                        .append(GamePrompts.CHOSEN_INTERNS_TO_HIRE).append(currentInterviewMaker.getHiredInternString());
                if (!this.currentInterviewMaker.haveInterviewsLeft()){
                    updateLevelStatus();
                    return res + getEndOfInterviewPrompt();
                }
                if (this.currentInterviewMaker.haveInterviewsLeft()){
                    this.currentInterviewMaker.updateInterviewIntern();
                    return res + "\n" + GamePrompts.NEXT_INTERVIEW_INTERN_PROMPT +
                            this.currentInterviewMaker.getInterviewInternInfo();
                }
            }
        }
        // end the level if the player has already hired 6 interns and decides not to fire any other intern.
        if (Objects.equals(input, "end interview") & (!input.isBlank())){
            updateLevelStatus();
            return GamePrompts.CHOSEN_INTERNS_TO_HIRE +
                    this.currentInterviewMaker.getHiredInternString() + getEndOfInterviewPrompt();
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