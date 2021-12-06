import Entities.HiredIntern;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class HiredInternTest {
    @Test
    public void testHiredIntern (){
        HashMap<String, Double> internSkills = new HashMap<>();
        internSkills.put("Smart", 10.0);
        HiredIntern hiredIntern = new HiredIntern("Mar", 21, internSkills);
        hiredIntern.updateInternSkills("hi");
        assert hiredIntern.getInternSkills().containsKey("hi");
    }
}
