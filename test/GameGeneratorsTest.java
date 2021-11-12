import Entities.GamePrompts;
import Entities.HiredIntern;
import Entities.Intern;
import Entities.Project;
import UseCases.GameGenerators;
import UseCases.HRSystem;


import java.lang.reflect.Field;
import java.util.*;
import java.io.FileNotFoundException;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class GameGeneratorsTest {
    GameGenerators gameGenerators = new GameGenerators();

    Field privateField;
    {
        try {
            privateField = GameGenerators.class.getDeclaredField("currentHRSystem"); // Create Field object
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
//        privateField.setAccessible(true); // Set the accessibility as true
    HRSystem hrSystem;

    {
        try {
            hrSystem = (HRSystem) privateField.get(gameGenerators); // Store the value of private field in variable
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    //Hmm...Why tf are they not working?
//    Method privateGenerateUniqueSkillMap; // Create Method object
//    {
//        try {
//            privateGenerateUniqueSkillMap = GameGenerators.class.getDeclaredMethod("generateUniqueSkillMap", ArrayList.class);
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
//    }
//
////    HashMap<String, Integer> returnValue = (HashMap<String, Integer>) privateGenerateUniqueSkillMap.invoke(gameGenerators);
//
//    @org.junit.jupiter.api.Test
//    void TestGenerateUniqueSkillMap() throws InvocationTargetException, IllegalAccessException {
//        List<String> skillSets = new ArrayList<>(List.of("Geeks", "for", "Geeks"));
//        HashMap result = (HashMap) privateGenerateUniqueSkillMap.invoke(gameGenerators, skillSets);
//        Assert.assertEquals(result.size);
//    }

// TODO: These tests might need to be modified after HRSystem's implementation is completed.

    @Test
    void TestGenerateInterns() throws FileNotFoundException {
        gameGenerators.generateInterns(3);
        assertEquals(hrSystem.getInternList().size(), 3);
        }

    @Test
    void TestAddInternToList() {
        HashMap<String, Double> MagSkillSets = (HashMap<String, Double>) Map.of("Responsible", 90.0, "Observant",
                80.0, "Communication", 70.0);
        Intern mag = new HiredIntern("Maggie", 20, MagSkillSets);
        HashMap<String, Double> FarzSkillSets = (HashMap<String, Double>) Map.of("Leadership", 90.0, "Patient",
                80.0, "Teamwork", 70.0);
        Intern farz = new HiredIntern("Farzana", 20, FarzSkillSets);
        ArrayList<Intern> internList = (ArrayList<Intern>) List.of(mag, farz);
        gameGenerators.addInternToList(internList);
        assertEquals(hrSystem.getInternList().size(), 5);
    }

    // TODO: Enam needs to add a getter for projectList in HRSystem.
//    @Test
//    void TestGenerateProjects() throws FileNotFoundException {
//        gameGenerators.generateProjects(3);
//        assertEquals(hrSystem.getProjectList().size(), 3);
//    }

    @Test
    void TestGenerateFinalProject() throws FileNotFoundException {
        gameGenerators.generateFinalProject();
        assertEquals(hrSystem.getProject(HRSystem.FINAL_MONTH).size(), 1);
        assertTrue(hrSystem.getProject(HRSystem.FINAL_MONTH).get(0).isFinal());
    }
}
