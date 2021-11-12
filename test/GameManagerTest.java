import ControllersPresenters.GameManager;
import Entities.Exceptions;
import Entities.GamePrompts;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.*;

public class GameManagerTest {
    GameManager gameManager;

    @BeforeAll
    public void setUp() throws Exception {
        gameManager = new GameManager();
        gameManager.firstPrompt("Maggie"); //Set the saved name
    }

    @Test
    public void testSaveGameFail() throws Exception {
        String actual = gameManager.getOutput("save M");
        String expected = Exceptions.UNIVERSAL_COMMAND_NOT_FOUND;
        assertEquals(expected, actual);
    }

    @Test
    public void testSaveGameSuccess() throws Exception {
        String actual = gameManager.getOutput("save");
        String expected = GamePrompts.GAME_SAVED_SUCCESSFUL;
    }

}
