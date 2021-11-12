import ControllersPresenters.ReportLevel;
import Entities.GamePrompts;
import Entities.HiredIntern;
import Entities.Intern;
import Entities.Project;
import UseCases.HRSystem;
import UseCases.MonthReportMaker;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class MonthReportMakerTest {

    ReportLevel reportLevel;
    HRSystem hrSystem;
    MonthReportMaker reportMaker;

    @Test(timeout = 100)
    public void testMakeReportHeader(){
        String actual = reportMaker.makeReportHeader(1);
        String expected = GamePrompts.REPORT_HEADER + 1 + '\n';
        Assertions.assertEquals(actual, expected);
    }

    @Test(timeout = 100)
    public void testBakeProjectName(){
        String actual = reportMaker.bakeProjectName("a name");
        String expected = GamePrompts.PROJECT_NAME_HEADER + "a name";
        Assertions.assertEquals(actual, expected);
    }

    @Test(timeout = 100)
    public void testBakeProgress(){
        String actual = reportMaker.bakeProgress(1);
        String expected = GamePrompts.PROJECT_PROGRESS_HEADER + 1;
        Assertions.assertEquals(actual, expected);
    }

    @Test(timeout = 100)
    public void testEndOfMonthPromptNotFinal(){
        String actual = reportMaker.endOfMonthPrompt(1);
        String expected = GamePrompts.END_OF_MONTH_PROMPT;
        Assertions.assertEquals(actual, expected);
    }

    @Test(timeout = 100)
    public void testEndOfMonthPromptFinal(){
        String actual = reportMaker.endOfMonthPrompt(6);
        String expected = GamePrompts.END_OF_FINAL_MONTH_PROMPT;
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
            reportLevel = new ReportLevel(1, hrSystem);
            reportLevel.getOutputString("stuff");//Gets the first output so that now it can make stuff go.
        }

        @Test(timeout = 100)
        public void testCheckUpgradedTrue(){
            boolean actual = reportMaker.checkUpgraded(1);
            boolean expected = hrSystem.internUpgraded(1);
            Assertions.assertEquals(actual, expected);
        }

//        @Test(timeout = 100)
//        public void testGetUpgradingInfo() throws Exception {
//
//        }
//
//        @Test(timeout = 100)
//        public void testGetProjectInfo() throws Exception {
//            String actual = reportMaker.endOfMonthPrompt(6);
//            String expected = GamePrompts.END_OF_FINAL_MONTH_PROMPT;
//            Assertions.assertEquals(actual, expected);
//        }
//
//        @Test(timeout = 100)
//        public void testGetInternsInfo() throws Exception {
//            String actual = reportMaker.endOfMonthPrompt(6);
//            String expected = GamePrompts.END_OF_FINAL_MONTH_PROMPT;
//            Assertions.assertEquals(actual, expected);
//        }
//
//        @Test(timeout = 100)
//        public void testConfirmChoice() throws Exception {
//            String actual = reportMaker.endOfMonthPrompt(6);
//            String expected = GamePrompts.END_OF_FINAL_MONTH_PROMPT;
//            Assertions.assertEquals(actual, expected);
//        }
//
//        @Test(timeout = 100)
//        public void testBakeProgress() throws Exception {
//            String actual = reportMaker.bakeProgress(1);
//            String expected = GamePrompts.PROJECT_PROGRESS_HEADER + 1;
//            Assertions.assertEquals(actual, expected);
//        }
//
//
//        @Test(timeout = 100)
//        public void testGetInternSkills() throws Exception {
//            String actual = reportMaker.makeReportHeader(1);
//            String expected = GamePrompts.REPORT_HEADER + 1 + '\n';
//            Assertions.assertEquals(actual, expected);
//        }
//
//        @Test(timeout = 100)
//        public void testMakeReportBody() throws Exception {
//            String actual = reportMaker.makeReportBody(1, 1);
//            String expected = GamePrompts.REPORT_HEADER + 1 + '\n';
//            Assertions.assertEquals(actual, expected);
//        }

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
            marySkills.put("Efficiency", 100);
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
}
