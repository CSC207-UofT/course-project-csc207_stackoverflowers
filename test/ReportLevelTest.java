import ControllersPresenters.ReportLevel;
import Entities.*;
import UseCases.*;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class ReportLevelTest {

    ReportLevel reportLevel;
    HRSystem hrSystem;

    @Test(timeout = 100)
    public void testReportSetup() {
        for (int num = 1; num != 7; num++) {
            reportLevel = new ReportLevel(num, hrSystem);
            if (num == 1 || num == 3 || num == 5) {
                Assertions.assertEquals(new MonthReportMaker(hrSystem), reportLevel.getCurrentReportMaker());
            }else if (num == 2 || num == 4) {
                Assertions.assertEquals(new ProjectReportMaker(hrSystem), reportLevel.getCurrentReportMaker());
            }else  {
                Assertions.assertEquals(new FinalReportMaker(hrSystem), reportLevel.getCurrentReportMaker());
            }
        }
    }

    @Test(timeout = 100)
    public void testEndPrompt() throws Exception {
        reportLevel = new ReportLevel(1, hrSystem);
        String actual = reportLevel.getOutputString("confirm all decisions");
        String expected = reportLevel.getCurrentReportMaker().endOfMonthPrompt(1);
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
        public void testFinishedUpgradeMatch() throws Exception {
            reportLevel = new ReportLevel(1, hrSystem);
            String actual = reportLevel.getOutputString("assign intern to upgrade Mary");
            String expected = reportLevel.getCurrentReportMaker().assignInternToUpgrade("Mary");
            Assertions.assertEquals(actual, expected);
        }

        @Test(timeout = 100)
        public void testFinishedUpgradeFail() {
            try {
                reportLevel = new ReportLevel(1, hrSystem);
                reportLevel.getCurrentReportMaker().assignInternToUpgrade("Mary");
            } catch (Exception e) {
                Assertions.assertEquals(e.getMessage, Exceptions.INTERN_UPGRADING_FAILURE);
            }
        }

        @Test(timeout = 100)
        public void testFinishedUpgradeSuccess() throws Exception {
            String actual = reportLevel.getOutputString("assign intern to upgrade Bob");
            String expected = GamePrompts.INTERN_UPGRADING_SUCCESS;
            Assertions.assertEquals(actual, expected);
        }

        @Test(timeout = 100)
        public void testCheckProjects() throws Exception {
            String actual = reportLevel.getOutputString("check project info");
            String expected = reportLevel.getCurrentReportMaker().getProjectInfo(1);
            Assertions.assertEquals(actual, expected);
        }

        @Test(timeout = 100)
        public void testCheckInterns() throws Exception {
            String actual = reportLevel.getOutputString("check interns info");
            String expected = reportLevel.getCurrentReportMaker().getInternsInfo();
            Assertions.assertEquals(actual, expected);
        }

        @Test(timeout = 100)
        public void testAssignUpgradeFail() {
            try {
                reportLevel.getOutputString("assign intern to project Mary");
            } catch (Exception e) {
                Assertions.assertEquals(e.getMessage(), Exceptions.INVALID_COMMAND);
            }
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