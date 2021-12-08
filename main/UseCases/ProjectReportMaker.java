package UseCases;
import Entities.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProjectReportMaker implements ReportMaker {
    private final GamePrompts prompts;
    private final HRSystem currentHRSystem;
    private final PMSystem currentPMSystem;

    public ProjectReportMaker(HRSystem hrSystem, PMSystem pmSystem){
        this.prompts = new GamePrompts();
        this.currentHRSystem = hrSystem;
        this.currentPMSystem = pmSystem;
    }

    @Override
    public String makeReportHeader(int month) {
        return GamePrompts.REPORT_HEADER + month + "\n";
    }

    @Override
    public String makeReportIntro() {
        return GamePrompts.PROJECT_REPORT_MAKER_INTRO;
    }

    /* format for the reportBody
    Project name: xxx (need to access project.projectName)
    Project progress: excellent/fair/mediocre (average of internPerformance)
    Assigned interns: hhh, yyy, zzz (how do we know who are working on this project? Class Project needs an instance variable
                                     that stores the assigned interns, which first refers to None and later gets updated
                                     to refer to the list of assigned interns?)
    Interns performances (calculated by the skills * projectCompatability(A Dictionary)):
        - hhh: excellent, exhibits great leadership skills
        - yyy: great, but needs to be more efficient
        - zzz: poor, could be potentially fired
     */
    @Override
    public String makeReportBody( int currentMonth) {
        List<Project> projList = currentPMSystem.getProjects(currentMonth);
        HashMap<String, Float> projectCompatibilityList = new HashMap<>();
        for (Project proj : projList) {
            projectCompatibilityList.putAll(proj.getSkillsCompatibilities());
        }
        return bakeProjectName(currentPMSystem.getProjectNames(currentMonth).split("\\|")[0]) + "\n" +
                currentPMSystem.getInternNamesProject(projList.get(0)) + "\n" +
                bakeInternsPerformances(currentPMSystem.getProjectToInterns().get(projList.get(0)), projectCompatibilityList) + "\n" +
                bakeProjectName(currentPMSystem.getProjectNames(currentMonth).split("\\|")[1]) + "\n" +
                currentPMSystem.getInternNamesProject(projList.get(1)) + "\n" +
                bakeInternsPerformances(currentPMSystem.getProjectToInterns().get(projList.get(1)), projectCompatibilityList);
    }

    @Override
    public String bakeProjectName(String projectName) {
        return GamePrompts.PROJECT_NAME_HEADER + projectName;
    }

    @Override
    public String bakeInternsPerformances (ArrayList<HiredIntern> interns, HashMap<String, Float> projectSkill) {
        StringBuilder res = new StringBuilder();
        for (Intern i : interns) {
            res.append(i.getInternName());
            res.append("|");
        }
        StringBuilder returnLine = new StringBuilder(GamePrompts.INTERN_PERFORMANCE_HEADER + res + "\n");
        String[] internNamesList = res.toString().split("\\|");
        for (int i = 0; i != interns.size(); i+=1) {
            returnLine.append("     - ").append(internNamesList[i]).append(": ").append(calculateInternPerformance(interns.get(i).getInternSkills(), projectSkill)).append("\n");
        }
        return returnLine.toString();
    }

    @Override
    public int calculateInternPerformance(HashMap<String, Double> internSkills,
                                          HashMap<String, Float> projectSkill) {

        int result = 0;
        ArrayList<Double> effectiveSkills = new ArrayList<>();
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
        return GamePrompts.REPORT_CONCLUSION;
    }


    @Override
    public String endOfMonthPrompt( int currentMonth) {
        if (currentMonth == HRSystem.FINAL_MONTH){return GamePrompts.END_OF_FINAL_MONTH_REPORT_PROMPT;}
        else{return GamePrompts.END_OF_MONTH_REPORT_PROMPT;}
    }

    @Override
    public String confirmChoice(int currentMonth) {
        return GamePrompts.CONFIRM_UPGRADE;
    }

    @Override
    public String getInternsInfo(){
        return GamePrompts.INTERN_INFO_HEADER + currentHRSystem.getHiredInternsNames();
    }

    @Override
    public String getProjectInfo(int currentMonth) {
        return GamePrompts.PROJECT_NAME_HEADER + currentPMSystem.makeProjectsToString(currentMonth);
    }

    public String upgradeIntern(String internName, int currentMonth, String randomSkillThisMonth) throws Exception {
        boolean success = currentHRSystem.upgradeInternSkill(internName, currentMonth,randomSkillThisMonth);
        if (!success){throw new Exception(Exceptions.INTERN_UPGRADING_FAILURE);}
        return GamePrompts.INTERN_UPGRADING_SUCCESS;
    }

    @Override
    public String getUpgradingInfo(int currentMonth) {
        return currentPMSystem.makeUpgradeToString(currentMonth);
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
