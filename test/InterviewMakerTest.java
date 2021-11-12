import Entities.GamePrompts;
import Entities.InterviewIntern;
import Entities.HiredIntern;
import UseCases.HRSystem;

import UseCases.InterviewMaker;
import UseCases.MonthMaker;
import org.junit.Before;
import org.junit.Test;


import java.io.Serializable;
import java.util.*;
import static org.junit.Assert.*;


public class InterviewMakerTest {


        InterviewMaker make;

        @Before
        public void setUp(){
            make = new InterviewMaker(new HRSystem());
        }

        @Test(timeout = 1000)
        public void TestStartOfInterviewPrompt(){
            String actual = make.startOfInterviewPrompt();
            String expected = GamePrompts.ASK_FOR_INTERVIEWEE_NAME + make.getInterviewInternInfo();
            assertEquals(expected, actual);
        }

        @Test(timeout = 1000)
        public void TestGetChoiceOptions(){
            String expected = make.getChoiceOptions();
            HRSystem sys = new HRSystem();
            String actual = sys.choicesToString();
            assertEquals(expected, actual);
        }

        @Test(timeout = 1000)
        public void TestChoicePrompt(){
            playerInput = "yes";
            String expected = make.choicePrompt("That's great. Why don't you tell me a little about yourself?",);
            String actual = GamePrompts.PLAYER_CHOICE;
            assertEquals(expected, actual);
        }

        @Test(timeout = 1000)
        public void TestEndOfInterviewPrompt(){
            String expected = make.endOfInterviewPrompt();
            String actual = GamePrompts.END_OF_INTERVIEW_PROMPT;
            assertEquals(expected, actual);
        }
    }
