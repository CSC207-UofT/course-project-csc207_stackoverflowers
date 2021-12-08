import Entities.*;
import UseCases.HRSystem;
import UseCases.PMSystem;
import UseCases.FinalReportMaker;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class FinalReportMakerTest {

    HRSystem hrSystem;
    PMSystem pmSystem;
    FinalReportMaker reportMaker;

    @Before
    public void setup() throws FileNotFoundException {
        hrSystem = new HRSystem();
        pmSystem = new PMSystem(hrSystem);
        pmSystem.setCurrentMonth(1);
        hrSystem.updateHiredInternList(makeInterns());
        pmSystem.updateProjectList(makeProjects());
        reportMaker = new FinalReportMaker(hrSystem, pmSystem);
    }

    @Test
    public void makeReportHeader() {
        String actual = reportMaker.makeReportHeader(1);
        String expected = GamePrompts.REPORT_HEADER + 1 + '\n';
        assertEquals(actual, expected);
    }

    @Test
    public void bakeProjectName() {
        String actual = reportMaker.bakeProjectName("a name");
        String expected = GamePrompts.PROJECT_NAME_HEADER + "a name";
        assertEquals(actual, expected);
    }

    @Test
    public void endOfMonthPromptNotFinal() {
        String actual = reportMaker.endOfMonthPrompt(1);
        String expected = GamePrompts.END_OF_MONTH_REPORT_PROMPT;
        assertEquals(actual, expected);
    }

    @Test
    public void endOfMonthPromptFinal() {

        String actual = reportMaker.endOfMonthPrompt(6);
        String expected = GamePrompts.END_OF_MONTH_REPORT_PROMPT;
        assertEquals(actual, expected);
    }
    @Test
    public void bakeInternsPerformances() throws FileNotFoundException {
        setup();
        pmSystem.assignInternToProject("Mary", GamePrompts.PROJECT1_NAME);
        pmSystem.assignInternToProject("Maggie", GamePrompts.PROJECT1_NAME);
        pmSystem.assignInternToProject("Ruby", GamePrompts.PROJECT1_NAME);
        HashMap<String, Float> projectCompatibilityList = new HashMap<>();
        for (Project proj : pmSystem.getProjects(2)) {
            projectCompatibilityList.putAll(proj.getSkillsCompatibilities());
        }
        String result = reportMaker.bakeInternsPerformances(pmSystem.getProjectToInterns().get(pmSystem.getProjects(2).get(0)), projectCompatibilityList);
        StringBuilder returnLine = new StringBuilder(GamePrompts.INTERN_PERFORMANCE_HEADER + "Mary|Maggie|Ruby|" + "\n");
        String[] internNamesList = "Mary|Maggie|Ruby|".split("\\|");
        for (int i = 0; i != 3; i += 1) {
            returnLine.append("     - ").append(internNamesList[i]).append(": ").append(reportMaker.calculateInternPerformance(pmSystem.getProjectToInterns().get(pmSystem.getProjects(1).get(0)).get(i).getInternSkills(), projectCompatibilityList)).append("\n");
        }
        String expected = returnLine.toString();
        assertEquals(result, expected);
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

    @Test
    public void makeReportConclusion() {
        String result = reportMaker.makeReportConclusion();
        assertEquals(result, GamePrompts.REPORT_CONCLUSION);
    }

    @Test
    public void confirmChoice() {
        String result = reportMaker.confirmChoice(2);
        assertEquals(result, GamePrompts.CONFIRM_UPGRADE);
    }

    @Test
    public void makeReportIntro() {
        String result = reportMaker.makeReportIntro();
        assertEquals(result, null);
    }

    @Test
    public void getInternsInfo() throws FileNotFoundException {
        setup();
        pmSystem.assignInternToProject("Mary", GamePrompts.PROJECT1_NAME);
        pmSystem.assignInternToProject("Maggie", GamePrompts.PROJECT1_NAME);
        pmSystem.assignInternToProject("Ruby", GamePrompts.PROJECT1_NAME);
        String result = reportMaker.getInternsInfo();
        String expected = GamePrompts.INTERN_INFO_HEADER + hrSystem.getHiredInternsNames();
        assertEquals(result,expected);
    }

    @Test
    public void getProjectInfo() throws FileNotFoundException {
        setup();
        pmSystem.assignInternToProject("Mary", GamePrompts.PROJECT1_NAME);
        pmSystem.assignInternToProject("Maggie", GamePrompts.PROJECT1_NAME);
        pmSystem.assignInternToProject("Ruby", GamePrompts.PROJECT1_NAME);
        String result = reportMaker.getProjectInfo(2);
        String expected = GamePrompts.PROJECT_NAME_HEADER + pmSystem.makeProjectsToString(2);
        assertEquals(result,expected);
    }

    @Test
    public void endOfMonthPrompt() {
        String result = reportMaker.endOfMonthPrompt(6);
        assertEquals(result, GamePrompts.END_OF_MONTH_REPORT_PROMPT);
    }
}