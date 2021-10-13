import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
public class GameMaker {
    private HRSystem sys;



    /*


        //TODO:
        - write a method called update_intern
        just call the HRSystem method to update the interns in this class
        //TODO:
        - method return list of interns
        //TODO:
        - method return {Farzana Rahman, new manager at X company} -> return the prompt with the 'filled in' name of player
        (use GamePrompts for the prompt itself)
        //TODO:
        - makes a HR system - inside GameMaker constructor
         */

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

    public String FirstPrompt(String playerInput){


    }

}
