import Entities.*;
import UseCases.HRSystem;
import UseCases.MonthMaker;
import UseCases.PMSystem;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;


public class MonthMakerTest {
    MonthMaker monthMaker;
    HRSystem hrSystem;
    PMSystem pmSystem;

    @Before
    public void setup() throws Exception {
        hrSystem = new HRSystem();
        pmSystem = new PMSystem(hrSystem);
        pmSystem.setCurrentMonth(1);
        hrSystem.updatePlayerName("Wahaha");
        hrSystem.updateHiredInternList(makeInterns());
        pmSystem.updateProjectList(makeProjects()); //Just one project for the first month

    }

    @Test
    public void testStartOfMonthPromptFinalMonth(){
        monthMaker = new MonthMaker(hrSystem, pmSystem,5);
        String actual = monthMaker.startOfMonthPrompt();
        String expected = GamePrompts.FINAL_MONTH_PROMPT_BEFORE_PROJECT + monthMaker.getProjectInfo() +
                GamePrompts.AVAILABLE_COMMANDS_IN_MONTH;
        assertEquals(expected, actual);
    }

    @Test
    public void testStartOfMonthPromptNotFinalMonth(){
        monthMaker = new MonthMaker(hrSystem, pmSystem,1);
        String actual = monthMaker.startOfMonthPrompt();
        String expected = GamePrompts.START_OF_MONTH_PROMPT_BEFORE_NAME +
                "Wahaha" +
                GamePrompts.START_OF_MONTH_PROMPT_AFTER_NAME +
                monthMaker.getProjectInfo() +
                GamePrompts.AVAILABLE_COMMANDS_IN_MONTH;
        assertEquals(actual, expected);
    }

    @Test
    public void testEndOfMonthPromptFinalMonth(){
        monthMaker = new MonthMaker(hrSystem, pmSystem,5);
        String actual = monthMaker.endOfMonthPrompt();
        String expected = GamePrompts.END_OF_FINAL_MONTH_PROMPT;
        assertEquals(expected, actual);
    }

    @Test
    public void testEndOfMonthPromptNotFinalMonth(){
        monthMaker = new MonthMaker(hrSystem, pmSystem,3);
        String actual = monthMaker.endOfMonthPrompt();
        String expected = GamePrompts.END_OF_MONTH_PROMPT;
        assertEquals(expected, actual);
    }

    @Test
    public void testAssignInternToProjectFailure(){
        monthMaker = new MonthMaker(hrSystem, pmSystem,1);
        try {
            monthMaker.assignInternToProject("Mara", GamePrompts.PROJECT1_NAME);
        }catch (Exception e){
            assertEquals(e.getMessage(), Exceptions.INTERN_ASSIGNING_FAILURE);
        }
    }

    @Test
    public void testAssignInternToProjectFailure2(){
        monthMaker = new MonthMaker(hrSystem, pmSystem,1);
        try {
            monthMaker.assignInternToProject("Bob", GamePrompts.PROJECT1_NAME);
            //Since Bob is not a hired intern, should return failure.
        }catch (Exception e){
            assertEquals(e.getMessage(), Exceptions.INTERN_ASSIGNING_FAILURE);
        }
    }

    @Test
    public void testRemoveInternFromProjectFailure(){
        monthMaker = new MonthMaker(hrSystem, pmSystem, 1);
        try {
            monthMaker.assignInternToProject("Mary", GamePrompts.PROJECT1_NAME);
            monthMaker.removeInternFromProject("Mary", GamePrompts.PROJECT2_NAME);
            //Since Mary was not yet assigned to project2, should return failure.
        }catch (Exception e){
            assertEquals(Exceptions.INTERN_REMOVING_FAILURE, e.getMessage());
        }
    }

    @Test
    public void testCheckAllInternsAssigned(){
        monthMaker = new MonthMaker(hrSystem, pmSystem,1);
        pmSystem.setCurrentMonth(1);
        pmSystem.assignInternToProject("Mary", GamePrompts.PROJECT1_NAME);
        pmSystem.assignInternToProject("Maggie", GamePrompts.PROJECT1_NAME);
        pmSystem.assignInternToProject("Ruby", GamePrompts.PROJECT1_NAME);
        pmSystem.assignInternToProject("Maryy", GamePrompts.PROJECT2_NAME);
        pmSystem.assignInternToProject("Maggiee", GamePrompts.PROJECT2_NAME);
        pmSystem.assignInternToProject("Rubyy", GamePrompts.PROJECT2_NAME);

        assertTrue(monthMaker.checkAllInternsAssigned());
    }

    @Test
    public void testCheckAllInternsAssignedFalse(){
        monthMaker = new MonthMaker(hrSystem, pmSystem,1);
        pmSystem.setCurrentMonth(1);
        pmSystem.assignInternToProject("Mary", GamePrompts.PROJECT1_NAME);
        pmSystem.assignInternToProject("Maggie", GamePrompts.PROJECT1_NAME);
        pmSystem.assignInternToProject("Ruby", GamePrompts.PROJECT1_NAME);
        assertTrue(! monthMaker.checkAllInternsAssigned());
    }

    @Test
    public void testGetAssigningInfo(){
        monthMaker = new MonthMaker(hrSystem, pmSystem, 1);
        String actual = monthMaker.getAssigningInfo();
        String expected = pmSystem.makeAssignmentToString(1);
        assertEquals(expected, actual);
    }


    private ArrayList<Project> makeProjects() throws FileNotFoundException {
        //A helper function that sets up the Projects in HRSystem for the test.
        //Only one project for the first month.
        Project project1 = new Project(GamePrompts.PROJECT1_NAME);
        Project project2 = new Project(GamePrompts.PROJECT2_NAME);
        Project project3 = new Project(GamePrompts.PROJECT3_NAME);
        Project project4 = new Project(GamePrompts.PROJECT4_NAME);
        Project project5 = new Project(GamePrompts.PROJECT5_NAME);
        ArrayList<Project> projects = new ArrayList<>();
        projects.add(project1);
        projects.add(project2);
        projects.add(project3);
        projects.add(project4);
        projects.add(project5);
        return projects;
    }

    private ArrayList<HiredIntern> makeInterns() {
        //A helper function that sets up the interns in HRSystem for the test.
        //Setting up two Hired interns
        HashMap<String, Double> marySkills = new HashMap<>();
        marySkills.put("Efficiency", 85.0);
        HiredIntern Mary = new HiredIntern("Mary", 19, marySkills);

        HashMap<String, Double> maggieSkills = new HashMap<>();
        maggieSkills.put("Responsible", 87.0);
        HiredIntern Maggie = new HiredIntern("Maggie", 20, maggieSkills);

        HashMap<String, Double> rubySkills = new HashMap<>();
        rubySkills.put("Communication", 66.0);
        HiredIntern Ruby = new HiredIntern("Ruby", 21, rubySkills);

        HashMap<String, Double> maryySkills = new HashMap<>();
        maryySkills.put("Efficiency", 88.0);
        HiredIntern Maryy = new HiredIntern("Maryy", 19, maryySkills);

        HashMap<String, Double> maggieeSkills = new HashMap<>();
        maggieeSkills.put("Responsible", 5.0);
        HiredIntern Maggiee = new HiredIntern("Maggiee", 20, maggieeSkills);

        HashMap<String, Double> rubyySkills = new HashMap<>();
        rubyySkills.put("Communication", 6.0);
        HiredIntern Rubyy = new HiredIntern("Rubyy", 21, rubyySkills);

        ArrayList<HiredIntern> interns = new ArrayList<>();
        interns.add(Mary);
        interns.add(Maggie);
        interns.add(Ruby);
        interns.add(Maryy);
        interns.add(Maggiee);
        interns.add(Rubyy);
        return interns;
    }

}