import Entities.GamePrompts;
import Entities.InterviewIntern;
import UseCases.HRSystem;

import UseCases.InterviewMaker;
import org.junit.Before;
import org.junit.Test;


import java.util.HashMap;

import static org.junit.Assert.*;


public class InterviewMakerTest {

        InterviewMaker interviewMaker;

        @Before
        public void setUp(){
            interviewMaker = new InterviewMaker(new HRSystem());
        }

        @Test(timeout = 1000)
        public void TestStartOfInterviewPrompt(){
            String actual = interviewMaker.startOfInterviewPrompt();
            String expected = GamePrompts.START_INTERVIEW_PROMPT + interviewMaker.getInterviewInternInfo();
            assertEquals(expected, actual);
        }

        @Test(timeout = 1000)
        public void TestGetChoiceOptions(){
            String expected = interviewMaker.getChoiceOptions();
            HRSystem sys = new HRSystem();
            InterviewIntern choiceIntern = new InterviewIntern("", 0, new HashMap<>());
            for (InterviewIntern inter : sys.getInterviewInternList()){
                if (interviewMaker.currentInterviewInternToString().equals(inter.getInternName())){
                    choiceIntern = inter;
                }
            }
            String actual = sys.choicesToString(choiceIntern);
            assertEquals(expected, actual);
        }

        @Test(timeout = 1000)
        public void TestGetHiredInternString(){
            String expected = interviewMaker.getHiredInternString();
            HRSystem sys = new HRSystem();
            String actual = sys.makeHiredInternsToString();
            assertEquals(expected, actual);
        }

        @Test(timeout = 1000)
        public void TestFireIntern(){
            HRSystem sys = new HRSystem();
            sys.fireIntern(interviewMaker.currentInterviewInternToString());
            interviewMaker.fireIntern(interviewMaker.currentInterviewInternToString());
            assertEquals(interviewMaker.getHiredInternList(), sys.getHiredInternList());
        }

        @Test(timeout = 1000)
        public void TestHaveInterviewsLeft(){
            HRSystem sys = new HRSystem();
            boolean expected = interviewMaker.haveInterviewsLeft();
            boolean actual = sys.getInterviewInternList().size() != interviewMaker.getInterviewedInterns().size();
            assertEquals(expected, actual);
        }

        @Test(timeout = 1000)
        public void TestEndOfInterviewPrompt(){
            String expected = interviewMaker.endOfInterviewPrompt();
            String actual = GamePrompts.END_OF_INTERVIEW_PROMPT;
            assertEquals(expected, actual);
        }
    }
