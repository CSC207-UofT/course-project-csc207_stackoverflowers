package UseCases;

import Entities.*;
import UseCases.HRSystem;
import UseCases.MonthMaker;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class MonthMakerTest {
    MonthMaker monthMaker;
    HRSystem hrSystem;

    @Before
    public void setup() throws FileNotFoundException {
        hrSystem = new HRSystem();
        hrSystem.updatePlayerName("Wahaha");
        hrSystem.updateInternList(makeInterns());
        hrSystem.updateProjectList(makeProjects()); //Just one project for the first month

    }

    @Test(timeout = 100)
    public void testStartOfMonthPromptFinalMonth(){
        monthMaker = new MonthMaker(hrSystem, 5);
        String actual = monthMaker.startOfMonthPrompt();
        String expected = GamePrompts.FINAL_MONTH_PROMPT_BEFORE_PROJECT + monthMaker.getProjectInfo() +
                GamePrompts.FINAL_MONTH_PROMPT_AFTER_PROJECT;
        Assertions.assertEquals(expected, actual);
    }

    @Test(timeout = 100)
    public void testStartOfMonthPromptNotFinalMonth(){
        monthMaker = new MonthMaker(hrSystem, 1);
        String actual = monthMaker.startOfMonthPrompt();
        String expected = GamePrompts.START_OF_MONTH_PROMPT_BEFORE_NAME + GamePrompts.START_OF_MONTH_PROMPT_BEFORE_NAME +
                "Wahaha" +
                GamePrompts.START_OF_MONTH_PROMPT_AFTER_NAME +
                monthMaker.getProjectInfo() +
                GamePrompts.START_OF_MONTH_PROMPT_AFTER_PROJECTS;
        Assertions.assertEquals(expected, actual);
    }

    @Test(timeout = 100)
    public void testEndOfMonthPromptFinalMonth(){
        monthMaker = new MonthMaker(hrSystem, 5);
        String actual = monthMaker.endOfMonthPrompt();
        String expected = GamePrompts.END_OF_FINAL_MONTH_PROMPT;
        Assertions.assertEquals(expected, actual);
    }

    @Test(timeout = 100)
    public void testEndOfMonthPromptNotFinalMonth(){
        monthMaker = new MonthMaker(hrSystem, 3);
        String actual = monthMaker.endOfMonthPrompt();
        String expected = GamePrompts.END_OF_MONTH_PROMPT;
        Assertions.assertEquals(expected, actual);
    }

    @Test(timeout = 1000)
    public void testAssignInternToProjectFailure(){
        monthMaker = new MonthMaker(hrSystem, 1);
        try {
            monthMaker.assignInternToProject("Mara", GamePrompts.PROJECT1_NAME);
        }catch (Exception e){
            assertEquals(e.getMessage(), Exceptions.INTERN_ASSIGNING_FAILURE);
        }
    }

    @Test(timeout = 1000)
    public void testAssignInternToProjectFailure2(){
        monthMaker = new MonthMaker(hrSystem, 1);
        try {
            monthMaker.assignInternToProject("Bob", GamePrompts.PROJECT1_NAME);
            //Since Bob is not a hired intern, should return failure.
        }catch (Exception e){
            assertEquals(e.getMessage(), Exceptions.INTERN_ASSIGNING_FAILURE);
        }
    }

    @Test(timeout = 500)
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

    @Test(timeout = 500)
    public void testCheckAllInternsAssigned(){
        hrSystem.assignInternToProject("Mary", GamePrompts.PROJECT1_NAME);
        hrSystem.assignInternToProject("Maggie", GamePrompts.PROJECT1_NAME);
        hrSystem.assignInternToProject("Ruby", GamePrompts.PROJECT1_NAME);
        assertTrue(monthMaker.checkAllInternsAssigned());
    }

    @Test(timeout = 500)
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
        ArrayList<Project> projects = new ArrayList<>();
        projects.add(project1);
        return projects;
    }

    private ArrayList<Intern> makeInterns() {
        //A helper function that sets up the interns in HRSystem for the test.
        //Setting up two Hired interns
        HashMap<String, Integer> marySkills = new HashMap<>();
        marySkills.put("Efficiency", 85);
        Intern Mary = new HiredIntern("Mary", 19, marySkills);

        HashMap<String, Integer> maggieSkills = new HashMap<>();
        maggieSkills.put("Responsible", 87);
        Intern Maggie = new HiredIntern("Maggie", 20, maggieSkills);

        HashMap<String, Integer> rubySkills = new HashMap<>();
        rubySkills.put("Communication", 66);
        Intern Ruby = new HiredIntern("Ruby", 21, rubySkills);

        //Also add a interviewIntern that shouldn't be used anywhere during month:
        HashMap<String, Integer> bobSkills = new HashMap<>();
        bobSkills.put("Flexibility", 50);
        Intern Bob = new HiredIntern("Bob", 60, bobSkills);
        //Make a new list of interns to put in HRSystem to update:
        ArrayList<Intern> interns = new ArrayList<>();
        interns.add(Mary);
        interns.add(Maggie);
        interns.add(Ruby);
        interns.add(Bob);
        return interns;
    }

}