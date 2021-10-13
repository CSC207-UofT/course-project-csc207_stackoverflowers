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
        Intern Farzana = new Intern("Farzana Rahman", 20, HashMap < "Teamwork", 92 >);
        Intern Maggie = new Intern("Maggie Huang", 20, HashMap < "Leadership", 82 >);
        Intern Mary = new Intern("Mary Yijia Li", 19, HashMap < "Efficiency", 99 >);
        List<Intern> newInterns = new ArrayList<Intern>();
        newInterns.add(Farzana);
        newInterns.add(Maggie);
        newInterns.add(Mary);
        this.addInternToList(newInterns);
    }

    /**
     * Add the list of interns to
     */
    public List<Intern> addInternToList(List<Intern> newInterns){
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
        re += this.prompts.FIRST_PROMPT_AFTER_NAME;
        return re;
    }

}
