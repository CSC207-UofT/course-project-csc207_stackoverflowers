import ControllersPresenters.MonthLevel;
import Entities.*;
import UseCases.HRSystem;
import UseCases.MonthMaker;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;


public class MonthLevelTest {
    MonthLevel monthLevel;
    HRSystem hrSystem;
    MonthMaker monthMaker;

    @Test
    public void testStartOfMonthPrompt() throws Exception {
        hrSystem = new HRSystem();
        hrSystem.updateHiredInternList(makeInterns());
        hrSystem.updateProjectList(makeProjects());
        monthLevel = new MonthLevel(1, hrSystem);
        String actual = monthLevel.getOutputString("Yup");
        monthMaker = new MonthMaker(hrSystem, 1);
        String expected = monthMaker.startOfMonthPrompt();
        assertEquals(expected, actual);
    }

    @Test
    public void testEndPrompt() throws Exception {
        hrSystem = new HRSystem();
        hrSystem.updateHiredInternList(makeInterns());
        hrSystem.updateProjectList(makeProjects());
        monthLevel = new MonthLevel(1, hrSystem);
        String actual = monthLevel.getOutputString("confirm all decisions");
        monthMaker = new MonthMaker(hrSystem, 1);
        String expected = monthMaker.endOfMonthPrompt();
        assertEquals(actual, expected);
    }


    public void setUp() throws Exception {
        //Set tup the hrSystem so that it has some interns and projects in it.
        HRSystem hrSystem = new HRSystem();
        hrSystem.updateHiredInternList(makeInterns());
        hrSystem.updateProjectList(makeProjects());
        monthMaker = new MonthMaker(hrSystem, 1);
        monthLevel = new MonthLevel(1, hrSystem);
        monthLevel.getOutputString("yo");//Gets the first output so that now it can make stuff go.
    }

    @Test
    public void testInvalidCommand() throws Exception {
        setUp();
        try {
            monthLevel.getOutputString("check assign");
        } catch (Exception e) {
            assertEquals(e.getMessage(), Exceptions.INVALID_COMMAND);
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
            assertEquals(e.getMessage(), Exceptions.INVALID_COMMAND);
        }
    }

    @Test
    public void testRemoveInternFromProjectInvalidCommand() throws Exception {
        setUp();
        try {
            monthLevel.getOutputString("remove intern from project");
        } catch (Exception e) {
            assertEquals(e.getMessage(), Exceptions.INVALID_COMMAND);
        }
    }

    @Test
    public void testRemoveInternFromProjectInvalidCommand2() throws Exception {
        setUp();
        try {
            monthLevel.getOutputString("remove intern from project Bob Logo Design");
        } catch (Exception e) {
            assertEquals(e.getMessage(), Exceptions.INVALID_COMMAND);
        }
    }

    @Test
    public void testConfirmDecisions() throws Exception {
        //Test that prompt asking to confirm decisions will actually appear
        // if all Hiredinterns have been assigned to a project.
        setUp();
        hrSystem.assignInternToProject("Mary", GamePrompts.PROJECT1_NAME);
        hrSystem.assignInternToProject("Maggie", GamePrompts.PROJECT1_NAME);
        hrSystem.assignInternToProject("Ruby", GamePrompts.PROJECT1_NAME);
        String actual = monthLevel.getOutputString("huh");
        String expected = monthMaker.confirmChoice();
        assertEquals(actual, expected);
    }

    private ArrayList<Project> makeProjects() throws FileNotFoundException {
        //A helper function that sets up the Projects in HRSystem for the test.
        //Only one project for the first month.
        Project project1 = new Project(GamePrompts.PROJECT1_NAME);
        ArrayList<Project> projects = new ArrayList<>();
        projects.add(project1);
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

        //Also add a interviewIntern that shouldn't be used anywhere during month:
        HashMap<String, Double> bobSkills = new HashMap<>();
        bobSkills.put("Flexibility", 50.0);
        Intern Bob = new HiredIntern("Bob", 60, bobSkills);
        //Make a new list of interns to put in HRSystem to update:
        ArrayList<HiredIntern> interns = new ArrayList<>();
        interns.add(Mary);
        interns.add(Maggie);
        interns.add(Ruby);
        return interns;
    }
}