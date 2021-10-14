import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


class GameMakerTest {
    GameMaker gm;

    @Before
    public void setUp() {
        GameMaker gm = new GameMaker();
    }
    
    // Hi Camille, feel free to change this test as needed. - Maggie
    @Test(timeout = 50)
    public void testFirstPrompt() {
        String expectedResult = GamePrompts.FIRST_PROMPT_BEFORE_NAME + "Maggie" + "\n"
            + GamePrompts.FIRST_PROMPT_AFTER_NAME
            + "Name: Farzana Rahman; age: 20; skills: teamwork (98)\n"
            + "Name: Maggie Huang; age: 20; skills: leadership (82)\n"
            + "Name: Mary Yijia Li; age: 19; skills: efficiency (99)\n";
        assertEquals(expectedResult, gm.firstPrompt("Maggie"));
    }
}