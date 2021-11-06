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

    public final String FIRST_PROMPT_AFTER_NAME = """
            , you have just been hired as a new manager.\s
            You're company has decided to expand the staff for some new and exciting projects.
            Your job is to hire some interns and then... Here is a brief view of the interviewee list.\s
            Take a glance and let's start the interview!\s
            """; //
    //TODO:
    public static final String ASK_FOR_INTERVIEWEE_NAME = "Now, which potential would you like to interview first?";
    //see usage in firstPrompt

    public final String QUIT_GAME_CONFIRMATION = "Are you sure you want to quit the game?";

    public final String HIRE_INTERN = "";

    public final String HIRED_INTERN = "Congratulations! You have hired this intern! ";

    public final String PLAYER_CHOICE = ""; // what would you like to choose etc

    public final String CONFIRM_HIRING = "Are you sure you want to hire this intern?";

    public final String NEXT_CANDIDATE = "";

    // not all of the project prompts will be used, the projectgenerator will output a list and choose 4 projects
    // for the game from all the prompts here

    public static final String START_OF_MONTH_PROMPT_BEFORE_NAME = "Welcome to the start of the month. \n " +
            "Your job for this month, " ;

    public static final String START_OF_MONTH_PROMPT_AFTER_NAME = "is to manage some new projects. \n " +
            "Below is a list of projects that you will need to assign certain interns to:";

    public static final String START_OF_MONTH_PROMPT_AFTER_PROJECTS = """
            Here are a list of commands you can do:
            assign intern to project <Intern Name> <Project Name>\s
            remove intern from project <Intern Name> <Project Name>\s
            check intern info\s
            check project info\s
            """;

    public static final String CONFIRM_ASSIGNING = """
            All interns have been assigned to a project, and here is the entire assignment you made so far. If you are sure with your decisions, type 'confirm all decisions'.
            If not, feel free to use the same given commands to modify your decisions.\s
            \s""";

    public static final String END_OF_MONTH_PROMPT = "Now you finished your part of the job, all interns have started " +
            "working hard on their own. The month went by quickly, and now you can view this month's progress! " +
            "type anything to continue and view the report that has been generated... ";

    public final String PROJECT_PROMPT1 = """
            Project Name: Create New Logo\s
            Project Description: This project requires individuals to be creative, artistic and imaginative
            The interns will be working with world-renowned graphic designer to create a new logo that represents\s
            our company morals, values and overall message. Thus, a clear understanding of how our company operates is required\s
            Please ensure that you pick a team where each intern possesses at least one skill that is required to complete this task.
            \s
            Length of Project: 2 months\s
            Team Size: 3
            Skills: Creativity, Observant, Artistic""";

            /*"""
            Project Name: Create New Logo for Company.\s
            Project Description: This project requires individuals to be creative, artistic and imaginative.
            You will be working with a world-renowned graphic designer to create a new logo that represents\s
            our company morals, values and message. Thus, you must have a clear understanding of how our  company operates.
            Please ensure you pick interns that have at least one skill that is required to complete this task.
            \s
            Length of Project: 2 months\s
            Team Size: 3\s
            Skills: Creativity, Observant, Artistic and Teamwork""";

            "Project Name: Create New Logo \n" + "Project Description: This project requires individuals to be creative, artistic and imaginative\n" +
            "The interns will be working with world-renowned graphic designer to create a new logo that represents \n" +
            "our company morals, values and overall message. Thus, a clear understanding of how our company operates is required \n" +
            "Please ensure that you pick a team where each intern possesses at least one skill that is required to complete this task.\n \n" +
            "Length of Project: 2 months \n" +
            "Team Size: 3\n" +
            "Skills: Creativity, Observant and Artistic";

             */

    public final String PROJECT_PROMPT2 = """
            Project Name: A Bug's Life \s
            Project Description: The software development team all went on vacation and didn't fully test their work and now\040
            they are riddled with bugs in their code!\s
            Assign interns who can support the company's software engineers in exterminating all these bugs! The interns must be\s
            orderly, flexible and quick on their feet for this task. They must also be able to work efficiently as a team for this task.\040
            \s
            Length of Project: 2 months\s
            Team Size: 3\s
            Skills: Teamwork, Flexibility, Efficiency""";

    public final String PROJECT_PROMPT3 = """
            Project Name: Game Character Design\s
            \s
            Skills: Creativity, Confidence, Artistic""";

    public final String PROJECT_PROMPT4 = """
            Project Name: Hackathon Leaders\s
            \s
            Skills: Leadership, Confidence, Responsible""";

    public final String PROJECT_PROMPT5 = """
            Project Name: Customer Data Privacy & Security\s
            \s
            Skills: Teamwork, Creativity, Efficiency""";

    public final String PROJECT_PROMPT6 = """
            Project Name: Teaching Coding to Young Children\s
            \s
            Skills: Patience, Creativity, Responsible
            """;

    public final String PROJECT_PROMPT7 = """
            Project Name: Social Media Management\s
            \s
            Skills: Efficiency, Teamwork, Endurance\s
            """;

    public final String PROJECT_PROMPT8 = """
            Project Name: Customer Feedback Summary and Analysis\s
            \s
            Skills: Observant, Communications, Analytical\s
            """;

    //See usage in MonthMaker
    public static final String INTERN_ASSIGNING_SUCCESS = "The intern was successfully assigned to the project specified.";

    public static final String INTERN_ASSIGNING_FAILURE = "The intern could not be assigned to this project.";

    //See usage in MonthMaker
    public static final String INTERN_REMOVING_SUCCESS = "The intern was successfully removed from the project specified.";

    public static final String INTERN_REMOVING_FAILURE = "The intern could not be removed from the project.";

    public final String FINAL_PROJECT1 = """
            Final Project Name: Add New Features to the Website\s
            \s
            Skills: Observant, Confidence, Creativity""";

    public final String FINAL_PROJECT2 = """
            Project Name: Machine Learning Research Assistant\s
            \s
            Skills: Communication, Endurance, Observant""";

    public final String FINAL_PROJECT3 = """
            Project Name: Debug Company's COVID-19 Tracking System\s
            \s
            Skills: Efficiency, Patience, Responsible""";

    public final String REPORT_CONCLUSION = "That's all! Have a good day manager. :)";

}
