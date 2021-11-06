package UseCases;

import Entities.GamePrompts;

public class MonthMaker {
    /* This MonthMaker class is responsible for returning the outputs related to month.
     */
    private final HRSystem currentHRSystem;
    private final GamePrompts prompts;

    public MonthMaker(HRSystem currentHRSystem){
        this.currentHRSystem = currentHRSystem;
        this.prompts = new GamePrompts();
    }
    //TODO: finish 3 methods that output the needed information from UseCases.HRSystem
    // * startOfMonthPrompt()
    // * getProjectInfo()
    // * getInternInfo()
    public String startOfMonthPrompt() {
        StringBuilder returnLine = new StringBuilder(prompts.START_OF_MONTH_PROMPT_BEFORE_NAME);
        returnLine.append(prompts.START_OF_MONTH_PROMPT_BEFORE_NAME);
        returnLine.append(currentHRSystem.getPlayerName());
        returnLine.append(prompts.START_OF_MONTH_PROMPT_AFTER_NAME);
        return returnLine.toString();
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
        if (success){return prompts.INTERN_ASSIGNING_SUCCESS;}
        return prompts.INTERN_ASSIGNING_FAILURE;
    }

    public String removeInternFromProject(String internName, String projectName) {
        boolean success = currentHRSystem.removeInternFromProject(internName, projectName);
        if (success){return prompts.INTERN_REMOVING_SUCCESS;}
        return prompts.INTERN_REMOVING_FAILURE;
    }

    public boolean finishedAssigning() {
        return currentHRSystem.internsAllAssigned();
    }
}
