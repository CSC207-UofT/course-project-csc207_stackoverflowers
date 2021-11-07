import Entities.GamePrompts;
import UseCases.GameMaker;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class GameMakerTest {
    GameMaker gm;
    GamePrompts gamePrompts;

    @Before
    public void setUp() {
         gm = new GameMaker();
    }
    
    // Hi Camille, feel free to change this test as needed. - Maggie
    @Test(timeout = 1000)
    public void TestFirstPrompt() {
        String expectedResult = GamePrompts.FIRST_PROMPT_BEFORE_NAME + "Maggie" + GamePrompts.FIRST_PROMPT_AFTER_NAME +
                gm.getCurrentHRSystem().makeInternsToString() + GamePrompts.ASK_FOR_INTERVIEWEE_NAME;
        assertEquals(expectedResult, gm.firstPrompt("Maggie"));
    }

    @Test(timeout =  500)
    public void TestEndPrompt(){
        String expectedResult = GamePrompts.END_PROMPT;
        assertEquals(expectedResult, gm.endPrompt());
    }

}