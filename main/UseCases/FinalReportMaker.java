package UseCases;
import Entities.GamePrompts;
import Entities.Intern;
import Entities.Project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class FinalReportMaker implements ReportMaker {
    private final GamePrompts prompts;

    public  FinalReportMaker(){
        this.prompts = new GamePrompts();
    }
    //TODO: Make every string a call to GamePrompt
    @Override
    public String makeReportHeader(int month) {
        return "Here is your final project report!" + "\n";
    }

    @Override
    public String makeReportIntro() {
        return null;
    }

    //这个格式和其他那两个差不多，但别忘了这是最后的project，所以只有一个intern（见mary在discord上发的照片）
    @Override
    public String makeReportBody(String projectName, int projectProgress, String internNames, HashMap<String, Integer> projectSkill, ArrayList<HashMap<String, Integer>>  internSkills) {
        return bakeProjectName(projectName) + "\n" +
                bakeProgress(projectProgress)+"\n"+
                bakeInterns(internNames) + "\n" +
                bakeInternsPerformances(internNames, internSkills, projectSkill);
    }

    @Override
    public String bakeProjectName(String projectName) {
        return "Project name: " + projectName;
    }

    @Override
    public String bakeProgress(int projectProgress) {
        return "Project progress: " + projectProgress;
    }

    @Override
    public String bakeInterns(String internNames) {
        return internNames;
    }

    @Override
    public String bakeInternsPerformances (String internNames, ArrayList<HashMap<String, Integer>>  internSkills, HashMap<String, Integer> projectSkill) {
        StringBuilder returnLine = new StringBuilder("Assigned interns: " + internNames + "\n");
        String[] internNamesList = internNames.split("|");
        for (int i = 0; i != internNamesList.length; i+=1) {
            returnLine.append("     - ").append(internNamesList[i]).append(": ").append(calculateInternPerformance(internSkills.get(i), projectSkill)).append("\n");
        }
        return returnLine.toString();
    }

    @Override
    public int calculateInternPerformance(HashMap<String, Integer> internSkills, HashMap<String, Integer> projectSkill) {
        //TODO: Can't go on as of now, need my friends to make get method for skillsCompatability in Entities.Project
        int result = 0;
        return result;
    }

    @Override
    public String makeReportConclusion() {
        return prompts.REPORT_CONCLUSION;
    }
}
