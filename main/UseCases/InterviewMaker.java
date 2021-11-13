package UseCases;

import Entities.GamePrompts;
import Entities.InterviewIntern;
import Entities.HiredIntern;

import java.util.ArrayList;


public class InterviewMaker {

    private InterviewIntern currentInterviewIntern;
    // private HiredIntern currentHiredIntern; (may not need this in interviewmaker)
    private final HRSystem currentHRSystem ;


    /**
     * The constructor makes a new InterviewMaker for the current phase, and stores attributes to be used in
     * Controllers.InterviewLevel.
     */
    public InterviewMaker(HRSystem currentHRSystem){
        // this.currentHiredIntern = new HiredIntern();
        this.currentHRSystem = currentHRSystem;

    }

    /**
     * This method returns a String prompting the player to choose an intern to start interviewing.
     * @return a String representation of the interview prompt and possible intern names.
     */
    public String startOfInterviewPrompt(){
        //condition to check if interview has started? need in interview level?
        //The first sentence the interviewee says
        //the options the player can choose.
        return GamePrompts.START_INTERVIEW_PROMPT + getInterviewInternInfo();
    }

    /**
     * This method stores the Entities.InterviewIntern a player has decided to interview.
     * @param chosenIntern the Interview Intern chosen as a player input.
     */
    public void updatePlayerInternChoice(InterviewIntern chosenIntern){
        this.currentHRSystem.updatePlayerInternChoice(chosenIntern);
        this.currentInterviewIntern = currentHRSystem.getPlayerInternChoice();
    }


    /**
     * This method takes in this interviewIntern from UseCases.HRSystem and return the intern's first choices that
     * the player can choose.
     */

    public String getChoiceOptions(){
        return this.currentHRSystem.choicesToString();
    }

    public String choicePrompt(String playerInput){
        return GamePrompts.PLAYER_CHOICE;
    }

    /**
     * This method allows player to choose from the choices in ChoiceOptions and stores the choice
     * @return
     */
    public Object storePlayerChoice(Object options){
        return this.currentHRSystem.getPlayerInternResponseChoice(options);
    }


    /**
     * This method takes in the Player's choice from method playerChoice and returns the interviewIntern's
     * corresponding response from the response tree in GameMaker.
     */
    // needs structure of the response tree?
    public String internChoiceResponse(Object playerChoice, InterviewIntern intern){
        return this.currentHRSystem.getInternChoiceResponse(playerChoice, intern);
    }



    /**
     * This method prompts the player if they would like to hire this intern and stores the response
     * @param playerInput the player's response to the Hiring Prompt.
     */
    public String internToHire(String playerInput){
        return GamePrompts.HIRE_INTERN + currentHRSystem.updatePlayerResponse(playerInput);
    }


    /**
     * Get the list of hiredInterns from HRSystem
     */
    public ArrayList<HiredIntern> getHiredInternList(){
        return currentHRSystem.getHiredInternList();
    }


    public String hireInternPrompt(String playerInput){
        String res = "";
        if (this.currentHRSystem.getPlayerResponse().equals("yes")){
            res += GamePrompts.HIRE_INTERN;
            res += playerInput;
            res += GamePrompts.CONFIRM_HIRING;
        }
        return res;
    }

    /**
     * If the player responds "yes" to hire this interviewIntern, make this interviewIntern a hiredIntern and add this
     * HiredIntern to the list of hired interns.
     */
    public void hireIntern(){
        if (this.currentHRSystem.getPlayerResponse().equals("yes")){
            currentHRSystem.hireIntern(this.currentInterviewIntern);
        }
    }

    /**
     * This method returns the Entities.InterviewIntern name information from HRSystem.
     * @return a String representation of the interviewIntern info.
     */
    public String getInterviewInternInfo(){
        return currentHRSystem.getInterviewInternNames();
    }

    public String endOfInterviewPrompt(){
        return GamePrompts.END_OF_INTERVIEW_PROMPT;
    }


}

