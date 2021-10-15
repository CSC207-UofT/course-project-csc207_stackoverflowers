import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

public class GameMaker {
    private HRSystem sys;
    private GamePrompts prompts;


    /**
     * Construct a game maker.
     */
    public GameMaker() {
        this.sys = new HRSystem();
        this.prompts = new GamePrompts();

        Intern Farzana = helperMakeInternOneSkill("Farzana Rahman", 20, "Teamwork", 98 );
        Intern Maggie = helperMakeInternOneSkill("Maggie Huang", 20, "Leadership", 82);
        Intern Mary = helperMakeInternOneSkill("Mary Yijia Li", 19, "Efficiency", 99 );
        List<Intern> newInterns = new ArrayList<Intern>();
        newInterns.add(Farzana);
        newInterns.add(Maggie);
        newInterns.add(Mary);
        this.addInternToList(newInterns);
    }
    /**
     * Add the list of interns to HRSystem.
     *
     * @param newInterns A list of interns constructed in the GameMaker constructor
     */
    public void addInternToList(List<Intern> newInterns){
        this.sys.updateInternList(newInterns);
    }

    /**
     * Return a list of interns.
     */
    public List<Intern> returnNewInterns() {
        return this.sys.getInternList();
    }

    /**
     * Return the first display prompt after the player enters their name.
     *
     *  @param playerInput the input the player enters (their name)
     */
    public String firstPrompt(String playerInput){
        String re = "";
        re += this.prompts.FIRST_PROMPT_BEFORE_NAME;
        re += playerInput;
        re += "\n";
        re += this.prompts.FIRST_PROMPT_AFTER_NAME;
        re += this.sys.makeInternsToPrompt();
        return re;
    }

    /**
     * A helper method for creating an intern.
     */
    private Intern helperMakeInternOneSkill(String name, int age, String skillName, int skillLevel){
        HashMap<String, Integer> skills = new HashMap<String, Integer>();
        skills.put(skillName, skillLevel);
        return new Intern(name, age, skills);
    }

}
