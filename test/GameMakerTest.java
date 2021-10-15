import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class GameMakerTest {
    GameMaker gm;

    @Before
    public void setUp() {
         gm = new GameMaker();
    }
    
    // Hi Camille, feel free to change this test as needed. - Maggie
    @Test(timeout = 1000)
    public void TestFirstPrompt() {
        String expectedResult = "Welcome! My friend Maggie, you have just been hired as a new manager."
            + " Here are your potential interns. Choose wisely ...\n"
            + "Name: Farzana Rahman; age: 20; skills: Teamwork (98)\n"
            + "Name: Maggie Huang; age: 20; skills: Leadership (82)\n"
            + "Name: Mary Yijia Li; age: 19; skills: Efficiency (99)\n";
        assertEquals(expectedResult, gm.firstPrompt("Maggie"));
    }
}