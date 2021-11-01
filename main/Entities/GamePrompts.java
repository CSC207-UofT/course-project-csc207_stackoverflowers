package Entities;

public class GamePrompts {



    //TODO:
    /*
    - any textual prompt we would need
    - feel free to add more as we continue coding our project
    - if class gets too long, maybe consider creating another prompt class
     */
    public final String ASK_FOR_NAME = "Welcome to HRSimulator! To start the game, please enter your name:\n";

    public final String FIRST_PROMPT_BEFORE_NAME = "Welcome! My friend ";

    public final String FIRST_PROMPT_AFTER_NAME = ", you have just been hired as a new manager. \n" +
            "You're company has decided to expand the staff for some new and exciting projects.\n" +
            "Your job is to hire some interns and then... Here is a brief view of the interviewee list. \n" +
            "Take a glance and let's start the interview! \n"; //
    //TODO:
    public static final String ASK_FOR_INTERVIEWEE_NAME = "Now, which potential would you like to interview first?";
    //see usage in firstPrompt

    public final String QUIT_GAME = "";

    public final String HIRE_INTERN = "";

    public final String HIRED_INTERN = "Congratulations! You have hired this intern! ";

    public final String PLAYER_CHOICE = ""; // what would you like to choose etc

    public final String CONFIRM_CHOICE = "";

    public final String NEXT_CANDIDATE = "";

    // not all of the project prompts will be used, the projectgenerator will output a list and choose 4 projects
    // for the game from all the prompts here

    public final String PROJECT_PROMPT1 = "";

    public final String PROJECT_PROMPT2 = "";

    public final String PROJECT_PROMPT3 = "";

    public final String PROJECT_PROMPT4 = "";

    public final String PROJECT_PROMPT5 = "";

    public final String PROJECT_PROMPT6 = "";

    public final String PROJECT_PROMPT7 = "";

    public final String PROJECT_PROMPT8 = "";

    //See usage in MonthMaker
    public static final String INTERN_ASSIGNING_SUCCESS = "";

    public static final String INTERN_ASSIGNING_FAILURE = "";

    //See usage in MonthMaker
    public static final String INTERN_REMOVING_SUCCESS = "";

    public static final String INTERN_REMOVING_FAILURE = "";

    public final String FINAL_PROJECT1 = "";

    public final String FINAL_PROJECT2 = "";

    public final String FINAL_PROJECT3 = "";

    public final String REPORT_CONCLUSION = "That's all! Have a good day manager. :)";

}
