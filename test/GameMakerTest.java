
import Entities.GamePrompts;
import UseCases.GameMaker;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.*;




public class GameMakerTest {
    GameMaker gameMaker;
    @Before
    public void setUp() {
         gameMaker = new GameMaker();
         gameMaker.setCurrentMonth(3);
    }
    
    // Hi Camille, feel free to change this test as needed. - Maggie
    @Test
    public void TestFirstPrompt() throws Exception {
        String expectedResult = GamePrompts.FIRST_PROMPT_BEFORE_NAME + "Maggie" + GamePrompts.FIRST_PROMPT_AFTER_NAME +
                gameMaker.getCurrentHRSystem().makeInterviewInternsToString() + GamePrompts.START_INTERVIEW_PROMPT;
        assertEquals(expectedResult, gameMaker.firstPrompt("Maggie"));
    }

    @Test
    public void TestEndPrompt(){
        String expectedResult = GamePrompts.END_PROMPT;
        assertEquals(expectedResult, gameMaker.endPrompt());
    }

//    @Test(timeout = 1000)
//    public void TestGenerateInterns() throws FileNotFoundException {
//        gameMaker.generateInterns(3);
//    }

    @Test
    public void TestSerializable() throws IOException {
            new ObjectOutputStream(new FileOutputStream("Maggie")).writeObject(gameMaker);

    }
    @Test
    public void TestSaveGame() throws IOException {
        gameMaker.save(gameMaker.getCurrentMonth());
    }
    @Test
    public  void TestLoad() throws IOException, ClassNotFoundException {
        gameMaker.save(3);
        GameMaker copy = gameMaker.load(gameMaker.getCurrentHRSystem().getPlayerName());
        assertEquals(gameMaker.getCurrentHRSystem().getPlayerName(), copy.getCurrentHRSystem().getPlayerName());
        assertEquals(gameMaker.getCurrentHRSystem().getInterviewInternList(), copy.getCurrentHRSystem().getInterviewInternList());
    }


}