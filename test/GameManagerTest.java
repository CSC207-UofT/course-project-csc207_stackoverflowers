import ControllersPresenters.GameManager;
import Entities.Exceptions;
import Entities.GamePrompts;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import static org.junit.Assert.*;

public class GameManagerTest {
    GameManager gameManager;

    @Before
    public void setUp() throws Exception {
        gameManager = new GameManager();
        gameManager.firstPrompt("Maggie"); //Set the saved name
    }

    @Test(timeout = 100)
    public void TestSaveGameFail() throws Exception {
        String actual = gameManager.getOutput("save M");
        String expected = Exceptions.UNIVERSAL_COMMAND_NOT_FOUND;
        assertEquals(expected, actual);
    }

    @Test(timeout = 100)
    public void TestSaveGameSuccess() throws Exception {
        String actual = gameManager.getOutput("save");
        String expected = GamePrompts.GAME_SAVED_SUCCESSFUL;
    }

}
