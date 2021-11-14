
import UseCases.ResponseTree;
import UseCases.ResponseTreeMaker;
import Entities.InterviewIntern;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ResponseTreeMakerTest {

    @Test
    public void TestGenerateInternResponses() throws FileNotFoundException {
        HashMap<String, Double> skills = new HashMap<>();
        skills.put("Confidence", 50.0);
        skills.put("Teamwork", 50.0);
        skills.put("Flexibility", 50.0);
        InterviewIntern intern = new InterviewIntern("Camille", 20, skills);
        ResponseTreeMaker rtm = new ResponseTreeMaker(intern);
        ResponseTree<ArrayList<String>> respTree = rtm.generateInternResponses();
        assertEquals(15, respTree.getSize());
    }
    //Since this method is private, it doesn't need to be tested.
//    @Test
//    public void  TestGenerateDialogue() throws FileNotFoundException {
//        HashMap<String, Double> skills = new HashMap<>();
//        skills.put("Confidence", 50.0);
//        skills.put("Teamwork", 50.0);
//        skills.put("Flexibility", 50.0);
//        InterviewIntern intern = new InterviewIntern("Camille", 20, skills);
//        ResponseTreeMaker resp = new ResponseTreeMaker(intern);
//        ArrayList<String> questions = resp.generateDialogueList("Resources/questions.txt");
//        assertEquals("Nice to meet you. Why don't you tell me a bit about your experience?", questions.get(1));
//    }

    /*
    @Test
    public void TestAssignResponseToIntern() throws FileNotFoundException {
        HashMap<String, Double> skills = new HashMap<>();
        skills.put("Confidence", 50.0);
        skills.put("Teamwork", 50.0);
        skills.put("Flexibility", 50.0);
        InterviewIntern intern = new InterviewIntern("Camille", 20, skills);
        ResponseTreeMaker rtm = new ResponseTreeMaker(intern);
        ResponseTree<ArrayList<String>> respTree = rtm.generateInternResponses();
        rtm.assignResponseToIntern(respTree);
        assertEquals(15, intern.getResponseTree().getSize());
    }

     */
}