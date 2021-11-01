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

    public final String CONFIRM_CHOICE = "Are you sure you want to hire this intern?";

    public final String NEXT_CANDIDATE = "";

    // not all of the project prompts will be used, the projectgenerator will output a list and choose 4 projects
    // for the game from all the prompts here

    public static final String START_OF_MONTH_PROMPT_BEFORE_NAME = "Welcome to the start of the month. \n " +
            "Your job for this month, " ;

    public static final String START_OF_MONTH_PROMPT_AFTER_NAME = "is to manage some new projects. \n " +
            "Below is a list of projects that you will need to assign certain interns to:";

    public static final String START_OF_MONTH_PROMPT_AFTER_PROJECTS = "Here are a list of commands you can do:\n" +
            "assign intern to project <Intern Name> <Project Name> \n" +
            "remove intern from project <Intern Name> <Project Name> \n" +
            "check intern info \n" +
            "check project info \n";

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
