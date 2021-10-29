package UseCases;

import Entities.Intern;
import Entities.Project;

import java.util.ArrayList;

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
     * @param project the project of which this Reportmaker is trying to make a report
     * @return correctly formatted body for the report based on the current phase (month)
     */
    String makeReportBody (String projectName, int projectProgress, ArrayList<Intern> interns, Project project);


    /**
     * Make a conclusion for the report corresponding to the current phase (month) the player is in
     *
     * @return correctly formatted conclusion for the report based on the current phase (month)
     */
    String makeReportConclusion();


    // Hi Jacob, I'm not sure what these "back" methods do. Do you mind writing out the java docs for them yourself?
    String bakeProjectName (String projectName);



    String bakeProgress (int projectProgress);



    String bakeInterns (ArrayList<Intern> interns);



    int calculateInternPerformance (Intern intern, Project project);



    String bakeInternsPerformances (ArrayList<Intern> interns, Project project);



}
