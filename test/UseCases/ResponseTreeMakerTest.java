package UseCases;

import UseCases.ResponseTree;
import UseCases.ResponseTreeMaker;
import Entities.InterviewIntern;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;
import static org.junit.Assert.*;

public class ResponseTreeMakerTest {

    @Test(timeout = 1000)
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

    @Test(timeout = 1000)
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
}