package ControllersPresenters;

import Entities.Intern;
import Entities.Project;
import UseCases.FinalReportMaker;
import UseCases.MonthReportMaker;
import UseCases.ProjectReportMaker;
import UseCases.ReportMaker;

import java.util.ArrayList;

public class ReportLevel extends Level{

    private ReportMaker currentReportMaker;
    private ReportPresenter currentReportPresenter;
    private int currentMonth;
    private String projectName;
    private int projectProgress;
    private ArrayList<Intern> interns;
    private Project project;

    /**
     * Create a ReportLevel object, which then creates a ReportMaker (stored as instance variable)
     * that's corresponding to this current phase.
     *
     * @param month A month in this game
     */
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


    /** getReport() asks the current ReportMaker to make a report,
     * which is then passed to ReportPresenter to return a formatted string representation of the report.
     */
    public String getReport() {
        String header = currentReportMaker.makeReportHeader(currentMonth);
        String intro = currentReportMaker.makeReportIntro();
        String body = currentReportMaker.makeReportBody(projectName,projectProgress, interns, project);
        String end = currentReportMaker.makeReportConclusion();
        return currentReportPresenter.displayOutput(header, intro, body, end);
    }


}
