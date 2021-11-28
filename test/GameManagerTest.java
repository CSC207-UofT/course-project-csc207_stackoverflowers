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
    public void testLoad() throws Exception{
        gameManager.getOutput("load haha");
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
