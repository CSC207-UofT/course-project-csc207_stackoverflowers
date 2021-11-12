
import Entities.HiredIntern;
import Entities.Intern;
import Entities.InterviewIntern;
import Entities.Project;
import UseCases.HRSystem;
import org.junit.Before;
import org.junit.Test;


import java.io.Serializable;
import java.util.*;
import static org.junit.Assert.*;


public class HRSystemTest {

    HRSystem system;

    @Before
    public void setUp(){
        system = new HRSystem();
        system.updateInternList("Enam", 21, {"Teamwork"= 80.5});
        system.updatePlayerName("Richard");
        system.updateHiredInternList("Rebecca", 21, {"Communication"=20.8});
    }

    @Test(timeout = 1000)
    public void TestGetInternNames(){
        String expected = "Enam";
        assertEquals(expected, system.getInternNames());
    }

    @Test(timeout = 1000)
    public void TestMakeInternsToString(){
        String expected = "Enam 21 Teamwork 80.5";
        assertEquals(expected, system.makeInternsToString());
    }

    @Test(timeout = 1000)
    public void TestFireIntern(){
        ArrayList<HiredIntern> expected = new ArrayList<>();
        assertEquals(expected, system.fireIntern("Rebecca", 21, {"Communication" = 20.8}));
    }

}
