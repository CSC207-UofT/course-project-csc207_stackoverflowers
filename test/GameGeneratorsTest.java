import UseCases.GameGenerators;
import Entities.GamePrompts;
import Entities.Intern;
import Entities.InterviewIntern;
import Entities.Project;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class GameGeneratorsTest {
    GameGenerators gameGenerators;

    @Before
    public void setUp() {
        gameGenerators = new GameGenerators();
    }
    @Test (timeout = 1000)
    public void TestGenerateInterns() throws FileNotFoundException {
        gameGenerators.generateInterns(3);

    }

    @Test (timeout = 1000)
    public void TestGenerateProjects() throws FileNotFoundException {
        gameGenerators.generateProjects(4);

    }

    @Test (timeout = 1000)
    public void TestGenerateFinalProjects() throws FileNotFoundException {
        gameGenerators.generateFinalProject();

    }





















}
