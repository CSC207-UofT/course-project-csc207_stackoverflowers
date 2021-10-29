package UseCases;

//An interface class that shows all common methods used to make reports:
//TODO: * makeReportHeader()
//TODO:  * makeReportIntro()
//TODO:  * makeReportBody()
//TODO:  * makeReportConclusion()
public interface ReportMaker {

    String makeReportHeader (int month);



    String makeReportIntro ();



    String makeReportBody ();



    String makeReportConclusion();


}
