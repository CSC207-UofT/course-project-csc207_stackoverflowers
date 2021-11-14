package UseCases;
import Entities.Exceptions;
import Entities.GamePrompts;
import Entities.HiredIntern;
import Entities.Intern;
import Entities.Project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProjectReportMaker implements ReportMaker{
    private final GamePrompts prompts;
    private final HRSystem currentHRSystem;

    public ProjectReportMaker(HRSystem currentHRSystem){
        this.prompts = new GamePrompts();
        this.currentHRSystem = currentHRSystem;
    }
    @Override
    public String makeReportHeader(int month) {
        return GamePrompts.REPORT_HEADER + month + "\n";
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
    public String makeReportBody( int currentMonth) {
        ArrayList<HiredIntern> interns = currentHRSystem.getHiredInternList();

        List<Project> projList = currentHRSystem.getProjects(currentMonth);
        HashMap<String, Float> projectCompatibilityList = new HashMap<>();
        for (Project proj : projList) {
            projectCompatibilityList.putAll(proj.getSkillsCompatibilities());
        }
        ArrayList<HashMap<String, Double>> internsSkills = getHiredInternsSkills(currentHRSystem.getHiredInternList());
        return bakeProjectName(currentHRSystem.getProjectNames(currentMonth)) + "\n" +
                bakeInterns(currentHRSystem.getHiredInternsNames()) + "\n" +
                bakeInternsPerformances(interns, projectCompatibilityList);
    }


    private ArrayList<HashMap<String, Double>> getHiredInternsSkills(ArrayList<HiredIntern> hiredInternList) {
        //Makes an arrayList full of internSkills.
        ArrayList<HashMap<String, Double>> internCompatabilityList  = new ArrayList<>();
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
    public String bakeInterns(String internNames) {
        return internNames;
    }
    @Override
    public String bakeInternsPerformances (ArrayList<HiredIntern> interns, HashMap<String, Float> projectSkill) {
        StringBuilder returnLine = new StringBuilder(GamePrompts.INTERN_PERFORMANCE_HEADER + currentHRSystem.getHiredInternsNames() + "\n");
        String[] internNamesList = currentHRSystem.getHiredInternsNames().split("|");
        for (int i = 0; i != interns.size(); i+=1) {
            returnLine.append("     - ").append(internNamesList[i]).append(": ").append(calculateInternPerformance(interns.get(i).getInternSkills(), projectSkill)).append("\n");
        }
        return returnLine.toString();
    }

    @Override
    public int calculateInternPerformance(HashMap<String, Double> internSkills, HashMap<String, Float> projectSkill) {
        int result = 0;
        ArrayList<Double> effectiveSkills = new ArrayList<Double>();
        for (String key : internSkills.keySet()) {
            double internSkill = internSkills.get(key);
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

    @Override
    public String endOfMonthPrompt( int currentMonth) {
        if (currentMonth == HRSystem.FINAL_MONTH){return GamePrompts.END_OF_FINAL_MONTH_REPORT_PROMPT;}
        else{return GamePrompts.END_OF_MONTH_REPORT_PROMPT;}
    }

    public String confirmChoice(int currentMonth) {
        return GamePrompts.CONFIRM_ASSIGNING + currentHRSystem.makeAssignmentToString(currentMonth);
    }

    @Override
    public String getInternsInfo(){
        return GamePrompts.INTERN_INFO_HEADER + currentHRSystem.getHiredInternsNames();
    }

    @Override
    public String getProjectInfo(int currentMonth) {
        return GamePrompts.PROJECT_PROGRESS_HEADER + currentHRSystem.makeProjectsToString(currentMonth);
    }

    @Override
    public String upgradeIntern(String internName, int currentMonth, String randomSkillThisMonth) throws Exception {
        boolean success = currentHRSystem.upgradeInternSkill(internName, currentMonth,randomSkillThisMonth);
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

    @Override
    public String makeUpgradePrompt(String skillName) {
        return skillName + GamePrompts.MAKE_UPGRADE_PROMPT_MONTH;
    }
}
