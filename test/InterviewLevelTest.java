import ControllersPresenters.InterviewLevel;
import Entities.*;
import UseCases.HRSystem;
import UseCases.InterviewMaker;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class InterviewLevelTest {

    InterviewLevel lev;
    HRSystem sys;
    InterviewMaker make;

    @Before
    public void setUp(){
        sys = new HRSystem();
        lev = new InterviewLevel(sys);
        make = new InterviewMaker(sys);
    }

    @Test(timeout = 1000)
    public void TestGetStartOfInterviewPrompt(){
        String actual = lev.getStartOfInterviewPrompt();
        String expected = GamePrompts.START_INTERVIEW_PROMPT + make.getInterviewInternInfo();
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
