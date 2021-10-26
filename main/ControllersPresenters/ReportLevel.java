package ControllersPresenters;

import UseCases.ReportMaker;

//This class is a controller class that is in charge of the order
public class ReportLevel {
    //has a ReportMaker made and stored inside private variable
    private ReportMaker reportMaker;

    //TODO: instantiate and make new ReportMaker (the one needed for this current phase)
    public ReportLevel(){
        reportMaker = new ReportMaker();
    }

    //TODO: method getReport() which asks the actual ReportMaker of choice to make the report and pass the info to
    // ReportPresenter to make formatted string
    void getReport(ReportMaker rm) {

    }


}
