import ControllersPresenters.GameManager;
import ControllersPresenters.InterviewLevel;
import ControllersPresenters.MonthLevel;
import Entities.Exceptions;
import Entities.GamePrompts;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameManagerTest {
    GameManager gameManager;

    @Before
    public void setUp() throws Exception {
        gameManager = new GameManager();
        gameManager.firstPrompt("Maggie"); //Set the saved name
    }

    @Test
    public void testSaveGameFail() throws Exception {
        try{
        gameManager.getOutput("save M");}
        catch(Exception e){
            String actual = e.toString();
            String expected = Exceptions.UNIVERSAL_COMMAND_NOT_FOUND;
            assertEquals(expected, actual);
        }

    }

    @Test
    public void testSaveGameSuccess() throws Exception {
        String actual = gameManager.getOutput("save");
        String expected = GamePrompts.GAME_SAVED_SUCCESSFUL;
    }

//    @Test
//    public void testStartToInterview(){
//        assertTrue(gameManager.currentLevel instanceof InterviewLevel);
//    }

//    @Test
//    public void testInterviewToMonth() throws Exception {
//        gameManager.currentLevel.endLevel();
//        gameManager.getOutput("ha");
//        gameManager.getOutput("now");
//        assertTrue(gameManager.currentLevel instanceof MonthLevel);
//    }
}
