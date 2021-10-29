package ControllersPresenters;

import UseCases.MonthReportMaker;
import UseCases.ReportMaker;

public class ReportLevel extends Level{
    //has a ReportMaker made and stored inside private variable
    private ReportMaker currentReportMaker;
    private ReportPresenter currentReportPresenter;
    private int currentMonth;
    //TODO: instantiate and make new ReportLevel, and give it a new ReportMaker(the one needed for this current phase)
    public ReportLevel(int month){
        currentReportMaker = new MonthReportMaker(); //TODO: THIS IS NOT FINAL! CHANGE!!
        //Would need to use factory method, or figure a way to instantiate the right ReportMaker needed
        currentReportPresenter = new ReportPresenter();
        currentMonth = month;
    }

    //TODO: method getReport() which asks the actual ReportMaker of choice to make the report and pass the info to
    // ReportPresenter to make formatted string
    private String getReport() {
        String header = currentReportMaker.makeReportHeader(currentMonth);
        String intro = currentReportMaker.makeReportIntro();
        String body = currentReportMaker.makeReportBody();
        String end = currentReportMaker.makeReportConclusion();
        return currentReportPresenter.displayReport(header, intro, body, end);
    }


}
