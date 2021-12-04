
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
    public void setUp() throws Exception {
         gameMaker = new GameMaker();
         gameMaker.setCurrentMonth(3);
         gameMaker.firstPrompt("Maggie"); //initialize the game so that it can be serialized tested
    }
    
    // Hi Camille, feel free to change this test as needed. - Maggie
    @Test
    public void TestFirstPrompt() throws Exception {
        String expectedResult = GamePrompts.FIRST_PROMPT_BEFORE_NAME + "Maggie" + GamePrompts.FIRST_PROMPT_AFTER_NAME +
                gameMaker.getCurrentHRSystem().makeInterviewInternsToString() + GamePrompts.FIRST_PROMPT_AFTER_INTERNS;
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
        gameMaker.save(1, "Month");
    }
    @Test
    public  void TestLoad() throws IOException, ClassNotFoundException {
        gameMaker.save(1, "FinalMonth");
        GameMaker copy = gameMaker.load(gameMaker.getCurrentHRSystem().getPlayerName()+"_0");
        assertEquals(gameMaker.getCurrentHRSystem().getPlayerName(), copy.getCurrentHRSystem().getPlayerName());
        assertEquals(gameMaker.getCurrentHRSystem().getInterviewInternList(), copy.getCurrentHRSystem().getInterviewInternList());
    }


}