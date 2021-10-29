package UseCases;
import Entities.GamePrompts;
import Entities.Intern;
import Entities.Project;

import java.util.ArrayList;
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
    public String makeReportBody(String projectName, int projectProgress, ArrayList<Intern> interns, Project project) {
        return bakeProjectName(projectName) + "\n" +
                bakeProgress(projectProgress)+"\n"+
                bakeInterns(interns) + "\n" +
                bakeInternsPerformances(interns, project);
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
    public String bakeInterns(ArrayList<Intern> interns) {
        StringBuilder returnLine = new StringBuilder("Assigned interns: ");
        for (int i =0; i != interns.size(); i++){
            returnLine.append(interns.get(i)).append("|");
        }
        return returnLine.toString();
    }

    @Override
    public String bakeInternsPerformances (ArrayList<Intern> interns, Project project) {
        StringBuilder returnLine = new StringBuilder("Assigned interns: " + interns + "\n");
        for (int i = 0; i != interns.size(); i++){
            returnLine.append("     - ").append(interns.get(i)).append(": ").append(calculateInternPerformance(interns.get(i), project)).append("\n");
        }
        return returnLine.toString();
    }

    @Override
    public int calculateInternPerformance(Intern intern, Project project) {
        //TODO: Can't go on as of now, need my friends to make get method for skillsCompatability in Entities.Project
        int result = 0;
        Set<String> keys = intern.getInternSkills().keySet();
        for(Object item:keys){
            result += intern.getInternSkills().get(item);
        }
        return result;
    }

    @Override
    public String makeReportConclusion() {
        return prompts.REPORT_CONCLUSION;
    }
}
