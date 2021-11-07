import Entities.GamePrompts;
import UseCases.GameMaker;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;


public class GameMakerTest {
    GameMaker gameMaker;
    GamePrompts gamePrompts;

    @Before
    public void setUp() {
         gameMaker = new GameMaker();
    }
    
    // Hi Camille, feel free to change this test as needed. - Maggie
    @Test(timeout = 100)
    public void TestFirstPrompt() {
        String expectedResult = GamePrompts.FIRST_PROMPT_BEFORE_NAME + "Maggie" + GamePrompts.FIRST_PROMPT_AFTER_NAME +
                gameMaker.getCurrentHRSystem().makeInternsToString() + GamePrompts.ASK_FOR_INTERVIEWEE_NAME;
        assertEquals(expectedResult, gameMaker.firstPrompt("Maggie"));
    }

    @Test(timeout =  100)
    public void TestEndPrompt(){
        String expectedResult = GamePrompts.END_PROMPT;
        assertEquals(expectedResult, gameMaker.endPrompt());
    }

    @Test(timeout = 100)
    public void TestSerializable(){
        try {
            new ObjectOutputStream(new FileOutputStream("Maggie")).writeObject(gameMaker);
        }catch (IOException e){
            assertEquals(1,2);//What should I do if exception occurs?
        }
    }

    @Test(timeout = 100)
    public  void TestLoad() throws IOException, ClassNotFoundException {
        gameMaker.save();
        GameMaker copy = gameMaker.load(gameMaker.getCurrentHRSystem().getPlayerName());
        assertEquals(gameMaker, copy);
    }


}