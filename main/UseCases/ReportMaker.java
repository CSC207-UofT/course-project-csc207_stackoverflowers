package UseCases;

//An interface class that shows all common methods used to make reports:
//TODO: * makeReportHeader()
//TODO:  * makeReportIntro()
//TODO:  * makeReportBody()
//TODO:  * makeReportConclusion()
public interface ReportMaker {

    String makeReportHeader (int month);
        //return "Here is your report for the end of " + month;


    String makeReportIntro ();
        //return "";


    String makeReportBody ();
        //return "";


    String makeReportConclusion();
        //return REPORT_CONCLUSION;

}
