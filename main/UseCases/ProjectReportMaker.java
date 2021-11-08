package UseCases;
import Entities.GamePrompts;
import Entities.Intern;

import java.util.ArrayList;
import java.util.HashMap;

public class ProjectReportMaker implements ReportMaker{
    private final GamePrompts prompts;
    private final HRSystem currentHRSystem;

    public ProjectReportMaker(HRSystem currentHRSystem){
        this.prompts = new GamePrompts();
        this.currentHRSystem = currentHRSystem;
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
    public String makeReportBody(int projectProgress, int currentMonth) {
        String internNames = currentHRSystem.getInternNames();
        HashMap<String, Integer> projectCompatibilityList = currentHRSystem.getProject(currentMonth).getSkillsCompatibilities();
        ArrayList<HashMap<String, Integer>> internsSkills = getInternsSkills(currentHRSystem.getInternList(true));
        return bakeProjectName(currentHRSystem.getProjectName(currentMonth)) + "\n" +
                bakeProgress(projectProgress)+"\n"+
                bakeInterns(internNames) + "\n" +
                bakeInternsPerformances(internNames, internsSkills, projectCompatibilityList);
    }

    private ArrayList<HashMap<String, Integer>> getInternsSkills(ArrayList<Intern> internList) {
        //Makes an arrayList full of internSkills.
        ArrayList<HashMap<String, Integer>> internCompatabilityList  = new ArrayList<>();
        for (Intern i :internList){
            internCompatabilityList.add(i.getInternSkills());
        }
        return internCompatabilityList;
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
        int result = 0;
        ArrayList<Double> effectiveSkills = new ArrayList<Double>();
        for (String key : internSkills.keySet()) {
            int internSkill = internSkills.get(key);
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
}
