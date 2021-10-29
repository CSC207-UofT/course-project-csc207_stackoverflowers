package UseCases;
import Entities.GamePrompts;
import Entities.Intern;
import Entities.Project;

import java.util.ArrayList;
import java.util.Set;

public class ProjectReportMaker implements ReportMaker{
    private final GamePrompts prompts;

    public ProjectReportMaker(){
        this.prompts = new GamePrompts();
    }
    //TODO: Make every string a call to GamePrompt
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
