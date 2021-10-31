package UseCases;

public class MonthMaker {
    /* This MonthMaker class is responsible for returning the outputs related to month.
     */
    public MonthMaker(){
        //constructor, is constructed with no variables
    }
    //TODO: finish 3 methods that output the needed information from Entities.HRSystem
    // * startOfMonthPrompt()
    // * getProjectInfo()
    // * getInternInfo()
    public String startOfMonthPrompt() {
        return "This startOfMonthPrompt is not implemented";
    }

    public String getProjectInfo() {
        return "There is no project info method yet.";
    }

    public String getInternInfo(){
        return "There is no intern info method yet.";
    }

    public boolean assignInternToProject(String internName, String projectName) {
        //TODO: implement assignInternToProject by asking the current HRSystem to do so.
        return false;
    }

    public boolean removeInternFromProject(String internName, String projectName) {
        //TODO: implement this method by asking the current HRSystem to do so.
        return false;
    }



    //TODO: write methods related to player's command
    // * assignInternToProject()
    // * removeInternFromProject()

    // (the results need to be passed back to MonthLevel)
}
