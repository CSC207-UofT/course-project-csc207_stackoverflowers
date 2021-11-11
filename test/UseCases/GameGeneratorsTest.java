package UseCases;

import Entities.GamePrompts;
import Entities.HiredIntern;
import Entities.Intern;
import Entities.Project;

import org.testng.Assert;
import java.util.*;

import java.io.FileNotFoundException;
import java.lang.reflect.Field;
// import java.lang.reflect.Method;


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
        privateField.setAccessible(true); // Set the accessibility as true
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

// TODO: These tests might need to be modified after HRSystem's implementation is completed. Hhaha

    @org.junit.jupiter.api.Test
    void TestGenerateInterns() throws FileNotFoundException {
        gameGenerators.generateInterns(3);
        Assert.assertEquals(hrSystem.getInternList().size(), 3);
        }

    @org.junit.jupiter.api.Test
    void TestAddInternToList() {
        HashMap<String, Double> MagSkillSets = (HashMap<String, Double>) Map.of("Responsible", 0.90, "Observant",
                0.80, "Communication", 0.70);
        Intern mag = new HiredIntern("Maggie", 20, MagSkillSets);
        HashMap<String, Double> FarzSkillSets = (HashMap<String, Double>) Map.of("Leadership", 0.90, "Patient",
                0.80, "Teamwork", 0.70);
        Intern farz = new HiredIntern("Farzana", 20, FarzSkillSets);
        ArrayList<Intern> internList = (ArrayList<Intern>) List.of(mag, farz);
        gameGenerators.addInternToList(internList);
        Assert.assertEquals(hrSystem.getInternList().size(), 5);
    }

    // TODO: I'm currently not sure how to test this method (I think there're gaps between the implementation of
    // GameGenerator and HRSystem...Idk, maybe we need a getter for projectList in HRSystem??)
    @org.junit.jupiter.api.Test
    void TestGenerateProjects() throws FileNotFoundException {
        gameGenerators.generateProjects(3);
    }

    @org.junit.jupiter.api.Test
    void TestGenerateFinalProject() throws FileNotFoundException {
        Project finalProject = new Project(GamePrompts.PROJECT1_NAME);
        Assert.assertEquals(hrSystem.getProject(HRSystem.FINAL_MONTH).size(), 1);
        Assert.assertTrue(hrSystem.getProject(HRSystem.FINAL_MONTH).get(0).isFinal());
    }
}
