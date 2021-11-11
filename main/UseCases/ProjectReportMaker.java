package UseCases;
import Entities.Exceptions;
import Entities.GamePrompts;
import Entities.HiredIntern;
import Entities.Intern;
import Entities.Project;

import java.util.ArrayList;
import java.util.HashMap;

public class ProjectReportMaker implements ReportMaker{
    private final GamePrompts prompts;
    private final HRSystem currentHRSystem;

    public ProjectReportMaker(HRSystem currentHRSystem){
        this.prompts = new GamePrompts();
        this.currentHRSystem = currentHRSystem;
    }
    @Override
    public String makeReportHeader(int month) {
        return "Here is your report for the end of " + month + "\n";
    }

    @Override
    public String makeReportIntro() {
        return "This is an end report of this completed project." + "\n";
    }


    /*
    project reportBody format
    Project name: xxx (need to access project.projectName)
    Project overall result rating: 8/10 (randomly generated)

    Assigned interns: hhh, yyy, zzz (how do we know who are working on this project? Class Project needs an instance variable
                                     that stores the assigned interns, which first refers to None and later gets updated
                                     to refer to the list of assigned interns?)
    Interns ratings and evaluations (comments need to be related to their performances in the monthly report & their ratings):
        - hhh: 10/10: excellent leadership, great teamwork skills
        - yyy: 7/10: a diligent worker, needs to improve on efficiency
        - zzz: 3/10: poor attendance, bad teamwork
    */
    @Override
    public String makeReportBody(int projectProgress, int currentMonth) {
        String internNames = currentHRSystem.getInternNames();

        ArrayList<Project> projList = currentHRSystem.getProject(currentMonth);
        HashMap<String, Integer> projectCompatibilityList = new HashMap<>();
        for (Project proj : projList) {
            projectCompatibilityList.putAll(proj.getSkillsCompatibilities());
        }
        ArrayList<HashMap<String, Integer>> internsSkills = getHiredInternsSkills(currentHRSystem.getHiredInternList());
        return bakeProjectName(currentHRSystem.getProjectName(currentMonth)) + "\n" +
                bakeProgress(projectProgress)+"\n"+
                bakeInterns(internNames) + "\n" +
                bakeInternsPerformances(internNames, internsSkills, projectCompatibilityList);
    }
    private ArrayList<HashMap<String, Integer>> getHiredInternsSkills(ArrayList<HiredIntern> hiredInternList) {
        //Makes an arrayList full of internSkills.
        ArrayList<HashMap<String, Integer>> internCompatabilityList  = new ArrayList<>();
        for (Intern i : hiredInternList){
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

    public String endOfMonthPrompt(int currentMonth) {
        if (currentMonth == HRSystem.FINAL_MONTH){return GamePrompts.END_OF_FINAL_MONTH_PROMPT;}
        else{return GamePrompts.END_OF_MONTH_PROMPT;}
    }

    public String confirmChoice(int currentMonth) {
        return GamePrompts.CONFIRM_ASSIGNING + currentHRSystem.makeAssignmentToString(currentMonth);
    }

    @Override
    public String getInternsInfo(){
        return GamePrompts.INTERN_INFO_HEADER + currentHRSystem.getInternNames(true);
    }

    @Override
    public String getProjectInfo(int currentMonth) {
        return GamePrompts.PROJECT_PROGRESS_HEADER + currentHRSystem.makeProjectsToString(currentMonth);
    }

    @Override
    public String assignInternToUpgrade(String internName) throws Exception {
        boolean success = currentHRSystem.assignInternToUpgrade(internName);
        if (!success){throw new Exception(Exceptions.INTERN_UPGRADING_FAILURE);}
        return GamePrompts.INTERN_UPGRADING_SUCCESS;
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
