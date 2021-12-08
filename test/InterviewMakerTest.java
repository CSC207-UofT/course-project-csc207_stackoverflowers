import Entities.Exceptions;
import Entities.GamePrompts;
import Entities.HiredIntern;
import Entities.InterviewIntern;
import UseCases.HRSystem;

import UseCases.InterviewMaker;
import UseCases.ResponseTreeMaker;
import org.junit.Before;
import org.junit.Test;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;


public class InterviewMakerTest {

        InterviewMaker interviewMaker;
        HRSystem hrSystem;
        InterviewIntern Maggie;

        @Before
        public void setUp(){
            hrSystem = new HRSystem();
            try {
                hrSystem.updateInterviewInternList(makeInterns());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            interviewMaker = new InterviewMaker(hrSystem);
        }

        @Test(timeout = 1000)
        public void TestStartOfInterviewPrompt(){
            String actual = interviewMaker.startOfInterviewPrompt();
            String expected = GamePrompts.START_INTERVIEW_PROMPT + interviewMaker.getInterviewInternInfo();
            assertEquals(expected, actual);
        }

//        @Test(timeout = 1000)
//        public void TestGetChoiceOptions(){
//            String expected = interviewMaker.getChoiceOptions();
//            HRSystem sys = new HRSystem();
//            InterviewIntern choiceIntern = new InterviewIntern("", 0, new HashMap<>());
//            for (InterviewIntern inter : sys.getInterviewInternList()){
//                if (interviewMaker.currentInterviewInternToString().equals(inter.getInternName())){
//                    choiceIntern = inter;
//                }
//            }
//            String actual = sys.choicesToString(choiceIntern);
//            assertEquals(expected, actual);
//        }

        @Test(timeout = 1000)
        public void TestGetHiredInternString(){
            String expected = interviewMaker.getHiredInternString();
            HRSystem sys = new HRSystem();
            String actual = sys.makeHiredInternsToString();
            assertEquals(expected, actual);
        }

        @Test(timeout = 1000)
        public void TestFireInternFail(){
            try {
                interviewMaker.fireIntern(interviewMaker.currentInterviewInternToString());
            }catch (Exception e){
                assert (e.getMessage().contains(Exceptions.INTERN_FIRING_FAILURE));
            }
        }

        @Test(timeout = 1000)
        public void TestFireInternSuccess() throws Exception {
            hrSystem.hireIntern(Maggie);
            interviewMaker.fireIntern("Maggie");
            assertEquals(0, hrSystem.getHiredInternList().size());
        }

        @Test(timeout = 1000)
        public void TestHaveInterviewsLeft(){
            HRSystem sys = new HRSystem();
            boolean actual = interviewMaker.haveInterviewsLeft();
            boolean expected = true;
            assertEquals(expected, actual);
        }

        @Test(timeout = 1000)
        public void TestEndOfInterviewPrompt(){
            String expected = interviewMaker.endOfInterviewPrompt();
            String actual = GamePrompts.END_OF_INTERVIEW_PROMPT;
            assertEquals(expected, actual);
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
            this.Maggie = Maggie;

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
    }
