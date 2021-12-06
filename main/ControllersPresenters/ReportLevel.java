package ControllersPresenters;
import Entities.Exceptions;
import Entities.GamePrompts;
import UseCases.HRSystem;
import UseCases.PMSystem;
import UseCases.FinalReportMaker;
import UseCases.MonthReportMaker;
import UseCases.ProjectReportMaker;
import UseCases.ReportMaker;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class ReportLevel extends Level{

    private ReportMaker currentReportMaker;
    private ReportPresenter currentReportPresenter;
    private int currentMonth;
    String randomSkillThisMonth = generateRandomSkill();
    String upgradePrompt;


    /**
     * Create a ReportLevel object, which then creates a ReportMaker (stored as instance variable)
     * that's corresponding to this current phase.
     *
     * @param month A month in this game
     */
    public ReportLevel(int month, HRSystem hrSystem, PMSystem pmSystem) {
        if (month == 1 | month == 3 | month == 5) {
            currentReportMaker = new MonthReportMaker(hrSystem, pmSystem);
        }
        if (month == 2 | month == 4) {
            //this is for the end of month 2, 4
            currentReportMaker = new ProjectReportMaker(hrSystem, pmSystem);
        } if (month == 6) {
            //this is for the end of month 6
            currentReportMaker = new FinalReportMaker(hrSystem, pmSystem);
        }
        currentMonth = month;
        upgradePrompt = currentReportMaker.makeUpgradePrompt(randomSkillThisMonth);
        currentReportPresenter = new ReportPresenter();
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
        if (input.contains("assign intern to upgrade")) {
            if (!input.startsWith("assign intern to upgrade ")){
                throw new Exception(Exceptions.INVALID_COMMAND);
            }
            return assignInternToUpgrade(input);
        }
        else{throw new Exception(Exceptions.INVALID_COMMAND);}
    }


    /** getReport() asks the current ReportMaker to make a report,
     * which is then passed to ReportPresenter to return a formatted string representation of the report.
     */
    public String getReport() {
        String header = currentReportMaker.makeReportHeader(currentMonth);
        String intro = currentReportMaker.makeReportIntro();
        String body = currentReportMaker.makeReportBody(currentMonth);
        String end = currentReportMaker.makeReportConclusion();

        return currentReportPresenter.displayOutput(header, intro, body, end, upgradePrompt);
    }

    private String generateRandomSkill(){
        ArrayList<String> skillList = new ArrayList<>(){
            {
                add(GamePrompts.SKILL1);
                add(GamePrompts.SKILL2);
                add(GamePrompts.SKILL3);
                add(GamePrompts.SKILL4);
                add(GamePrompts.SKILL5);
                add(GamePrompts.SKILL6);
                add(GamePrompts.SKILL7);
                add(GamePrompts.SKILL8);
                add(GamePrompts.SKILL9);
                add(GamePrompts.SKILL10);
                add(GamePrompts.SKILL11);
                add(GamePrompts.SKILL12);
                add(GamePrompts.SKILL13);
            }
        };
        Random r = new Random();
        int randomItem = r.nextInt(skillList.size());
        return skillList.get(randomItem);
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
        if (inputs.length != 5){
            throw new Exception(Exceptions.INVALID_COMMAND);
        }
        String intern = inputs[4];
        return currentReportMaker.upgradeIntern(intern, currentMonth, randomSkillThisMonth);
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
