package ControllersPresenters;
import Entities.Exceptions;
import UseCases.HRSystem;
import Entities.Intern;
import Entities.Project;

import UseCases.FinalReportMaker;
import UseCases.MonthReportMaker;
import UseCases.ProjectReportMaker;
import UseCases.ReportMaker;

import java.util.ArrayList;
import java.util.Objects;

public class ReportLevel extends Level{

    private ReportMaker currentReportMaker;
    private ReportPresenter currentReportPresenter;
    private final HRSystem currentHRsystem;
    private int currentMonth;
    private int projectProgress;

    /**
     * Create a ReportLevel object, which then creates a ReportMaker (stored as instance variable)
     * that's corresponding to this current phase.
     *
     * @param month A month in this game
     * @param currentHRSystem
     */
    public ReportLevel(int month, HRSystem currentHRSystem) {
        if (month < 6 & month % 2 == 1) {
            //this is for the end of month 1, 3 ,5
            currentReportMaker = new MonthReportMaker(currentHRSystem);
        }
        if (month < 4 & month % 2 == 0) {
            //this is for the end of month 2, 4
            currentReportMaker = new ProjectReportMaker(currentHRSystem);
        } else {
            //this is for the end of month 6
            currentReportMaker = new FinalReportMaker(currentHRSystem);
        }
        currentHRsystem = currentHRSystem;
        currentMonth = month;
    }

    public String getOutputString(String input) throws Exception {
        if (levelStarted()){
            getIntoLevel();
            return getReport();
        }
        if (Objects.equals(input, "confirm all decisions")){
            endLevel();
            return endPrompt(currentMonth);
        }
        if (finishedUpgrading(currentMonth)){
            return currentReportMaker.confirmChoice(currentMonth);
        }
        if (Objects.equals(input, "check project info")){
            return checkProjectInfo();
        }
        if (Objects.equals(input, "check interns info")){
            return checkInternsInfo();
        }
        if (Objects.equals(input, "check assign")){
            return checkUpgradingInfo(currentMonth);
        }
        if (input.contains("" +
                "assign intern to upgrade")) {
            return assignInternToUpgrade(input);
        }
        else{throw new Exception(Exceptions.INVALID_COMMAND);}
    }


    /** getReport() asks the current ReportMaker to make a report,
     * which is then passed to ReportPresenter to return a formatted string representation of the report.
     */
    public String getReport() {
        String header = currentReportMaker.makeReportHeader(currentMonth);
        String body = currentReportMaker.makeReportBody(currentMonth, projectProgress);
        String end = currentReportMaker.makeReportConclusion();
        return currentReportPresenter.displayOutput(header, intro, body, end);
    }
    private String checkInternsInfo() {
        return currentReportMaker.getInternsInfo();
    }

    private String checkProjectInfo() {
        return currentReportMaker.getProjectInfo(currentMonth);
    }

    private String endPrompt(int currentMonth){
        return currentReportMaker.endOfMonthPrompt(currentMonth);
    }

    private String assignInternToUpgrade(String input) throws Exception {
        String[] inputs = input.split(" ");
        String intern = inputs[3];
        return currentReportMaker.assignInternToUpgrade(intern);
    }

    private String checkUpgradingInfo(int currentMonth) {
        return currentReportMaker.getUpgradingInfo(currentMonth);
    }

    private boolean finishedUpgrading(int currentMonth){
        return currentReportMaker.checkUpgraded(currentMonth);
    }

    public ReportMaker getCurrentReportMaker(){
        return currentReportMaker;
    }
}
