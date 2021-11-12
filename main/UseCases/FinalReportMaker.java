package UseCases;
import Entities.Exceptions;
import Entities.GamePrompts;
import Entities.Intern;
import Entities.Project;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class FinalReportMaker implements ReportMaker {
    private final GamePrompts prompts;
    private final HRSystem currentHRSystem;

    public  FinalReportMaker(HRSystem currentHRSystem){
        this.prompts = new GamePrompts();
        this.currentHRSystem = currentHRSystem;

    }
    @Override
    public String makeReportHeader(int month) {
        return GamePrompts.REPORT_HEADER + month + '\n';
    }

    @Override
    public String makeReportBody(int projectProgress, int currentMonth) {
        String internNames = currentHRSystem.getInternNames();
        HashMap<String, Integer> projectCompatibilityList = currentHRSystem.getProject(currentMonth).getSkillsCompatibilities();
        ArrayList<HashMap<String, Integer>> internsSkills = getInternsSkills(currentHRSystem.getInternList(true));
        return bakeProjectName(currentHRSystem.getProjectName(currentMonth)) + "\n" +
                bakeProgress(projectProgress)+"\n"+
                bakeInterns(internNames) + "\n" +
                bakeInternsPerformances(internNames, internsSkills, projectCompatibilityList);
    }
    private ArrayList<HashMap<String, Integer>> getInternsSkills(ArrayList<Intern> internList) {
        //Makes an arrayList full of internSkills.
        ArrayList<HashMap<String, Integer>> internCompatabilityList  = new ArrayList<>();
        for (Intern i :internList){
            internCompatabilityList.add(i.getInternSkills());
        }
        return internCompatabilityList;
    }
    @Override
    public String bakeProjectName(String projectName) {
        return GamePrompts.PROJECT_NAME_HEADER + projectName;
    }

    @Override
    public String bakeProgress(int projectProgress) {
        return GamePrompts.PROJECT_PROGRESS_HEADER + projectProgress;
    }

    @Override
    public String bakeInterns(String internNames) {
        return internNames;
    }

    @Override
    public String bakeInternsPerformances (String internNames, ArrayList<HashMap<String, Integer>>  internSkills, HashMap<String, Integer> projectSkill) {
        StringBuilder returnLine = new StringBuilder(GamePrompts.INTERN_PERFORMANCE_HEADER + internNames + "\n");
        String[] internNamesList = internNames.split("|");
        for (int i = 0; i != internNamesList.length; i+=1) {
            returnLine.append("     - ").append(internNamesList[i]).append(": ").append(calculateInternPerformance(internSkills.get(i), projectSkill)).append("\n");
        }
        return returnLine.toString();
    }

    @Override
    public int calculateInternPerformance(HashMap<String, Integer> internSkills, HashMap<String, Integer> projectSkill) {
        int result = 0;
        ArrayList<Double> effectiveSkills = new ArrayList<Double>();
        for (String key : internSkills.keySet()) {
            int internSkill = internSkills.get(key);
            double compatibility = projectSkill.get(key);
            effectiveSkills.add(internSkill* compatibility);
        }
        for (Double number : effectiveSkills) {
            result += number;
        }
        return result/effectiveSkills.size();
    }

    @Override
    public String makeReportConclusion() {
        return prompts.REPORT_CONCLUSION;
    }

    public String endOfMonthPrompt( int currentMonth) {
        if (currentMonth == HRSystem.FINAL_MONTH){return GamePrompts.END_OF_FINAL_MONTH_PROMPT;}
        else{return GamePrompts.END_OF_MONTH_PROMPT;}
    }

    @Override
    public String confirmChoice(int currentMonth) {
        return GamePrompts.CONFIRM_ASSIGNING + currentHRSystem.makeAssignmentToString(currentMonth);
    }

    @Override
    public String getInternsInfo(){
        return GamePrompts.INTERN_INFO_HEADER + currentHRSystem.getInternNames(true);
    }

    @Override
    public String getProjectInfo(int currentMonth) {
        return GamePrompts.PROJECT_NAME_HEADER + currentHRSystem.makeProjectsToString(currentMonth);
    }

    @Override
    public String assignInternToUpgrade(String internName){
        return Exceptions.INTERN_UPGRADING_FAILURE;
    }

    @Override
    public String getUpgradingInfo(int currentMonth) {
        return currentHRSystem.makeUpgradeToString(currentMonth);
    }

    @Override
    public boolean checkUpgraded(int currentMonth) {
        //returns true if all interns have been assigned to a project
        return currentHRSystem.internUpgraded(currentMonth);
    }
}
