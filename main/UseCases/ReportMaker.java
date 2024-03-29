package UseCases;

import Entities.HiredIntern;

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
     * @return correctly formatted body for the report based on the current phase (month)
     */
    String makeReportBody( int currentMonth);


    /**
     * Make a conclusion for the report corresponding to the current phase (month) the player is in
     *
     * @return correctly formatted conclusion for the report based on the current phase (month)
     */
    String makeReportConclusion();

    String bakeProjectName (String projectName);

    int calculateInternPerformance(HashMap<String, Double> internSkills,
                                   HashMap<String, Float> projectSkill);



    String bakeInternsPerformances (ArrayList<HiredIntern> interns, HashMap<String, Float> projectSkill);


    String endOfMonthPrompt(int currentMonth);

    String confirmChoice(int currentMonth);

    String getInternsInfo();

    String getProjectInfo(int currentMonth);

    String upgradeIntern(String internName, int currentMonth, String randomSkillThisMonth) throws Exception;

    String getUpgradingInfo(int currentMonth);

    boolean checkUpgraded(int currentMonth);

    String makeUpgradePrompt(String skillName);
}
