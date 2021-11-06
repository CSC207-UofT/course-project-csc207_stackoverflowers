package UseCases;

import Entities.GamePrompts;

public class MonthMaker {
    /* This MonthMaker class is responsible for returning the outputs related to month.
     */
    private final HRSystem currentHRSystem;

    public MonthMaker(HRSystem currentHRSystem){
        this.currentHRSystem = currentHRSystem;
    }

    public String startOfMonthPrompt() {
        return GamePrompts.START_OF_MONTH_PROMPT_BEFORE_NAME + GamePrompts.START_OF_MONTH_PROMPT_BEFORE_NAME +
                currentHRSystem.getPlayerName() +
                GamePrompts.START_OF_MONTH_PROMPT_AFTER_NAME +
                getProjectInfo() +
                GamePrompts.START_OF_MONTH_PROMPT_AFTER_PROJECTS;
    }

    public String endOfMonthPrompt() {
        return GamePrompts.END_OF_MONTH_PROMPT;
    }

    public String getProjectInfo() {
        return currentHRSystem.makeProjectsToString();
    }

    public String getInternsInfo(){
        //We only want the list of hired interns
        return currentHRSystem.makeInternsToString(true);
    }

    public String assignInternToProject(String internName, String projectName) {
        boolean success = currentHRSystem.assignInternToProject(internName, projectName);
        if (success){return GamePrompts.INTERN_ASSIGNING_SUCCESS;}
        return GamePrompts.INTERN_ASSIGNING_FAILURE;
    }

    public String removeInternFromProject(String internName, String projectName) {
        boolean success = currentHRSystem.removeInternFromProject(internName, projectName);
        if (success){return GamePrompts.INTERN_REMOVING_SUCCESS;}
        return GamePrompts.INTERN_REMOVING_FAILURE;
    }

    public boolean checkAllInternsAssigned() {
        //returns true if all interns have been assigned to a project
        return currentHRSystem.internsAllAssigned();
    }

    public String confirmChoice() {
        return GamePrompts.CONFIRM_ASSIGNING + currentHRSystem.makeAssignmentToString();
    }

    public String getAssigningInfo() {
        //returns
        return currentHRSystem.makeAssignmentToString();
    }
}
