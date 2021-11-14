import ControllersPresenters.InterviewLevel;
import Entities.*;
import UseCases.HRSystem;
import UseCases.InterviewMaker;

import UseCases.ResponseTreeMaker;
import org.junit.Before;
import org.junit.Test;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class InterviewLevelTest {

    InterviewLevel lev;
    HRSystem sys;
    InterviewMaker make;

    @Before
    public void setUp(){
        sys = new HRSystem();
        try {
            sys.updateInterviewInternList(makeInterns());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        lev = new InterviewLevel(sys);
        make = new InterviewMaker(sys);
    }
    private ArrayList<InterviewIntern> makeInterns() throws FileNotFoundException {
        //A helper function that sets up the interns in HRSystem for the test.
        //Setting up two Hired interns
        HashMap<String, Double> marySkills = new HashMap<>();
        marySkills.put("Efficiency", 85.0);
        InterviewIntern Mary = new InterviewIntern("Mary", 19, marySkills);
        ResponseTreeMaker forMary = new ResponseTreeMaker(Mary);
        forMary.assignResponseToIntern();

        HashMap<String, Double> maggieSkills = new HashMap<>();
        maggieSkills.put("Responsible", 87.0);
        InterviewIntern Maggie = new InterviewIntern("Maggie", 20, maggieSkills);
        ResponseTreeMaker forMaggie = new ResponseTreeMaker(Maggie);
        forMaggie.assignResponseToIntern();

        HashMap<String, Double> rubySkills = new HashMap<>();
        rubySkills.put("Communication", 66.0);
        InterviewIntern Ruby = new InterviewIntern("Ruby", 21, rubySkills);
        ResponseTreeMaker forRuby = new ResponseTreeMaker(Ruby);
        forRuby.assignResponseToIntern();
        //Make a new list of interns to put in HRSystem to update:
        ArrayList<InterviewIntern> interns = new ArrayList<>();
        interns.add(Mary);
        interns.add(Maggie);
        interns.add(Ruby);
        return interns;
    }

    @Test(timeout = 1000)
    public void TestGetStartOfInterviewPrompt(){
        String actual = lev.getStartOfInterviewPrompt();
        String expected = GamePrompts.START_INTERVIEW_PROMPT;
        assertEquals(expected, actual);
    }

    @Test(timeout = 1000)
    public void TestGetHiringResponse(){
        Object actual = lev.getHiringResponse("yes");
        Object expected = make.internToHire("yes");
        assertEquals(expected, actual);
    }

    @Test(timeout = 1000)
    public void TestGetEndOfInterviewPrompt(){
        String actual = lev.getEndOfInterviewPrompt();
        String expected = GamePrompts.END_OF_INTERVIEW_PROMPT;
        assertEquals(expected,actual);

    }

    @Test(timeout = 1000)
    public void TestUpdateLevelStatus(){
        lev.levelStarted();
        lev.endLevel();
        Boolean actual = lev.levelEnded();
        Boolean expected = true;
        assertEquals(expected, actual);
    }
}
