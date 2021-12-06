package UseCases;
import Entities.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("DuplicatedCode")
public class FinalReportMaker implements ReportMakerFinal {
    private final GamePrompts prompts;
    private final HRSystem currentHRSystem;
    private final PMSystem currentPMSystem;

    public FinalReportMaker(HRSystem currentHRSystem, PMSystem currentPMSystem) {
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

    @Override
    public String makeReportBody(int currentMonth) {
        List<Project> projList = currentPMSystem.getProjects(currentMonth);
        HashMap<String, Float> projectCompatibilityList = new HashMap<>();
        for (Project proj : projList) {
            projectCompatibilityList.putAll(proj.getSkillsCompatibilities());
        }
        return bakeProjectName(currentPMSystem.getProjectNames(currentMonth).split("\\|")[0]) + "\n" +
                bakeInterns(currentPMSystem.getInternNamesProject(projList.get(0))) + "\n" +
                bakeInternsPerformances(currentPMSystem.getProjectToInterns().get(projList.get(0)),
                        projectCompatibilityList) + "\n" ;
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
    public String bakeInternsPerformances(ArrayList<HiredIntern> interns, HashMap<String, Float> projectSkill) {
        StringBuilder res = new StringBuilder();
        for (Intern i : interns) {
            res.append(i.getInternName());
            res.append("|");
        }
        StringBuilder returnLine = new StringBuilder(GamePrompts.INTERN_PERFORMANCE_HEADER + res + "\n");
        String[] internNamesList = res.toString().split("\\|");
        for (int i = 0; i != interns.size(); i += 1) {
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
            effectiveSkills.add(internSkill * compatibility);
        }
        for (Double number : effectiveSkills) {
            result += number;
        }
        return result / effectiveSkills.size();
    }

    @Override
    public String makeReportConclusion() {
        return prompts.REPORT_CONCLUSION;
    }


    @Override
    public String endOfMonthPrompt(int currentMonth) {
        if (currentMonth == HRSystem.FINAL_MONTH) {
            return GamePrompts.END_OF_FINAL_MONTH_REPORT_PROMPT;
        } else {
            return GamePrompts.END_OF_MONTH_REPORT_PROMPT;
        }
    }

    @Override
    public String confirmChoice(int currentMonth) {
        return GamePrompts.CONFIRM_UPGRADE;
    }

    @Override
    public String getInternsInfo() {
        return GamePrompts.INTERN_INFO_HEADER + currentHRSystem.getHiredInternsNames();
    }

    @Override
    public String getProjectInfo(int currentMonth) {
        return GamePrompts.PROJECT_NAME_HEADER + currentPMSystem.makeProjectsToString(currentMonth);
    }
}