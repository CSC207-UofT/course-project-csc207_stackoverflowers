package UseCases;

public class MonthMaker {
    /* This MonthMaker class is responsible for returning the outputs related to month.
     */
    private final HRSystem currentHRSystem;
    public MonthMaker(HRSystem currentHRSystem){
        this.currentHRSystem = currentHRSystem;
    }
    //TODO: finish 3 methods that output the needed information from UseCases.HRSystem
    // * startOfMonthPrompt()
    // * getProjectInfo()
    public String startOfMonthPrompt() {
        return "This startOfMonthPrompt is not implemented";
    }

    public String getProjectInfo() {
        return currentHRSystem.makeProjectsToString();
    }

    public String getInternsInfo(){
        //We only want the list of hired interns
        return currentHRSystem.makeInternsToString(true);
    }

    public boolean assignInternToProject(String internName, String projectName) {
        boolean result = currentHRSystem.assignInternToProject(internName, projectName);
        return result;
    }

    public boolean removeInternFromProject(String internName, String projectName) {
        boolean result = currentHRSystem.removeInternFromProject(internName, projectName);
        return result;
    }


}
