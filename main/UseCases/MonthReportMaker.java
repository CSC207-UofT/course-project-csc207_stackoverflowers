package UseCases;
import Entities.GamePrompts;
import Entities.Intern;
import Entities.Project;

import java.util.ArrayList;
import java.util.Set;

public class MonthReportMaker implements ReportMaker {
    private final GamePrompts prompts;

    public MonthReportMaker(){
        this.prompts = new GamePrompts();
    }
    //TODO: Make every string a call to GamePrompt
    @Override
    public String makeReportHeader(int month) {
        return "Here is your report for the end of " + month + "\n";
    }


    @Override
    public String makeReportIntro() {

        return "This is an intermediate report of this ongoing project." + "\n";
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
