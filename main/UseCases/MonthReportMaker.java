package UseCases;
import Entities.GamePrompts;
import Entities.Intern;
import Entities.Project;

import java.util.ArrayList;
import java.util.HashMap;
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
    public String makeReportBody(String projectName, int projectProgress, String internNames,  HashMap<String, Integer> projectSkill, ArrayList<HashMap<String, Integer>>  internSkills) {
        return bakeProjectName(projectName) + "\n" +
                bakeProgress(projectProgress)+"\n"+
        bakeInterns(internNames) + "\n" +
        bakeInternsPerformances(internNames,internSkills, projectSkill);
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
