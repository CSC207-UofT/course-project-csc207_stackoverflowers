import ControllersPresenters.MonthLevel;
import Entities.*;
import UseCases.HRSystem;
import UseCases.MonthMaker;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;


public class MonthLevelTest {
    MonthLevel monthLevel;
    HRSystem hrSystem;
    MonthMaker monthMaker;

    @Test(timeout = 100)
    public void testStartOfMonthPrompt() throws Exception {
        monthLevel = new MonthLevel(1, hrSystem);
        String actual = monthLevel.getOutputString("Yup");
        String expected = monthMaker.startOfMonthPrompt();
        Assertions.assertEquals(expected, actual);
    }

    @Test(timeout = 100)
    public void testEndPrompt() throws Exception {
        monthLevel = new MonthLevel(1, hrSystem);
        String actual = monthLevel.getOutputString("confirm all decisions");
        String expected = monthMaker.endOfMonthPrompt();
        Assertions.assertEquals(actual, expected);
    }


    @Nested
    class TestDuringProject {
        @BeforeEach
        public void setUp() throws Exception {
            //Set tup the hrSystem so that it has some interns and projects in it.
            HRSystem hrSystem = new HRSystem();
            hrSystem.updateInternList(makeInterns());
            hrSystem.updateProjectList(makeProjects());
            monthMaker = new MonthMaker(hrSystem, 1);
            monthLevel = new MonthLevel(1, hrSystem);
            monthLevel.getOutputString("yo");//Gets the first output so that now it can make stuff go.
        }

        @Test(timeout = 100)
        public void testInvalidCommand() {
            try {
                monthLevel.getOutputString("checj assign");
            } catch (Exception e) {
                Assertions.assertEquals(e.getMessage(), Exceptions.INVALID_COMMAND);
            }
        }

        @Test(timeout = 100)
        public void testCheckAssign() throws Exception {
            String actual = monthLevel.getOutputString("check assign");
            String expected = monthMaker.getAssigningInfo();
            Assertions.assertEquals(actual, expected);
        }

        @Test(timeout = 100)
        public void testCheckInterns() throws Exception {
            String actual = monthLevel.getOutputString("check interns info");
            String expected = monthMaker.getInternsInfo();
            Assertions.assertEquals(actual, expected);
        }

        @Test(timeout = 100)
        public void testCheckProjects() throws Exception {
            String actual = monthLevel.getOutputString("check project info");
            String expected = monthMaker.getProjectInfo();
            Assertions.assertEquals(actual, expected);
        }

        @Test(timeout = 100)
        public void testAssignInternToProjectInvalidCommand() {
            try {
                monthLevel.getOutputString("assign intern to project");
            } catch (Exception e) {
                Assertions.assertEquals(e.getMessage(), Exceptions.INVALID_COMMAND);
            }
        }

        @Test(timeout = 100)
        public void testAssignInternToProjectFail() {
            try {
                monthLevel.getOutputString("assign intern to project Mary ");
            } catch (Exception e) {
                Assertions.assertEquals(e.getMessage(), Exceptions.INVALID_COMMAND);
            }
        }

        @Test(timeout = 100)
        public void testRemoveInternFromProjectInvalidCommand() {
            try {
                monthLevel.getOutputString("remove intern from project");
            } catch (Exception e) {
                Assertions.assertEquals(e.getMessage(), Exceptions.INVALID_COMMAND);
            }
        }

        @Test(timeout = 100)
        public void testRemoveInternFromProjectInvalidCommand2() {
            try {
                monthLevel.getOutputString("remove intern from project Bob Logo Design");
            } catch (Exception e) {
                Assertions.assertEquals(e.getMessage(), Exceptions.INVALID_COMMAND);
            }
        }

        @Test(timeout = 1000)
        public void testConfirmDecisions() throws Exception {
            //Test that prompt asking to confirm decisions will actually appear
            // if all Hiredinterns have been assigned to a project.
            hrSystem.assignInternToProject("Mary", GamePrompts.PROJECT1_NAME);
            hrSystem.assignInternToProject("Maggie", GamePrompts.PROJECT1_NAME);
            hrSystem.assignInternToProject("Ruby", GamePrompts.PROJECT1_NAME);
            String actual = monthLevel.getOutputString("huh");
            String expected = monthMaker.confirmChoice();
            Assertions.assertEquals(actual, expected);
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
            HashMap<String, Double> marySkills = new HashMap<>();
            marySkills.put("Efficiency", 85.0);
            Intern Mary = new HiredIntern("Mary", 19, marySkills);

            HashMap<String, Double> maggieSkills = new HashMap<>();
            maggieSkills.put("Responsible", 87.0);
            Intern Maggie = new HiredIntern("Maggie", 20, maggieSkills);

            HashMap<String, Double> rubySkills = new HashMap<>();
            rubySkills.put("Communication", 66.0);
            Intern Ruby = new HiredIntern("Ruby", 21, rubySkills);

            //Also add a interviewIntern that shouldn't be used anywhere during month:
            HashMap<String, Double> bobSkills = new HashMap<>();
            bobSkills.put("Flexibility", 50.0);
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
}