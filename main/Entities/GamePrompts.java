package Entities;

import java.io.Serializable;

public class GamePrompts implements Serializable {
    public static final String GAME_SAVED_SUCCESSFUL = "Your game was successfully saved under you username. You may now quit the game.";
    //TODO:
    /*
    - any textual prompt we would need
    - feel free to add more as we continue coding our project
    - if class gets too long, maybe consider creating another prompt class
     */

    public static final String REPORT_HEADER = "Here is your final project report!" + "\n";

    public static final String PROJECT_NAME_HEADER = "Project name: ";

    public static final String PROJECT_PROGRESS_HEADER = "Project progress: ";

    public static final String INTERN_PERFORMANCE_HEADER = "Assigned interns: ";

    public static final String INTERN_INFO_HEADER = "Here is the list of interns that you have hired:";

    public static final String PROJECT_INFO_HEADER = "Here are the projects that you are responsible for:";

    public static final String INTERN_UPGRADING_SUCCESS = "The skill was successfully assigned to the intern.";

    public static final String ASK_FOR_NAME = "Welcome to HRSimulator! To start the game, " +
            "please enter your name.\n" +
            "To load an already existing game under your name, please type 'load <name>'.";

    public static final String FIRST_PROMPT_BEFORE_NAME = "Welcome! My friend ";

    public static final String FIRST_PROMPT_AFTER_NAME = ", you have just been hired as a new manager. \n" +
                                                         "You're company has decided to expand the staff for some new and exciting projects.\n" +
                                                         "Your job is to hire some interns and then... Here is a brief view of the interviewee list. \n" +
                                                         "Take a glance and let's start the interview! \n";

    public static final String ASK_FOR_INTERVIEWEE_NAME = "Now, which potential would you like to interview first?";
    //see usage in firstPrompt

    public static final String INFORM_QUIT_GAME = "You have quit your game, and you progress has been saved to a file " +
            "with the player name you've used. Player name:";

    public static final String HIRE_INTERN = "Would you like to hire this intern?";

    public static final String HIRED_INTERN = "Congratulations! You have hired this intern! ";

    public static final String PLAYER_CHOICE = "Choose from either A or B";


    public static final String CONFIRM_HIRING = "Are you sure you want to hire this intern?";


    public static final String NEXT_CANDIDATE = "Would you like to move on to the next candidate?";


    // not all of the project prompts will be used, the projectgenerator will output a list and choose 4 projects
    // for the game from all the prompts here

    public static final String START_OF_MONTH_PROMPT_BEFORE_NAME = "Welcome to the start of the month. \n " +
            "Your job for this month, " ;

    public static final String START_OF_MONTH_PROMPT_AFTER_NAME = "is to manage some new projects. \n " +
            "Below is a list of projects that you will need to assign certain interns to:";

    public static final String START_OF_MONTH_PROMPT_AFTER_PROJECTS = "Here are a list of commands you can do:\n" +
                                                                      "assign intern to project <Intern Name> <Project Name>\n" +
                                                                      "\n" +
                                                                      "remove intern from project <Intern Name> <Project Name>\n" +
                                                                      "\n" +
                                                                      "check intern info\n" +
                                                                      "\n" +
                                                                      "check project info\n";

    public static final String CONFIRM_ASSIGNING = "All interns have been assigned to a project, and here is the entire assignment you made so far. If you are sure with your decisions, type 'confirm all decisions'.\n" +
                                                   "If not, feel free to use the same given commands to modify your decisions.\n";

    public static final String END_OF_MONTH_PROMPT = "Now you finished your part of the job, all interns have started " +
            "working hard on their own. \n The month went by quickly, and now you can view this month's progress! \n" +
            "type anything to continue and view the report that has been generated... ";

    public static final String FINAL_MONTH_PROMPT_BEFORE_PROJECT = "" ;
    public static final String FINAL_MONTH_PROMPT_AFTER_PROJECT = "";

    public static final String END_OF_FINAL_MONTH_PROMPT = "Now you finished assigning your SUPER intern to the FINAL project." +
            "Must feel good huh! Sit back and relax as we return to you the report on how the chosen one has done " +
            "after the month has finished. \n "+
            "type anything to continue and view the report that has been generated... ";
    

    public static final String PROJECT1_NAME = "Logo Design";
    public static final String PROJECT2_NAME = "A Bug's Life";
    public static final String PROJECT3_NAME = "Game Character Design";
    public static final String PROJECT4_NAME = "Hackathon Leaders";
    public static final String PROJECT5_NAME = "Customer Data Privacy & Security";
    public static final String PROJECT6_NAME = "Coding Lessons to Young Children";
    public static final String PROJECT7_NAME = "Social Media Platform Management";
    public static final String PROJECT8_NAME = "Customer Feedback Summary and Analysis";
    public static final String FINAL_PROJECT1_NAME = "Website Featuring";
    public static final String FINAL_PROJECT2_NAME = "Machine Learning Research Assistant";
    public static final String FINAL_PROJECT3_NAME = "COVID-19 Tracking System Debug";

    public static final String PROJECT1_DESCRIPTION = "";
    public static final String PROJECT2_DESCRIPTION = "";
    public static final String PROJECT3_DESCRIPTION = "";
    public static final String PROJECT4_DESCRIPTION = "";
    public static final String PROJECT5_DESCRIPTION = "";
    public static final String PROJECT6_DESCRIPTION = "";
    public static final String PROJECT7_DESCRIPTION = "";
    public static final String PROJECT8_DESCRIPTION = "";
    public static final String FINAL_PROJECT1_DESCRIPTION = "";
    public static final String FINAL_PROJECT2_DESCRIPTION = "";
    public static final String FINAL_PROJECT3_DESCRIPTION = "";

    public static final String PROJECT1_PROMPT = "Project Name: Create New Logo \n" +
                                                 "Project Description: This project requires individuals to be creative, artistic and imaginative\n" +
                                                 "The interns will be working with world-renowned graphic designer to create a new logo that represents \n" +
                                                 "our company morals, values and overall message. Thus, a clear understanding of how our company operates is required \n" +
                                                 "Please ensure that you pick a team where each intern possesses at least one skill that is required to complete this task.\n" +
                                                 " \n" +
                                                 "Length of Project: 2 months \n" +
                                                 "Team Size: 3\n" +
                                                 "Skills: Creativity, Observant, Artistic";

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

    public static final String PROJECT2_PROMPT = "Project Name: A Bug's Life  \n" +
                                                 "Project Description: The software development team all went on vacation and didn't fully test their work and now \n" +
                                                 "they are riddled with bugs in their code! \n" +
                                                 "Assign interns who can support the company's software engineers in exterminating all these bugs! The interns must be \n" +
                                                 "orderly, flexible and quick on their feet for this task. They must also be able to work efficiently as a team for this task. \n" +
                                                 " \n" +
                                                 "Length of Project: 2 months \n" +
                                                 "Team Size: 3 \n" +
                                                 "Skills: Teamwork, Flexibility, Efficiency";

    public static final String PROJECT3_PROMPT = "Project Name: Game Character Design \n" +
                                                 " \n" +
                                                 "Skills: Creativity, Confidence, Artistic";

    public static final String PROJECT4_PROMPT = "Project Name:" + PROJECT4_NAME + "\n" +
                                                 "Project Description: " + PROJECT4_DESCRIPTION + "\n" +
                                                 "Length of Project: 2 months \n" +
                                                 "Team Size: 3 \n" +
                                                 "Skills: Leadership, Confidence, Responsible";

    public static final String PROJECT5_PROMPT = "Project Name:" + PROJECT5_NAME + "\n" +
                                                 "Project Description: " + PROJECT5_DESCRIPTION + "\n" +
                                                 "Length of Project: 2 months \n" +
                                                 "Team Size: 3 \n" +
                                                 "Skills: Teamwork, Creativity, Efficiency";

    public static final String PROJECT6_PROMPT = "Project Name:" + PROJECT6_NAME + "\n" +
                                                 "Project Description: " + PROJECT6_DESCRIPTION + "\n" +
                                                 "Length of Project: 2 months \n" +
                                                 "Team Size: 3 \n" +
                                                 "Skills: Patience, Creativity, Responsible";

    public static final String PROJECT7_PROMPT = "Project Name:" + PROJECT7_NAME + "\n" +
                                                 "Project Description: " + PROJECT7_DESCRIPTION + "\n" +
                                                 "Length of Project: 2 months \n" +
                                                 "Team Size: 3 \n" +
                                                 "Skills: Efficiency, Teamwork, Endurance";

    public static final String PROJECT8_PROMPT = "Project Name:" + PROJECT8_NAME + "\n" +
                                                 "Project Description: " + PROJECT8_DESCRIPTION + "\n" +
                                                 "Length of Project: 2 months \n" +
                                                 "Team Size: 3 \n" +
                                                 "Skills: Observant, Communications, Analytical";

    public static final String FINAL_PROJECT1_PROMPT = "Project Name:" + FINAL_PROJECT1_NAME + "\n" +
                                                       "Project Description: " + FINAL_PROJECT1_DESCRIPTION + "\n" +
                                                       "Length of Project: 2 months \n" +
                                                       "Team Size: 1 \n" +
                                                       "Skills: Observant, Confidence, Creativity";

    public static final String FINAL_PROJECT2_PROMPT = "Project Name:" + FINAL_PROJECT2_NAME + "\n" +
                                                       "Project Description: " + FINAL_PROJECT2_DESCRIPTION + "\n" +
                                                       "Length of Project: 2 months \n" +
                                                       "Team Size: 1 \n" +
                                                       "Skills: Communication, Endurance, Observant";

    public static final String FINAL_PROJECT3_PROMPT = "Project Name:" + FINAL_PROJECT3_NAME + "\n" +
                                                       "Project Description: " + FINAL_PROJECT3_DESCRIPTION + "\n" +
                                                       "Length of Project: 2 months \n" +
                                                       "Team Size: 1 \n" +
                                                       "Skills: Efficiency, Patience, Responsible";

    //See usage in MonthMaker
    public static final String INTERN_ASSIGNING_SUCCESS = "The intern was successfully assigned to the project specified.";

    //See usage in MonthMaker
    public static final String INTERN_REMOVING_SUCCESS = "The intern was successfully removed from the project specified.";

    public final String REPORT_CONCLUSION = "That's all! Have a good day manager. :)";

    public static final String END_PROMPT = "Thank you so much for playing our game.";
    //TODO: modify END_PROMPT so it includes credits?
}
