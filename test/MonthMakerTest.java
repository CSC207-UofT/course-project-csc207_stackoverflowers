import Entities.*;
import UseCases.HRSystem;
import UseCases.MonthMaker;
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

    @Before
    public void setup() throws FileNotFoundException {
        hrSystem = new HRSystem();
        hrSystem.updatePlayerName("Wahaha");
        hrSystem.updateHiredInternList(makeInterns());
        hrSystem.updateProjectList(makeProjects()); //Just one project for the first month

    }

    @Test
    public void testStartOfMonthPromptFinalMonth(){
        monthMaker = new MonthMaker(hrSystem, 5);
        String actual = monthMaker.startOfMonthPrompt();
        String expected = GamePrompts.FINAL_MONTH_PROMPT_BEFORE_PROJECT + monthMaker.getProjectInfo() +
                GamePrompts.FINAL_MONTH_PROMPT_AFTER_PROJECT;
        assertEquals(expected, actual);
    }

    @Test
    public void testStartOfMonthPromptNotFinalMonth(){
        monthMaker = new MonthMaker(hrSystem, 1);
        String actual = monthMaker.startOfMonthPrompt();
        String expected = GamePrompts.START_OF_MONTH_PROMPT_BEFORE_NAME + GamePrompts.START_OF_MONTH_PROMPT_BEFORE_NAME +
                "Wahaha" +
                GamePrompts.START_OF_MONTH_PROMPT_AFTER_NAME +
                monthMaker.getProjectInfo() +
                GamePrompts.START_OF_MONTH_PROMPT_AFTER_PROJECTS;
        assertEquals(expected, actual);
    }

    @Test
    public void testEndOfMonthPromptFinalMonth(){
        monthMaker = new MonthMaker(hrSystem, 5);
        String actual = monthMaker.endOfMonthPrompt();
        String expected = GamePrompts.END_OF_FINAL_MONTH_PROMPT;
        assertEquals(expected, actual);
    }

    @Test
    public void testEndOfMonthPromptNotFinalMonth(){
        monthMaker = new MonthMaker(hrSystem, 3);
        String actual = monthMaker.endOfMonthPrompt();
        String expected = GamePrompts.END_OF_MONTH_PROMPT;
        assertEquals(expected, actual);
    }

    @Test
    public void testAssignInternToProjectFailure(){
        monthMaker = new MonthMaker(hrSystem, 1);
        try {
            monthMaker.assignInternToProject("Mara", GamePrompts.PROJECT1_NAME);
        }catch (Exception e){
            assertEquals(e.getMessage(), Exceptions.INTERN_ASSIGNING_FAILURE);
        }
    }

    @Test
    public void testAssignInternToProjectFailure2(){
        monthMaker = new MonthMaker(hrSystem, 1);
        try {
            monthMaker.assignInternToProject("Bob", GamePrompts.PROJECT1_NAME);
            //Since Bob is not a hired intern, should return failure.
        }catch (Exception e){
            assertEquals(e.getMessage(), Exceptions.INTERN_ASSIGNING_FAILURE);
        }
    }

    @Test
    public void testRemoveInternFromProjectFailure(){
        monthMaker = new MonthMaker(hrSystem, 1);
        try {
            monthMaker.assignInternToProject("Mary", GamePrompts.PROJECT1_NAME);
            monthMaker.removeInternFromProject("Mary", GamePrompts.PROJECT2_NAME);
            //Since Mary was not yet assigned to project2, should return failure.
        }catch (Exception e){
            assertEquals(e.getMessage(), Exceptions.INTERN_REMOVING_FAILURE);
        }
    }

    @Test
    public void testCheckAllInternsAssigned(){
        monthMaker = new MonthMaker(hrSystem, 1);
        hrSystem.assignInternToProject("Mary", GamePrompts.PROJECT1_NAME);
        hrSystem.assignInternToProject("Maggie", GamePrompts.PROJECT1_NAME);
        hrSystem.assignInternToProject("Ruby", GamePrompts.PROJECT1_NAME);
        assertTrue(monthMaker.checkAllInternsAssigned());
    }

    @Test
    public void testGetAssigningInfo(){
        monthMaker = new MonthMaker(hrSystem, 1);
        String actual = monthMaker.getAssigningInfo();
        String expected = hrSystem.makeAssignmentToString(1);
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

        ArrayList<HiredIntern> interns = new ArrayList<>();
        interns.add(Mary);
        interns.add(Maggie);
        interns.add(Ruby);
        return interns;
    }

}