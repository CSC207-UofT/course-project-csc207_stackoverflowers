package UseCases;
import java.lang.reflect.Array;
import Entities.*;
import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings("DuplicatedCode")
public class FinalReportMaker implements ReportMaker {
    private final GamePrompts prompts;
    private final HRSystem currentHRSystem;

    public  FinalReportMaker(HRSystem currentHRSystem){
        this.prompts = new GamePrompts();
        this.currentHRSystem = currentHRSystem;

    }
    @Override
    public String makeReportHeader(int month) {
        return GamePrompts.REPORT_HEADER;
    }

    @Override
    public String makeReportIntro() {
        return null;
    }

    //这个格式和其他那两个差不多，但别忘了这是最后的project，所以只有一个intern（见mary在discord上发的照片）
    @Override
    public String makeReportBody(int projectProgress, int currentMonth) {
        String internNames = currentHRSystem.getInternNames();

        ArrayList<Project> projList = currentHRSystem.getProject(currentMonth);
        HashMap<String, Float> projectCompatibilityList = new HashMap<>();
        for (Project proj : projList) {
            projectCompatibilityList.putAll(proj.getSkillsCompatibilities());
        }
        ArrayList<HashMap<String, Double>> internsSkills = getHiredInternsSkills(currentHRSystem.getHiredInternList());
        return bakeProjectName(currentHRSystem.getProjectName(currentMonth)) + "\n" +
                bakeProgress(projectProgress)+"\n"+
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
    public String bakeInternsPerformances (String internNames, ArrayList<HashMap<String, Double>>  internSkills, HashMap<String, Float> projectSkill) {
        StringBuilder returnLine = new StringBuilder(GamePrompts.INTERN_PERFORMANCE_HEADER + internNames + "\n");
        String[] internNamesList = internNames.split("|");
        for (int i = 0; i != internNamesList.length; i+=1) {
            returnLine.append("     - ").append(internNamesList[i]).append(": ").append(calculateInternPerformance(internSkills.get(i), projectSkill)).append("\n");
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
        return GamePrompts.INTERN_INFO_HEADER + currentHRSystem.getHiredInternNames();
    }

    @Override
    public String getProjectInfo(int currentMonth) {
        return GamePrompts.PROJECT_NAME_HEADER + currentHRSystem.makeProjectsToString(currentMonth);
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
