package UseCases;

import Entities.Exceptions;
import Entities.GamePrompts;

public class MonthMaker {
    /* This MonthMaker class is responsible for returning the outputs related to month.
     */
    private final HRSystem currentHRSystem;
    private final PMSystem currentPMSystem;
    private final int currentMonth;

    public MonthMaker(HRSystem currentHRSystem, PMSystem currentPMSystem, int currentMonth){
        this.currentHRSystem = currentHRSystem;
        this.currentPMSystem = currentPMSystem;
        this.currentMonth = currentMonth;
    }


    public String startOfMonthPrompt() {
        if (currentMonth != HRSystem.FINAL_MONTH){
            return GamePrompts.START_OF_MONTH_PROMPT_BEFORE_NAME +
                    currentHRSystem.getPlayerName() +
                    GamePrompts.START_OF_MONTH_PROMPT_AFTER_NAME +
                    getProjectInfo() +
                    GamePrompts.AVAILABLE_COMMANDS_IN_MONTH;
        }
        return GamePrompts.FINAL_MONTH_PROMPT_BEFORE_PROJECT + getProjectInfo() +
                GamePrompts.AVAILABLE_COMMANDS_IN_MONTH;
    }

    public String endOfMonthPrompt() {
        if (currentMonth == HRSystem.FINAL_MONTH){return GamePrompts.END_OF_FINAL_MONTH_PROMPT;}
        else{return GamePrompts.END_OF_MONTH_PROMPT;}
    }

    public String getProjectInfo() {
        return "Here are the projects that you are responsible for:" + currentPMSystem.makeProjectsToString(currentMonth);
    }

    public String getInternsInfo(){
        return "Here is the list of interns that you have hired:\n" + currentHRSystem.makeHiredInternsToString();
    }

    public String assignInternToProject(String internName, String projectName) throws Exception {
        boolean success = currentPMSystem.assignInternToProject(internName, projectName);
        if (!success){throw new Exception(Exceptions.INTERN_ASSIGNING_FAILURE);}
        return GamePrompts.INTERN_ASSIGNING_SUCCESS;
    }

    public String removeInternFromProject(String internName, String projectName) throws Exception {
        boolean success = currentPMSystem.removeInternFromProject(internName, projectName);
        if (!success){ throw new Exception(Exceptions.INTERN_REMOVING_FAILURE);}
        return GamePrompts.INTERN_REMOVING_SUCCESS;
    }

    public boolean checkAllInternsAssigned() {
        //returns true if all interns have been assigned to a project
        return currentPMSystem.internsAllAssigned(currentMonth);
    }

    public String confirmChoice() {
        return GamePrompts.CONFIRM_ASSIGNING + currentPMSystem.makeAssignmentToString(currentMonth);
    }

    public String getAssigningInfo() {
        return currentPMSystem.makeAssignmentToString(currentMonth);
    }


}
