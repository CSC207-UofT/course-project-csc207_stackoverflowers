
import Entities.HiredIntern;
import Entities.InterviewIntern;
import UseCases.HRSystem;
import org.junit.Before;
import org.junit.Test;


import java.util.*;
import static org.junit.Assert.*;


public class HRSystemTest {

    HRSystem system;

    @Before
    public void setUp(){
        system = new HRSystem();
        system.updateInternList(makeInterviewInterns());
        system.updatePlayerName("Richard");
        system.updateHiredInternList(makeHiredInters());
    }
    private ArrayList<InterviewIntern> makeInterviewInterns() {
        //A helper function that sets up the interns in HRSystem for the test.
        //Setting up two Hired interns
        HashMap<String, Double> enamSkills = new HashMap<>();
        enamSkills.put("Teamwork", 80.5);
        InterviewIntern Enam = new InterviewIntern("Enam", 21, enamSkills);
        ArrayList<InterviewIntern> interns = new ArrayList<>();
        interns.add(Enam);
        return interns;
    }

    private ArrayList<HiredIntern> makeHiredInters(){
        HashMap<String, Double> rebeccaSkills = new HashMap<>();
        rebeccaSkills.put("Communication",20.8);
        HiredIntern Rebecca = new HiredIntern("Rebecca", 21, rebeccaSkills);
        ArrayList<HiredIntern> interns = new ArrayList<>();
        interns.add(Rebecca);
        return interns;

    }

    @Test(timeout = 1000)
    public void TestGetHiredInternNames(){
        String expected = "Rebecca|";
        assertEquals(expected, system.getHiredInternsNames());
    }

    @Test(timeout = 1000)
    public void TestMakeInterviewInternsToString(){
        String expected = "Name: Enam; age: 21; skills: Teamwork (80.5)";
        assertEquals(expected, system.makeInterviewInternsToString());
    }

}
