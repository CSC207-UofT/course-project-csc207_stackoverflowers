package ControllersPresenters;

import UseCases.ReportMaker;

//This class is a controller class that is in charge of the order
public class ReportLevel {
    //has a ReportMaker made and stored inside private variable
    private ReportMaker currentReportMaker;
    private ReportPresenter currentReportPresenter;

    //TODO: instantiate and make new ReportLevel, and give it a new ReportMaker(the one needed for this current phase)
    public ReportLevel(){
        currentReportMaker = new ReportMaker(); //Would need to use factory method, or figure a way to instantiate
        currentReportPresenter = new ReportPresenter();
    }

    //TODO: method getReport() which asks the actual ReportMaker of choice to make the report and pass the info to
    // ReportPresenter to make formatted string
    private String getReport() {
        String header = currentReportMaker.makeReportHeader();
        String intro = currentReportMaker.makeReportIntro();
        String body = currentReportMaker.makeReportBody();
        String end = currentReportMaker.endReport();
        return currentReportPresenter.displayReport(header, intro, body, end);
    }


}
