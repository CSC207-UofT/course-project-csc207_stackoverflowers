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

    InterviewLevel interviewLevel;
    HRSystem hrSystem;
    InterviewMaker interviewMaker;

    @Before
    public void setUp(){
        hrSystem = new HRSystem();
        try {
            hrSystem.updateInterviewInternList(makeInterns());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        interviewLevel = new InterviewLevel(hrSystem);
        interviewMaker = new InterviewMaker(hrSystem);
    }
    private ArrayList<InterviewIntern> makeInterns() throws FileNotFoundException {
        //A helper function that sets up the interns in HRSystem for the test.
        //Setting up two Hired interns
        HashMap<String, Double> marySkills = new HashMap<>();
        marySkills.put("Efficiency", 85.0);
        marySkills.put("Responsible", 85.0);
        marySkills.put("Confidence", 85.0);
        InterviewIntern Mary = new InterviewIntern("Mary", 19, marySkills);
        ResponseTreeMaker forMary = new ResponseTreeMaker(Mary);
        forMary.assignResponseToIntern();

        HashMap<String, Double> maggieSkills = new HashMap<>();
        maggieSkills.put("Responsible", 87.0);
        maggieSkills.put("Creativity", 87.0);
        maggieSkills.put("Artistic", 87.0);
        InterviewIntern Maggie = new InterviewIntern("Maggie", 20, maggieSkills);
        ResponseTreeMaker forMaggie = new ResponseTreeMaker(Maggie);
        forMaggie.assignResponseToIntern();

        HashMap<String, Double> rubySkills = new HashMap<>();
        rubySkills.put("Communication", 66.0);
        rubySkills.put("Efficiency", 66.0);
        rubySkills.put("Creativity", 66.0);
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
    public void TestGetStartOfInterviewPrompt() throws Exception {
        assert interviewLevel.levelStarted();
        String actual = interviewLevel.getOutputString("ha");
        String expected = GamePrompts.START_INTERVIEW_PROMPT;
        assertTrue(actual.contains(expected));
    }

    @Test(timeout = 1000)
    public void TestGetEndOfInterviewPrompt() throws Exception {
        interviewLevel.endLevel();
        String actual = interviewLevel.getOutputString("ha");
        String expected = GamePrompts.END_OF_INTERVIEW_PROMPT;
        assertEquals(expected,actual);

    }

    @Test(timeout = 1000)
    public void TestUpdateLevelStatus(){
        interviewLevel.levelStarted();
        interviewLevel.endLevel();
        Boolean actual = interviewLevel.levelEnded();
        Boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void TestGetOutput() throws Exception {
        interviewLevel.getIntoLevel();
        interviewLevel.getOutputString("a");
    }

    @Test
    public void TestGetOutput2() throws Exception {
        interviewLevel.getIntoLevel();
        String actual = interviewLevel.getOutputString("end interview");
        String expected = interviewMaker.getHiredInternString() + GamePrompts.END_OF_INTERVIEW_PROMPT;

    }


    @Test
    public void TestGetOutputFail(){
        interviewLevel.getIntoLevel();
        try{
        interviewLevel.getOutputString("");
        }catch (Exception e){
            assert (e.getMessage().contains(Exceptions.INVALID_COMMAND));
        }
    }
}
