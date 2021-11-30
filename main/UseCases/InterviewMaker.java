package UseCases;

import Entities.GamePrompts;
import Entities.InterviewIntern;
import Entities.HiredIntern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


public class InterviewMaker {

    private InterviewIntern currentInterviewIntern;
    private final HRSystem currentHRSystem ;
    private final ArrayList<String> interviewedInterns;


    /**
     * The constructor makes a new InterviewMaker for the current phase, and stores attributes to be used in
     * Controllers.InterviewLevel.
     */
    public InterviewMaker(HRSystem currentHRSystem){
        this.currentHRSystem = currentHRSystem;
        this.interviewedInterns = new ArrayList<>();
        this.currentInterviewIntern = currentHRSystem.getInterviewInternList().get(0);

    }

    /**
     * This method returns a String prompting the player to choose an intern to start interviewing.
     * @return a String representation of the interview prompt and possible intern names.
     */
    public String startOfInterviewPrompt(){
        //This returns the InterviewInterns name, age and skill information as well as the first
        // choices a player must choose.
        return GamePrompts.START_INTERVIEW_PROMPT + getInterviewInternInfo();
    }

    /**
     * This method takes in this interviewIntern from UseCases.HRSystem and return the intern's first choices that
     * the player can choose.
     */

    public String getChoiceOptions(){
        return this.currentHRSystem.choicesToString(currentInterviewIntern);
    }

    public String choicePrompt(String playerInput){
        return GamePrompts.PLAYER_CHOICE;
    }


    /**
     * This method takes in the Player's choice from method playerChoice and returns the interviewIntern's
     * corresponding response from the response tree in GameMaker.
     */
    public String displayInternChoiceResponse(String input){
        return this.currentHRSystem.getInternChoiceResponse(input, currentInterviewIntern);
    }



    /**
     * This method prompts the player if they would like to hire this intern and stores the response
     * @param playerInput the player's response to the Hiring Prompt.
     */
    public String internToHire(String playerInput){
        return  GamePrompts.TO_HIRE_PROMPT ;
    }


    /**
     * Get the string representation of hiredInterns from HRSystem.
     * @return a string of hired interns.
     */
    public String getHiredInternString(){
        return currentHRSystem.makeHiredInternsToString();
    }

    public ArrayList<InterviewIntern> getInterviewInternList(){
        return currentHRSystem.getInterviewInternList();
    }

    //TODO: remove if not used
    public ArrayList<InterviewIntern> getInterviewInternString(){
        return currentHRSystem.getInterviewInternList();
    }

    //TODO: Add javadoc
    public ArrayList<HiredIntern> getHiredInternList(){
        return currentHRSystem.getHiredInternList();
    }
    /**
     * If the player responds "yes" to hire this interviewIntern, make this interviewIntern a hiredIntern and add this
     * HiredIntern to the list of hired interns.
     */
    public void hireIntern(){
        this.currentHRSystem.hireIntern(this.currentInterviewIntern);
    }

    //TODO: add javadoc
    public void fireIntern(String intern){
        this.currentHRSystem.fireIntern(intern);
    }

    //TODO: add javadoc
    public String currentInterviewInternToString(){
        return this.currentInterviewIntern.internToString();
    }

    /**
     * This method returns the Entities.InterviewIntern name information from HRSystem.
     * @return a String representation of the interviewIntern info.
     */
    public String getInterviewInternInfo(){
        StringBuilder res = new StringBuilder();
        ResponseTree<ArrayList<String>> responseTree = this.currentInterviewIntern.getResponseTree();
        res.append(responseTree.getData());

        res.append("\n\nThese are your options to ask the intern, please enter either 'A' or 'B' to ask a question. \n");
        int optCount = 0;
        for (ResponseTree<ArrayList<String>> response : responseTree.getChildren()){
            // if the response tree is a root, display the interns initial response(i.e. name, age & skill info).
            optCount += 1;
            if (! response.isRoot()){
                String qA = response.getData().get(0);
                if (optCount == 1){
                    res.append("A: ");
                }
                else{
                    res.append("B: ");
                }
                res.append(qA);
                res.append("\n");
            }
        }
        return res.toString();
    }

    public String endOfInterviewPrompt(){
        return GamePrompts.END_OF_INTERVIEW_PROMPT;
    }

    /**
     * This method updates the current interview intern to the next intern in the list that has not yet been interviewed.
     */
    public void updateInterviewIntern() {
        for (InterviewIntern intern : this.currentHRSystem.getInterviewInternList()){
            if (! this.interviewedInterns.contains(intern.getInternName()))
                this.currentInterviewIntern = intern;
        }
    }


    /**
     * This method checks if the intern's current response is the last response on their response tree
     * (i.e. at the end of the interview).
     * @return true if this is the last response and false otherwise.
     */
    public Boolean checkInternsLastResponse() {
        // if this response is a leaf (the last response) and the intern's response contains this last response
        if (currentInterviewIntern.getResponseTree().isLeaf() ) {
            this.interviewedInterns.add(this.currentInterviewIntern.getInternName());
            return true;
        }
        return false;
    }


    /**
     * This method checks if there are any more intern's to interview.
     * @return true if there are more interns to interview and false otherwise.
     */
    public boolean haveInterviewsLeft() {
        return this.currentHRSystem.getInterviewInternList().size() != this.interviewedInterns.size();
    }

    public void updateInternTree(int theWanted) {
        currentInterviewIntern.setResponseTree(currentInterviewIntern.getResponseTree().getChildren().get(theWanted));
    }

    public String getFiredInterns(){
        return this.currentHRSystem.firedInternsToString();
    }

    public ArrayList<HiredIntern> getFiredInternList(){
        return this.currentHRSystem.getFiredInternList();
    }

}

