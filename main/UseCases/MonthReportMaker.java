package UseCases;
import Entities.*;

import java.util.ArrayList;
import java.util.HashMap;

public class MonthReportMaker implements ReportMaker {
    private final GamePrompts prompts;
    private final HRSystem currentHRSystem;

    public MonthReportMaker(HRSystem currentHRSystem){
        this.prompts = new GamePrompts();
        this.currentHRSystem = currentHRSystem;

    }
    @Override
    public String makeReportHeader(int month) {
        return GamePrompts.REPORT_HEADER + month + '\n';
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
    public String makeReportBody(int projectProgress, int currentMonth) {
        String internNames = currentHRSystem.getInternNames();

        ArrayList<Project> projList = currentHRSystem.getProject(currentMonth);
        HashMap<String, Integer> projectCompatibilityList = new HashMap<>();
        for (Project proj : projList) {
            projectCompatibilityList.putAll(proj.getSkillsCompatibilities());
        }
        ArrayList<HashMap<String, Double>> internsSkills = getHiredInternsSkills(currentHRSystem.getHiredInternList());
        return bakeProjectName(currentHRSystem.getProjectName(currentMonth)) + "\n" +
                bakeProgress(projectProgress) + "\n" +
                bakeInterns(internNames) + "\n" +
                bakeInternsPerformances(internNames, internsSkills, projectCompatibilityList);
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
    public String bakeProgress(int projectProgress) {
        return GamePrompts.PROJECT_PROGRESS_HEADER + projectProgress;
    }

    @Override
    public String bakeInterns(String internNames) {
        return internNames;
    }

    @Override
    public String bakeInternsPerformances (String internNames,
                                           ArrayList<HashMap<String, Double>>  internSkills,
                                           HashMap<String, Integer> projectSkill) {
        StringBuilder returnLine = new StringBuilder(GamePrompts.INTERN_PERFORMANCE_HEADER + internNames + "\n");
        String[] internNamesList = internNames.split("|");
        for (int i = 0; i != internNamesList.length; i+=1) {
            returnLine.append("     - ")
                    .append(internNamesList[i])
                    .append(": ").append(calculateInternPerformance(internSkills.get(i), projectSkill))
                    .append("\n");
        }
        return returnLine.toString();
    }

    @Override
    public int calculateInternPerformance(HashMap<String, Double> internSkills,
                                          HashMap<String, Integer> projectSkill) {
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
        if (currentMonth == HRSystem.FINAL_MONTH){return GamePrompts.END_OF_FINAL_MONTH_PROMPT;}
        else{return GamePrompts.END_OF_MONTH_PROMPT;}
    }

    @Override
    public String confirmChoice(int currentMonth) {
        return GamePrompts.CONFIRM_ASSIGNING + currentHRSystem.makeAssignmentToString(currentMonth);
    }

    @Override
    public String getInternsInfo(){
        return
                GamePrompts.INTERN_INFO_HEADER + currentHRSystem.getHiredInternNames();
    }

    @Override
    public String getProjectInfo(int currentMonth) {
        return GamePrompts.PROJECT_NAME_HEADER + currentHRSystem.makeProjectsToString(currentMonth);
    }

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
        //returns true if the skill is maxed for the intern
        return currentHRSystem.internUpgraded(currentMonth);
    }
}
