package UseCases;

import Entities.Intern;
import Entities.Project;

import java.util.ArrayList;
import java.util.HashMap;

//An interface class that shows all common methods used to make reports:
public interface ReportMaker {
    /**
     * Make a header for the report corresponding to the current phase (month) the player is in
     *
     * @param month a month in this game
     * @return correctly formatted header for the report based on the current phase (month)
     */
    String makeReportHeader (int month);


    /**
     * Make an intro for the report corresponding to the current phase (month) the player is in
     *
     * @return correctly formatted intro for the report based on the current phase (month)
     */
    String makeReportIntro ();


    /**
     * Make a body for the report corresponding to the current phase (month) the player is in
     *
     * @param projectName // do we need to parameter?
     * @param projectProgress an int between 0 and 10 that indicates how well the project is going
     * @param interns the list of interns that were assigned to the project of which this Reportmaker is trying to make a report
     * @param projectSkill the project's skill compatibility used to calculate the intern performance on the project
     * @return correctly formatted body for the report based on the current phase (month)
     */
    String makeReportBody (String projectName, int projectProgress, String internNames, HashMap<String, Integer> projectSkill, ArrayList<HashMap<String, Integer>>  internSkills) ;


    /**
     * Make a conclusion for the report corresponding to the current phase (month) the player is in
     *
     * @return correctly formatted conclusion for the report based on the current phase (month)
     */
    String makeReportConclusion();


    // Hi Jacob, I'm not sure what these "back" methods do. Do you mind writing out the java docs for them yourself?
    String bakeProjectName (String projectName);



    String bakeProgress (int projectProgress);



    String bakeInterns (String internNames);



    int calculateInternPerformance (HashMap<String, Integer> internSkills, HashMap<String, Integer> projectSkill);



    String bakeInternsPerformances (
            String internNames,
            ArrayList<HashMap<String, Integer>>  internSkills,
            HashMap<String, Integer> projectSkill);

}
