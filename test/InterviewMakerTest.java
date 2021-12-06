import Entities.GamePrompts;
import Entities.HiredIntern;
import Entities.InterviewIntern;
import UseCases.HRSystem;

import UseCases.InterviewMaker;
import org.junit.Before;
import org.junit.Test;


import java.util.HashMap;

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
            String expected = GamePrompts.START_INTERVIEW_PROMPT + make.getInterviewInternInfo();
            assertEquals(expected, actual);
        }

        @Test(timeout = 1000)
        public void TestGetChoiceOptions(){
            String expected = make.getChoiceOptions();
            HRSystem sys = new HRSystem();
            InterviewIntern choiceIntern = new InterviewIntern("", 0, new HashMap<>());
            for (InterviewIntern inter : sys.getInterviewInternList()){
                if (make.currentInterviewInternToString().equals(inter.getInternName())){
                    choiceIntern = inter;
                }
            }
            String actual = sys.choicesToString(choiceIntern);
            assertEquals(expected, actual);
        }



        @Test(timeout = 1000)
        public void TestEndOfInterviewPrompt(){
            String expected = make.endOfInterviewPrompt();
            String actual = GamePrompts.END_OF_INTERVIEW_PROMPT;
            assertEquals(expected, actual);
        }
    }
