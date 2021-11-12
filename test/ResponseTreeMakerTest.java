
import UseCases.ResponseTree;
import UseCases.ResponseTreeMaker;
import Entities.InterviewIntern;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResponseTreeMakerTest {

    @Test
    public void TestGenerateInternResponses() throws FileNotFoundException {
        HashMap<String, Integer> skills = new HashMap<>();
        skills.put("Confidence", 50);
        skills.put("Teamwork", 50);
        skills.put("Flexibility", 50);
        InterviewIntern intern = new InterviewIntern("Camille", 20, skills);
        ResponseTreeMaker rtm = new ResponseTreeMaker(intern);
        ResponseTree<ArrayList<String>> respTree = rtm.generateInternResponses();
        assertEquals(15, respTree.getSize());
    }

    @Test
    public void TestAssignResponseToIntern() throws FileNotFoundException {
        HashMap<String, Integer> skills = new HashMap<>();
        skills.put("Confidence", 50);
        skills.put("Teamwork", 50);
        skills.put("Flexibility", 50);
        InterviewIntern intern = new InterviewIntern("Camille", 20, skills);
        ResponseTreeMaker rtm = new ResponseTreeMaker(intern);
        ResponseTree<ArrayList<String>> respTree = rtm.generateInternResponses();
        rtm.assignResponseToIntern(respTree);
        assertEquals(15, intern.getResponseTree().getSize());
    }
}