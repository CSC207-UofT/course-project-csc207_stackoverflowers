package UseCases;

public interface ReportMaker {
    // An abstract class, that have all the essentials needed to make a report.
    //TODO: instantiate
    //TODO: * an interface class that shows all common methods used to make reports:
    //TODO: * makeReportHeader()
    //TODO:  * makeReportIntro()
    //TODO:  * makeReportBody()
    //TODO:  * endReport()
    String makeReportHeader (String timeName){
        return "Here is your report for the end of " + timeName + ": ";
    }

    String makeReportIntro (){
        return "";
    }

    String makeReportBody (){
        return "";
    }

    private String endReport(){
        return "That's all! Have a good day manager. :)";
    }
    //TODO: void methods:
    //makeReportHeader()
    //makeReportIntro()
    //makeReportBody()
    //endReport()
}
