import ControllersPresenters.ReportLevel;
import Entities.*;
import UseCases.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class ReportLevelTest {
    ReportLevel reportLevel;
    HRSystem hrSystem;

    @Test
    public void testReportSetup() {
        for (int num = 1; num != 7; num++) {
            reportLevel = new ReportLevel(num, hrSystem);
            if (num == 1 || num == 3 || num == 5) {
                assertEquals(new MonthReportMaker(hrSystem), reportLevel.getCurrentReportMaker());
            } else if (num == 2 || num == 4) {
                assertEquals(new ProjectReportMaker(hrSystem), reportLevel.getCurrentReportMaker());
            } else {
                assertEquals(new FinalReportMaker(hrSystem), reportLevel.getCurrentReportMaker());
            }
        }
    }

    @Test
    public void testEndPrompt() throws Exception {
        reportLevel = new ReportLevel(1, hrSystem);
        String actual = reportLevel.getOutputString("confirm all decisions");
        String expected = reportLevel.getCurrentReportMaker().endOfMonthPrompt(1);
        assertEquals(actual, expected);
    }

    public void setUp() throws Exception {
        //Set tup the hrSystem so that it has some interns and projects in it.
        HRSystem hrSystem = new HRSystem();
        hrSystem.updateHiredInternList(makeInterns());
        hrSystem.updateProjectList(makeProjects());
        reportLevel = new ReportLevel(1, hrSystem);
        reportLevel.getOutputString("stuff");//Gets the first output so that now it can make stuff go.
    }

    @Test
    public void testFinishedUpgradeMatch() throws Exception {
        setUp();
        reportLevel = new ReportLevel(1, hrSystem);
        String actual = reportLevel.getOutputString("assign intern to upgrade Mary");
        String expected = reportLevel.getCurrentReportMaker().upgradeIntern("Mary",1);
        assertEquals(actual, expected);
    }

    @Test
    public void testFinishedUpgradeFail() throws Exception {
        setUp();
        reportLevel = new ReportLevel(1, hrSystem);
        reportLevel.getCurrentReportMaker().upgradeIntern("Mary",1);
        throw new Exception(Exceptions.INTERN_UPGRADING_FAILURE);
    }


    @Test
    public void testFinishedUpgradeSuccess() throws Exception {
        String actual = reportLevel.getOutputString("assign intern to upgrade Bob");
        String expected = GamePrompts.INTERN_UPGRADING_SUCCESS;
        assertEquals(actual, expected);
    }

    @Test
    public void testCheckProjects() throws Exception {
        String actual = reportLevel.getOutputString("check project info");
        String expected = reportLevel.getCurrentReportMaker().getProjectInfo(1);
        assertEquals(actual, expected);
    }

    @Test
    public void testCheckInterns() throws Exception {
        String actual = reportLevel.getOutputString("check interns info");
        String expected = reportLevel.getCurrentReportMaker().getInternsInfo();
        assertEquals(actual, expected);
    }

    @Test
    public void testAssignUpgradeFail() {
        try {
            reportLevel.getOutputString("assign intern to project Mary");
        } catch (Exception e) {
            assertEquals(e.getMessage(), Exceptions.INVALID_COMMAND);
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
        Intern Bob = new HiredIntern("Bob", 60, bobSkills);
        //Make a new list of interns to put in HRSystem to update:
        ArrayList<HiredIntern> interns = new ArrayList<>();
        interns.add(Mary);
        interns.add(Maggie);
        interns.add(Ruby);
        return interns;
    }
}
