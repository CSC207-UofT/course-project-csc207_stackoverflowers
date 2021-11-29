package UseCases;

import Entities.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/* add the new interns to a list of new employees that the player can access with their skills
UseCases.HRSystem would extend Entities.Intern class?
method updateInternList which takes in an ArrayList and puts it as the intern_list
intern_list is a private instance
method makeInternToPrompt which returns the interns in the intern_list as a formatted string (Can use method in intern
which formats each single intern into a string)


 */

public class HRSystem implements Serializable {
    public static final int FINAL_MONTH = 5;

    private final ArrayList<HiredIntern> hiredInternList;

    private ArrayList<InterviewIntern> interviewInternList;

    private ArrayList<HiredIntern> firedInternList;

    private String playerName;

    private String playerResponse;


    /**
     * The constructor makes a new HRSystem for the current phase, and stores attributes of intern
     * (both Hired and Interview) and Project to use it when needed.
     */
    public HRSystem() {
        this.hiredInternList = new ArrayList<>();
        this.interviewInternList = new ArrayList<>();
        this.firedInternList = new ArrayList<>();
    }

    /**
     * This method gets the list of Entities.HiredIntern
     *
     * @return an ArrayList of Entities.HiredIntern
     */
    public ArrayList<HiredIntern> getHiredInternList() {
        return this.hiredInternList;
    }

    /**
     * This method gets the list of Entities.InterviewIntern
     *
     * @return an ArrayList of Entities.InterviewIntern
     */
    public ArrayList<InterviewIntern> getInterviewInternList() {
        return this.interviewInternList;
    }

    /**
     * This method updates the list of Entities.HiredIntern
     *
     * @param hiredInterns the ArrayList of Entities.HiredIntern to add to the current list of HiredInterns.
     */
    public void updateHiredInternList(ArrayList<HiredIntern> hiredInterns) {
        this.hiredInternList.addAll(hiredInterns);
    }

    /**
     * This method updates the list of Entities.InterviewIntern
     *
     * @param interviewInterns the ArrayList of Entities.HiredIntern to add to the current list of HiredInterns.
     */
    public void updateInterviewInternList(ArrayList<InterviewIntern> interviewInterns) {
        this.interviewInternList = interviewInterns;
    }


    /**
     * This method gets only the names of all interns separated by "|".
     *
     * @return a String of all intern names
     */
    public String getHiredInternsNames() {
        StringBuilder res = new StringBuilder();
        for (Intern i : this.hiredInternList) {
            res.append(i.getInternName());
            res.append("|");
        }
        return res.toString();
    }


    /**
     * This method gets a String representation of all intern info.
     *
     * @return a String of intern information for every intern in internList
     */
    public String makeInterviewInternsToString() {
        StringBuilder res = new StringBuilder();
        for (Intern i : this.interviewInternList) {
            res.append(i.internToString());
        }
        return res.toString();
    }

    /**
     * This method gets a String representation of all Entities.HiredIntern info.
     *
     * @return a String of intern information for all Entities.HiredIntern in hiredInternList
     */
    public String makeHiredInternsToString() {
        StringBuilder result = new StringBuilder();
        for (HiredIntern i : this.hiredInternList) {
            result.append(i.internToString());
        }
        return result.toString();
    }


    /**
     * This method updates a Player's name.
     *
     * @param name the name of a current Player.
     */
    public void updatePlayerName(String name)throws Exception{
        if (name.isEmpty()){
            throw new IOException(Exceptions.INVALID_NAME_EMPTY);
        }else if (name.split(" ").length != 1){
            throw new IOException(Exceptions.INVALID_NAME_SPACE);
        }
        ArrayList<String> notValidCharacters = new ArrayList<>();
        notValidCharacters.add("\\");
        notValidCharacters.add("/");
        notValidCharacters.add(":");
        notValidCharacters.add(";");
        notValidCharacters.add("*");
        notValidCharacters.add("?");
        notValidCharacters.add("+");
        notValidCharacters.add("-");
        notValidCharacters.add("<");
        notValidCharacters.add(">");
        for (String character : notValidCharacters){
            if (name.contains(character)){
                throw new IOException(Exceptions.INVALID_NAME_CONTENT); //if the name contains any String from
                // notValidCharacters, raise a invalid name content error
            }
        }
        this.playerName = name;
    }

    /**
     * This method gets the Player's name information.
     *
     * @return a String representation of the current Player's name.
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * This method updates the players choice to hire an intern or not.
     *
     * @param res a String representation of the current Player's choice.
     */
    public void updatePlayerResponse(String res) {
        this.playerResponse = res;
    }

    /**
     * This method gets the Player's response to hire an intern.
     *
     * @return a String representation of the current Player's response.
     */
    public String getPlayerResponse() {
        return this.playerResponse;
    }


    /**
     * This method hires an intern into the company.
     *
     * @param intern the Entities.Intern to be hired.
     */
    public void hireIntern(Intern intern) {
        HiredIntern hiredVersion = new HiredIntern(intern.getInternName(), intern.getInternAge(),
                intern.getInternSkills());
        this.hiredInternList.add(hiredVersion);
    }

    public void fireIntern(String intern){
        int internIndex = -1;
        for (HiredIntern i : this.getHiredInternList()){
            if (i.getInternName().equals(intern)){
                internIndex = this.getHiredInternList().indexOf(i);
            }
        }
        this.firedInternList.add(this.hiredInternList.get(internIndex));
        this.hiredInternList.remove(this.hiredInternList.get(internIndex));
    }

    //todo: add javadoc
    public ArrayList<HiredIntern> getFiredInternList(){
        return this.firedInternList;
    }

    public String firedInternsToString(){
        StringBuilder result = new StringBuilder();
        for (HiredIntern i : this.firedInternList) {
            result.append(i.internToString());
        }
        return result.toString();
    }


    /**
     * This method returns if an Entities.Intern has been hired to the company.
     *
     * @param intern the Entities.Intern to check hired status.
     * @return true if the Entities.Intern has been hired or false otherwise.
     */
    public Boolean isHired(Intern intern) {
        return this.hiredInternList.contains((HiredIntern) intern);
    }


    public String choicesToString(InterviewIntern intern) {
        StringBuilder res = new StringBuilder();
        int optCount = 0;
        for (ResponseTree<ArrayList<String>> children : intern.getResponseTree().getChildren()) {
            String qA = children.getData().get(0);
            optCount += 1;
            if (optCount == 1){
                res.append("A: ");
            }
            else{
                res.append("B: ");
            }
            res.append(qA);
            res.append("\n");
        }
        return res.toString();
    }

    public String getInternChoiceResponse(String input, InterviewIntern intern) {
        //TODO: Update this method, for each intern in interviewInternList the input based on that intern
        StringBuilder res = new StringBuilder();
            if (input.equals("A")){
                ResponseTree<ArrayList<String>> child = intern.getResponseTree().getChildren().get(0);
                String response = child.getData().get(1);
                res.append(response);
            }
            if (input.equals("B")){
                ResponseTree<ArrayList<String>> child = intern.getResponseTree().getChildren().get(1);
                String response = child.getData().get(1);
                res.append(response);
            }

        return res.toString();

    }

    //TODO: Ask Jacob for javadoc.

    /**
     * Please finish javadoc
     *
     * @param internName   doc
     * @param currentMonth doc
     * @return doc
     */
    public boolean upgradeInternSkill(String internName, int currentMonth, String skillToUpgrade) {
        //Now only returns false if an intern doesn't exist.
        for (HiredIntern i : this.hiredInternList) {
            if (i.getInternName().equals(internName)) {
                if (!checkSkillSpace(skillToUpgrade, internName)) {
                    return false;
                }
                i.updateInternSkills(skillToUpgrade);
                i.updateUpgraded(currentMonth);
                return true;
            }
        }
        return false;
    }

    //TODO: ask jacob for javadoc
    /**
     * please finish javadoc
     * @param currentMonth doc
     * @return doc
     */
    public boolean internUpgraded(int currentMonth) {
        for(HiredIntern i: hiredInternList){
            if (i.getUpgradedIn() == currentMonth){
                return true;
            }
        }
        //a method that checks if an intern have been upgraded for this current month.
        // returns true when a intern has been upgraded for this month.
        return false;
    }


    private boolean checkSkillSpace(String skillToUpgrade, String internName) {
        for (HiredIntern i : this.hiredInternList) {
            if (i.getInternName().equals(internName) & i.getInternSkills().containsKey(skillToUpgrade)) {
                if (i.getInternSkills().get(skillToUpgrade) >= 100) {
                    return false;
                }
            }
        }
        return true;
    }

}
