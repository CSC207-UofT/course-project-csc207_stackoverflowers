package UseCases;

import Entities.Exceptions;
import Entities.GamePrompts;

public class MonthMaker {
    /* This MonthMaker class is responsible for returning the outputs related to month.
     */
    private final HRSystem currentHRSystem;
    private final int currentMonth;

    public MonthMaker(HRSystem currentHRSystem, int currentMonth){
        this.currentHRSystem = currentHRSystem;
        this.currentMonth = currentMonth;
    }


    public String startOfMonthPrompt() {
        if (currentMonth != HRSystem.FINAL_MONTH){
            return GamePrompts.START_OF_MONTH_PROMPT_BEFORE_NAME +
                    currentHRSystem.getPlayerName() +
                    GamePrompts.START_OF_MONTH_PROMPT_AFTER_NAME +
                    getProjectInfo() +
                    GamePrompts.START_OF_MONTH_PROMPT_AFTER_PROJECTS;
        }
        return GamePrompts.FINAL_MONTH_PROMPT_BEFORE_PROJECT + getProjectInfo() +
                GamePrompts.FINAL_MONTH_PROMPT_AFTER_PROJECT;
    }

    public String endOfMonthPrompt() {
        if (currentMonth == HRSystem.FINAL_MONTH){return GamePrompts.END_OF_FINAL_MONTH_PROMPT;}
        else{return GamePrompts.END_OF_MONTH_PROMPT;}
    }

    public String getProjectInfo() {
        return "Here are the projects that you are responsible for:" + currentHRSystem.makeProjectsToString(currentMonth);
    }

    public String getInternsInfo(){
        return "Here is the list of interns that you have hired:" + currentHRSystem.getHiredInternsNames();
    }

    public String assignInternToProject(String internName, String projectName) throws Exception {
        boolean success = currentHRSystem.assignInternToProject(internName, projectName);
        if (!success){throw new Exception(Exceptions.INTERN_ASSIGNING_FAILURE);}
        return GamePrompts.INTERN_ASSIGNING_SUCCESS;
    }

    public String removeInternFromProject(String internName, String projectName) throws Exception {
        boolean success = currentHRSystem.removeInternFromProject(internName, projectName);
        if (!success){ throw new Exception(Exceptions.INTERN_REMOVING_FAILURE);}
        return GamePrompts.INTERN_REMOVING_SUCCESS;
    }

    public boolean checkAllInternsAssigned() {
        //returns true if all interns have been assigned to a project
        return currentHRSystem.internsAllAssigned(currentMonth);
    }

    public String confirmChoice() {
        return GamePrompts.CONFIRM_ASSIGNING + currentHRSystem.makeAssignmentToString(currentMonth);
    }

    public String getAssigningInfo() {
        return currentHRSystem.makeAssignmentToString(currentMonth);
    }


}
