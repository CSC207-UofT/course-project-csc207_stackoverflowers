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
    }

    @Test
    public void testLoad() throws Exception {
        try {
            gameManager.getOutput("load haha");
        } catch (Exception e) {
            assert (e.getMessage().contains("Can't find file to load"));
        }
    }

    @Test
    public void testLoadingFail1() throws Exception {
        gameManager.getOutput("Maggie");
        gameManager.getOutput("haha");
        try {
            gameManager.getOutput("load Maggie_0");
        } catch (Exception e) {
            assert (e.getMessage().contains(Exceptions.NOT_LOADING_AT_START));
        }
    }
}
