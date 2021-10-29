package ControllersPresenters;

import UseCases.FinalReportMaker;
import UseCases.MonthReportMaker;
import UseCases.ProjectReportMaker;
import UseCases.ReportMaker;

public class ReportLevel extends Level{
    //has a ReportMaker made and stored inside private variable
    private ReportMaker currentReportMaker;
    private ReportPresenter currentReportPresenter;
    private int currentMonth;
    //TODO: instantiate and make new ReportLevel,
    // and give it a new ReportMaker(the one needed for this current phase)
    // Would need to use factory design pattern, or figure
    // a way to instantiate the right ReportMaker needed
    public ReportLevel(int month) {
        if (month < 6 & month % 2 == 1) {
            //this is for the end of month 1, 3 ,5
            currentReportMaker = new MonthReportMaker();
        }
        if (month < 4 & month % 2 == 0) {
            //this is for the end of month 2, 4
            currentReportMaker = new ProjectReportMaker();
        } else {
            //this is for the end of month 6
            currentReportMaker = new FinalReportMaker();
        }
    }



    //TODO: method getReport() which asks the actual ReportMaker of choice to make the report and pass the info to
    // ReportPresenter to make formatted string
    public String getReport() {
        String header = currentReportMaker.makeReportHeader(currentMonth);
        String intro = currentReportMaker.makeReportIntro();
        String body = currentReportMaker.makeReportBody();
        String end = currentReportMaker.makeReportConclusion();
        return currentReportPresenter.displayOutput(header, intro, body, end);
    }


}
