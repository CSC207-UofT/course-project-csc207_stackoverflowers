import ControllersPresenters.ReportLevel;
import Entities.*;
import UseCases.HRSystem;
import UseCases.PMSystem;
import UseCases.MonthReportMaker;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class MonthReportMakerTest {

    ReportLevel reportLevel;
    HRSystem hrSystem;
    PMSystem pmSystem;
    MonthReportMaker reportMaker;
    @Before
    public void setup() throws FileNotFoundException {
        HRSystem hrSystem = new HRSystem();
        PMSystem pmSystem = new PMSystem();
        hrSystem.updateHiredInternList(makeInterns());
        pmSystem.updateProjectList(makeProjects());
        reportMaker = new MonthReportMaker(hrSystem, pmSystem);
    }

    @Test
    public void testMakeReportHeader() throws FileNotFoundException {
        setup();
        String actual = reportMaker.makeReportHeader(1);
        String expected = GamePrompts.REPORT_HEADER + 1 + '\n';
        assertEquals(actual, expected);
    }

    @Test
    public void testBakeProjectName() throws FileNotFoundException {
        setup();
        String actual = reportMaker.bakeProjectName("a name");
        String expected = GamePrompts.PROJECT_NAME_HEADER + "a name";
        assertEquals(actual, expected);
    }
    /*

    @Test
    public void testBakeProgress(){
        String actual = reportMaker.bakeProgress(1);
        String expected = GamePrompts.PROJECT_PROGRESS_HEADER + 1;
        assertEquals(actual, expected);
    }

     */

    @Test
    public void testEndOfMonthPromptNotFinal() throws FileNotFoundException {
        setup();
        String actual = reportMaker.endOfMonthPrompt(1);
        String expected = GamePrompts.END_OF_MONTH_REPORT_PROMPT;
        assertEquals(actual, expected);
    }

    @Test
    public void testEndOfMonthPromptFinal() throws FileNotFoundException {
        setup();
        String actual = reportMaker.endOfMonthPrompt(6);
        String expected = GamePrompts.END_OF_MONTH_REPORT_PROMPT;
        assertEquals(actual, expected);
    }

    class TestDuringProject {
        @Before
        public void setUp() throws Exception {
            //Set tup the hrSystem so that it has some interns and projects in it.
            HRSystem hrSystem = new HRSystem();
            hrSystem.updateHiredInternList(makeInterns());
            pmSystem.updateProjectList(makeProjects());
            reportMaker = new MonthReportMaker(hrSystem, pmSystem);
        }

        @Test
        public void testCheckUpgradedTrue() {
            boolean actual = reportMaker.checkUpgraded(1);
            boolean expected = hrSystem.internUpgraded(1);
            assertEquals(actual, expected);
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
        marySkills.put("Efficiency", 100.0);
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
        HiredIntern Bob = new HiredIntern("Bob", 60, bobSkills);
        //Make a new list of interns to put in HRSystem to update:
        ArrayList<HiredIntern> interns = new ArrayList<>();
        interns.add(Mary);
        interns.add(Maggie);
        interns.add(Ruby);
        interns.add(Bob);
        return interns;
    }

}
