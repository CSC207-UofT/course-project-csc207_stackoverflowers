package Report;

public class ReportMaker {
    //An interface class that shows all common methods used to make reports:

    //TODO: makeReportHeader(),takes in a String "timeName"
    //TODO: Change raw Strings to reference to gamePrompts
    private String makeReportHeader (String timeName){
        return "Here is your report for the end of " + timeName + ": ";
    }

    //TODO: makeReportIntro(), takes in information related to intro
    //TODO: Change raw Strings to reference to gamePrompts
    private String makeReportIntro (){
        return "";
    }

    //TODO: makeReportBody(), takes in information related to body
    //TODO: Change raw Strings to reference to gamePrompts
    private String makeReportBody (){
        return "";
    }

    //TODO: endReport()
    //TODO: Change raw Strings to reference to gamePrompts
    private String endReport(){
        return "That's all! Have a good day manager. :)";
    }
}
