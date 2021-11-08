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
     * @param projectName name of the project, retrieved from HRSystem
     * @param projectProgress an int between 0 and 10 that indicates how well the project is going
     * @param internNames the intern names(not a list!) that were assigned to the project of which this ReportMaker is
     *                    trying to make a report
     * @param projectSkill the project's skill compatibility used to calculate the intern performance on the project
     * @param internSkills the interns' skill compatibility used to calculate the intern performance on the project
     * @return correctly formatted body for the report based on the current phase (month)
     */
    String makeReportBody (String projectName, int projectProgress, String internNames, HashMap<String, Integer> projectSkill, ArrayList<HashMap<String, Integer>>  internSkills) ;

    /**
     * Make a conclusion for the report corresponding to the current phase (month) the player is in
     *
     * @return correctly formatted conclusion for the report based on the current phase (month)
     */
    String makeReportConclusion();

    /**
     * Generate a string in the format of 'Project name: xxx' for makeReportBody to append
     *
     * @param projectName name of the project, retrieved from HRSystem
     *
     * @return a string in the format of 'Project name: xxx'
     */
    String bakeProjectName (String projectName);

    /**
     * Make a string in the format of 'Project progress: xxx' for makeReportBody to append
     *
     * @param projectProgress an int between 0 and 10 that indicates how well the project is going
     * @return  string in the format of 'Project progress: xxx'
     */
    String bakeProgress (int projectProgress);

    /**
     * Make a string in the format of 'Assigned interns: xxx|xxx|xxx|xxx|' for makeReportBody to append
     *
     * @param internNames the intern names(not a list!) that were assigned to the project of which this ReportMaker is trying to make a report
     * @return a string in the format of 'Assigned interns: xxx|xxx|xxx|xxx|'
     */
    String bakeInterns (String internNames);

    /**
     * A helper method, makes an int based on internSkills' compatibility with projectSkill for bakeInternsPerformances
     * to use
     *
     * @param projectSkill the project's skill compatibility used to calculate the intern performance on the project
     * @param internSkills the interns' skill compatibility used to calculate the intern performance on the project
     * @return an int based on sum[[each skill]*[skill compatibility]]/[number of skills]
     */
    int calculateInternPerformance (HashMap<String, Integer> internSkills, HashMap<String, Integer> projectSkill);

    /**
     * Generate the proper format of a report on each intern's performances on a project
     *
     * @param internNames the intern names(not a list!) that were assigned to the project of which this ReportMaker is trying to make a report
     * @param projectSkill the project's skill compatibility used to calculate the intern performance on the project
     * @param internSkills the interns' skill compatibility used to calculate the intern performance on the project
     * @return EX:
     *      Interns performances:
     *         - hhh: c
     *         - yyy: b
     *         - zzz: a
     */
    String bakeInternsPerformances (
            String internNames,
            ArrayList<HashMap<String, Integer>>  internSkills,
            HashMap<String, Integer> projectSkill);

}
