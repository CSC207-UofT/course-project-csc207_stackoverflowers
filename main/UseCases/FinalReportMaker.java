package UseCases;
import Entities.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("DuplicatedCode")
public class FinalReportMaker implements ReportMaker {
    private final GamePrompts prompts;
    private final HRSystem currentHRSystem;
    private PMSystem currentPMSystem;

    public  FinalReportMaker(HRSystem currentHRSystem, PMSystem currentPMSystem){
        this.prompts = new GamePrompts();
        this.currentHRSystem = currentHRSystem;
        this.currentPMSystem = currentPMSystem;

    }
    @Override
    public String makeReportHeader(int month) {
        return GamePrompts.REPORT_HEADER + month + "\n";
    }

    @Override
    public String makeReportIntro() {
        return null;
    }

    //这个格式和其他那两个差不多，但别忘了这是最后的project，所以只有一个intern（见mary在discord上发的照片）
    @Override
    public String makeReportBody( int currentMonth) {
        ArrayList<HiredIntern> interns = currentHRSystem.getHiredInternList();

        List<Project> projList = currentPMSystem.getProjects(currentMonth);
        HashMap<String, Float> projectCompatibilityList = new HashMap<>();
        for (Project proj : projList) {
            projectCompatibilityList.putAll(proj.getSkillsCompatibilities());
        }
        return bakeProjectName(currentPMSystem.getProjectNames(currentMonth)) + "\n" +
                bakeInterns(currentHRSystem.getHiredInternsNames()) + "\n" +
                bakeInternsPerformances(interns,  projectCompatibilityList);
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
    public int calculateInternPerformance(HashMap<String,Double> internSkills, HashMap<String, Float> projectSkill) {
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
        return prompts.REPORT_CONCLUSION;
    }

    @Override
    public String endOfMonthPrompt( int currentMonth) {
        if (currentMonth == HRSystem.FINAL_MONTH){return GamePrompts.END_OF_FINAL_MONTH_REPORT_PROMPT;}
        else{return GamePrompts.END_OF_MONTH_REPORT_PROMPT;}
    }

    @Override
    public String confirmChoice(int currentMonth) {
        return GamePrompts.CONFIRM_ASSIGNING + currentPMSystem.makeAssignmentToString(currentMonth);
    }

    @Override
    public String getInternsInfo(){
        return GamePrompts.INTERN_INFO_HEADER + currentHRSystem.getHiredInternsNames();
    }

    @Override
    public String getProjectInfo(int currentMonth) {
        return GamePrompts.PROJECT_NAME_HEADER + currentPMSystem.makeProjectsToString(currentMonth);
    }

    @Override
    public String upgradeIntern(String internName, int currentMonth, String randomSkillThisMonth) throws Exception {
        boolean success = currentHRSystem.upgradeInternSkill(internName, currentMonth, randomSkillThisMonth);
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
        return GamePrompts.MAKE_UPGRADE_PROMPT_FINAL_MONTH;
    }
}
