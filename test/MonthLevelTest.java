import ControllersPresenters.MonthLevel;
import Entities.*;
import UseCases.HRSystem;
import UseCases.PMSystem;
import UseCases.MonthMaker;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;


public class MonthLevelTest {
    MonthLevel monthLevel;
    HRSystem hrSystem;
    PMSystem pmSystem;
    MonthMaker monthMaker;

    @Test
    public void testStartOfMonthPrompt() throws Exception {
        hrSystem = new HRSystem();
        pmSystem = new PMSystem(hrSystem);
        hrSystem.updateHiredInternList(makeInterns());
        pmSystem.updateProjectList(makeProjects());
        monthLevel = new MonthLevel(1, hrSystem, pmSystem);
        String actual = monthLevel.getOutputString("Yup");
        monthMaker = new MonthMaker(hrSystem, pmSystem,1);
        String expected = monthMaker.startOfMonthPrompt();
        assertEquals(expected, actual);
    }

    @Test
    public void testEndPrompt() throws Exception {
        hrSystem = new HRSystem();
        pmSystem = new PMSystem(hrSystem);
        hrSystem.updateHiredInternList(makeInterns());
        pmSystem.updateProjectList(makeProjects());
        monthLevel = new MonthLevel(1, hrSystem, pmSystem);
        monthLevel.getIntoLevel();
        String actual = monthLevel.getOutputString("confirm all decisions");
        monthMaker = new MonthMaker(hrSystem, pmSystem,1);
        String expected = monthMaker.endOfMonthPrompt();
        assertEquals(expected, actual);
    }


    public void setUp() throws Exception {
        //Set tup the hrSystem so that it has some interns and projects in it.
        this.hrSystem = new HRSystem();
        this.pmSystem = new PMSystem(hrSystem);
        hrSystem.updateHiredInternList(makeInterns());
        pmSystem.updateProjectList(makeProjects());
        monthMaker = new MonthMaker(hrSystem, pmSystem,1);
        monthLevel = new MonthLevel(1, hrSystem, pmSystem);
        monthLevel.getOutputString("yo");//Gets the first output so that now it can make stuff go.
    }

    @Test
    public void testInvalidCommand() throws Exception {
        setUp();
        try {
            monthLevel.getOutputString("check assign");
        } catch (Exception e) {
            assertEquals(e.toString(),Exceptions.INVALID_COMMAND);
        }
    }

    @Test
    public void testCheckAssign() throws Exception {
        setUp();
        String actual = monthLevel.getOutputString("check assign");
        String expected = monthMaker.getAssigningInfo();
        assertEquals(actual, expected);
    }

    @Test
    public void testCheckInterns() throws Exception {
        setUp();
        String actual = monthLevel.getOutputString("check interns info");
        String expected = monthMaker.getInternsInfo();
        assertEquals(actual, expected);
    }

    @Test
    public void testCheckProjects() throws Exception {
        setUp();
        String actual = monthLevel.getOutputString("check project info");
        String expected = monthMaker.getProjectInfo();
        assertEquals(actual, expected);
    }

    @Test
    public void testAssignInternToProjectInvalidCommand() throws Exception {
        setUp();
        try {
            monthLevel.getOutputString("assign intern to project");
        } catch (Exception e) {
            assertEquals(e.getMessage(), Exceptions.INVALID_COMMAND);
        }
    }

    @Test
    public void testAssignInternToProjectFail() throws Exception {
        setUp();
        try {
            monthLevel.getOutputString("assign intern to project Mary ");
        } catch (Exception e) {
            assertTrue(e.toString().contains(Exceptions.INTERN_ASSIGNING_FAILURE));
        }
    }

    @Test
    public void testRemoveInternFromProjectSuccess() throws Exception {
        setUp();
        monthLevel.getOutputString("assign intern to project Mary Logo Design");
        String actual = monthLevel.getOutputString("remove intern from project Mary Logo Design");
        String expected = GamePrompts.INTERN_REMOVING_SUCCESS;
    }

    @Test
    public void testRemoveInternFromProjectInvalidCommand() throws Exception {
        setUp();
        try {
            monthLevel.getOutputString("remove intern from project");
        } catch (Exception e) {
            assertTrue(e.toString().contains(Exceptions.INVALID_COMMAND));
        }
    }

    @Test
    public void testRemoveInternFromProjectInvalidCommand2() throws Exception {
        setUp();
        try {
            monthLevel.getOutputString("remove intern from project Bob Logo Design");
        } catch (Exception e) {
            assertTrue(e.toString().contains(Exceptions.INTERN_REMOVING_FAILURE));
        }
    }

    @Test
    public void testConfirmDecisions() throws Exception {
        //Test that prompt asking to confirm decisions will actually appear
        // if all Hiredinterns have been assigned to a project.
        setUp();
        pmSystem.assignInternToProject("Mary", GamePrompts.PROJECT1_NAME);
        pmSystem.assignInternToProject("Maggie", GamePrompts.PROJECT1_NAME);
        pmSystem.assignInternToProject("Ruby", GamePrompts.PROJECT1_NAME);
        pmSystem.assignInternToProject("Bob", GamePrompts.PROJECT2_NAME);
        pmSystem.assignInternToProject("Kevin", GamePrompts.PROJECT2_NAME);
        pmSystem.assignInternToProject("Mike", GamePrompts.PROJECT2_NAME);
        String actual = monthLevel.getOutputString("huh");
        String expected = monthMaker.confirmChoice();
        assertEquals(expected, actual);
    }

    private ArrayList<Project> makeProjects() throws FileNotFoundException {
        //A helper function that sets up the Projects in HRSystem for the test.
        //Sets up all projects needed since it is never the case that only some months projects are settup.
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

        HashMap<String, Double> bobSkills = new HashMap<>();
        bobSkills.put("Flexibility", 50.0);
        HiredIntern Bob = new HiredIntern("Bob", 60, bobSkills);

        HashMap<String, Double> kevinSkills = new HashMap<>();
        bobSkills.put("Flexibility", 50.0);
        HiredIntern Kevin = new HiredIntern("Kevin", 60, kevinSkills);

        HashMap<String, Double> mikeSkills = new HashMap<>();
        bobSkills.put("Flexibility", 50.0);
        HiredIntern Mike = new HiredIntern("Mike", 60, mikeSkills);
        //Make a new list of interns to put in HRSystem to update:
        ArrayList<HiredIntern> interns = new ArrayList<>();
        interns.add(Mary);
        interns.add(Maggie);
        interns.add(Ruby);
        interns.add(Bob);
        interns.add(Kevin);
        interns.add(Mike);
        return interns;
    }
}