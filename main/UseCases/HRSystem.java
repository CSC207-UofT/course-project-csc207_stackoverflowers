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

    private String playerName;

    private String playerResponse;


    /**
     * The constructor makes a new HRSystem for the current phase, and stores attributes of intern
     * (both Hired and Interview) and Project to use it when needed.
     */
    public HRSystem() {
        this.hiredInternList = new ArrayList<>();
        this.interviewInternList = new ArrayList<>();
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
     * This method hires an intern into the company.
     *
     * @param intern the Entities.Intern to be hired.
     */
    public void hireIntern(Intern intern) {
        HiredIntern hiredVersion = new HiredIntern(intern.getInternName(), intern.getInternAge(),
                intern.getInternSkills());
        this.hiredInternList.add(hiredVersion);
    }

    /**
     * If the player wishes to fire a HiredIntern and hire another one, fireIntern removes the previously HiredIntern
     * from the list of HiredInterns.
     * @param intern a string representation of the name of the intern the player wishes to fire.
     */
    public void fireIntern(String intern) throws Exception {
        int internIndex = -1;
        for (HiredIntern i : this.getHiredInternList()){
            if (i.getInternName().equals(intern)){
                internIndex = this.getHiredInternList().indexOf(i);
            }
        }
        try{
        this.hiredInternList.remove(this.hiredInternList.get(internIndex));
        }catch (Exception e){
            throw new Exception(Exceptions.INTERN_FIRING_FAILURE);
        }

    }

    /**
     * This method takes in an InterviewIntern and traversing its response tree, displaying the intern's question options
     * in the form 'A' or 'B'.
     * @param intern The desired InterviewIntern to display question choices.
     * @return a string Representation of the InterviewIntern's choices in form 'A' or 'B'.
     */
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

    /**
     * Depending on the player's input of choice 'a' or 'b', this method takes in that choice and the current Interview
     * Intern, displaying the associated response from the intern's responseTree.
     * @param input a string representation of the player's choice of question 'a' or 'b'.
     * @param intern the desired InterviewIntern to display a response for.
     * @return a String representation of the response associated to the intern's chosen question.
     */
    public String getInternChoiceResponse(String input, InterviewIntern intern) {
        //Updated this method, for each intern in interviewInternList the input based on that intern
        StringBuilder res = new StringBuilder();
            if (input.equalsIgnoreCase("a")){
                ResponseTree<ArrayList<String>> child = intern.getResponseTree().getChildren().get(0);
                String response = child.getData().get(1);
                res.append(response);
            }
            if (input.equalsIgnoreCase("b")){
                ResponseTree<ArrayList<String>> child = intern.getResponseTree().getChildren().get(1);
                String response = child.getData().get(1);
                res.append(response);
            }

        return res.toString();

    }

    /**
     * This method upgrades an Entities.HiredIntern's Skill based on their name and month.
     *
     * @param internName   the desired HiredIntern to upgrade.
     * @param currentMonth the desired month to upgrade the HiredIntern for.
     * @return true if the skill has been upgraded or false if the intern does not exist.
     */
    public boolean upgradeInternSkill(String internName, int currentMonth, String skillToUpgrade) {
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

    /**
     * This method returns a boolean representing whether an Entities.HiredIntern has been upgraded already.
     * @param currentMonth the desired month to check if the HiredIntern has been upgraded.
     * @return true if the HiredIntern has been upgraded for the current month and false otherwise.
     */
    public boolean internUpgraded(int currentMonth) {
        for(HiredIntern i: hiredInternList){
            if (i.getUpgradedIn() == currentMonth){
                return true;
            }
        }
        return false;
    }

    /**
     * This method checks if a chosen HiredIntern's skill can be upgraded depending on whether the max skill value has
     * been reached or not.
     * @param skillToUpgrade The skill desired to be upgraded.
     * @param internName A string representation of the name of the intern, that is being checked.
     * @return return false if this HiredIntern's skill value surpasses the maximum threshold and thus does not have any
     * more space to upgrade with and true otherwise.
     */
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
