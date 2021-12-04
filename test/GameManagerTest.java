import ControllersPresenters.GameManager;
import ControllersPresenters.InterviewLevel;
import ControllersPresenters.MonthLevel;
import Entities.Exceptions;
import Entities.GamePrompts;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameManagerTest {
    GameManager gameManager;

    @Before
    public void setUp() throws Exception {
        gameManager = new GameManager();


    }
    @Test
    public void testLoad() throws Exception{
        gameManager.getOutput("Maggie");
        try{gameManager.getOutput("load haha");}
        catch (Exception e) {
            assertEquals("Can't find file to load",e.getMessage());
        }
    }
    @Test
    public void testLoadSuccess() throws Exception {
        gameManager.getOutput("load Maggie_0");
    }

    @Test
    public void testGetOutput() throws Exception {
        String result = gameManager.getOutput("quit");
        String expected = GamePrompts.INFORM_QUIT_GAME + "Maggie";
    }
}
