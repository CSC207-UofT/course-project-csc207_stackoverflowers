package Entities;

import java.io.Serializable;

public class GamePrompts implements Serializable {
    public static final String GAME_SAVED_SUCCESSFUL = "Your game was successfully saved under you username. You may now quit the game.";
    //
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

    public static final String MAKE_UPGRADE_PROMPT_FINAL_MONTH = "This is the final month, no upgrade available.";

    public static final String MAKE_UPGRADE_PROMPT_MONTH = " is the skill you can upgrade this month, who do you want to give this skill to? type 'assign intern to upgrade <internName>' to use.";

    public static final String FIRST_PROMPT_BEFORE_NAME = "Welcome! My friend ";


    public static final String FIRST_PROMPT_AFTER_NAME = ", you have just been hired as a new manager. \n" +
                                                         "You're company has decided to expand the staff for some new and exciting projects.\n" +
                                                         "Your job is to hire some interns and then... Here is a brief view of the interviewee list. \n" +
                                                         "Take a glance and let's start the interview! \n";

    public static final String START_INTERVIEW_PROMPT = "Let's start the interview with the first contestant. \n\n";

    public static final String INTERN_RESPONSE_PROMPT = "The current Intern under review has the following response for you to consider:\n\n";

    public static final String NEXT_CHOICE_PROMPT = "\n Please type 'a' or 'b' to select the next question to ask:\n";

    public static final String INFORM_QUIT_GAME = "You have quit your game, and you progress has been saved to a file " +
            "with the player name you've used. Type anything to see credits and quit. Player name: ";

    public static final String HIRE_INTERN = "\n\nWould you like to hire this intern? Type 'yes' or 'no'. You are allowed " +
            "to hire a maximum of 6 interns.";

    public static final String HOW_MANY_HIRED = "\nPlease note you have hired this number of interns so far:" +
            "\nNumber of interns hired: ";

    public static final String TOO_MANY_HIRED = "\nSorry, you have already hired 6 interns. Please fire another intern " +
            "or end the interviews now.\nPlease note that if you fire an intern you cannot hire them again and the recently interviewed intern will be hired.\nType the name of the intern you would like to fire or type 'end interview' to " +
            "end the interview process.\n";

    public static final String FIRING_PROMPT = "\n\nYou may fire an intern from the following list of interns you have hired:\n\n";

    public static final String CONFIRM_FIRING = "You have successfully fired this intern.";

    public static final String HIRED_INTERN = "Congratulations! You have hired this intern! ";

    public static final String NOT_HIRED_INTERN = "You have chosen not to hire this intern. ";

    public static final String CHOSEN_INTERNS_TO_HIRE = "\nYou have chosen to hire the following interns: \n";

    public static final String NEXT_INTERVIEW_INTERN_PROMPT = "It is now time to interview the next intern.\nPlease select the first question to ask. ";

    public static final String INTERVIEWED_TO_HIRE = "\n\nThe most recently interviewed intern has now been hired.\nThis is the intern you have chosen to hire:\n";

    public static final String END_OF_INTERVIEW_PROMPT = "Congratulations! You have successfully completed interviewing all " +
            "interns. Type anything to proceed to the next level of the game.";


    // not all the project prompts will be used, the projectGenerator will output a list and choose 4 projects
    // for the game from all the prompts here

    public static final String START_OF_MONTH_PROMPT_BEFORE_NAME = "Welcome to the start of the month. \n " +
            "Your job for this month, " ;

    public static final String START_OF_MONTH_PROMPT_AFTER_NAME = " , is to manage some new projects. Assign the interns you see fit to the project!\n ";

    public static final String CONFIRM_UPGRADE = "Now you have successfully chosen your intern to upgrade. Type 'confirm all decisions' if your sure of your choice and want to proceed to the next phase!";
    public static final String CONFIRM_ASSIGNING = "All interns have been assigned to a project, and here is the entire assignment you made so far. If you are sure with your decisions, type 'confirm all decisions'.\n" +
                                                   "If not, feel free to use the same given commands to modify your decisions.\n";

    public static final String END_OF_MONTH_PROMPT = "Now you finished your part of the job, all interns have started working hard on their own. \n" +
                                                     "The month went by quickly, and now you can view this month's progress! \n" +
                                                     "type anything to continue and view the report that has been generated... ";



    public static final String FINAL_MONTH_PROMPT_BEFORE_PROJECT = "Ah, welcome to the last month where you get to assign interns to projects.\n" +
                                                                   "Your final task is to assign one intern you think has performed well who will be able to accomplish a bigger project on their own. Here's the project:\n";
  
    public static final String AVAILABLE_COMMANDS_IN_MONTH = "\n=====\n" + "Here are a list of commands you can do:\n" +
                                                                  "assign intern to project <Intern Name> <Project Name>\n" +
                                                                  "remove intern from project <Intern Name> <Project Name>\n" +
                                                                  "check interns info\n" +
                                                                  "check project info\n" + "check assign\n";


    public static final String END_OF_FINAL_MONTH_PROMPT = "Now you have finished assigning your SUPER intern to the FINAL project." +
            "Must feel good huh? Sit back and relax as we return to you the report on how the chosen one has done" +
            "after the month has finished. \n "+
            "type anything to continue and view the report that has been generated... ";
    

    public static final String PROJECT1_NAME = "Logo Design";
    public static final String PROJECT2_NAME = "A Bug's Life";
    public static final String PROJECT3_NAME = "Game Character Design";
    public static final String PROJECT4_NAME = "Hackathon Leaders";
    public static final String PROJECT5_NAME = "Customer Data Privacy & Security";
    public static final String PROJECT6_NAME = "Coding Lessons to Young Children";

    public static final String PROJECT7_NAME = "Social Media Marketing";
    public static final String PROJECT8_NAME = "Product Management";
    public static final String FINAL_PROJECT1_NAME = "Software Engineering";
    public static final String FINAL_PROJECT2_NAME = "Machine Learning & AI Research Assistant";
    public static final String FINAL_PROJECT3_NAME = "COVID-19 Tracking System Update";

    public static final String SKILL1 ="Confidence";
    public static final String SKILL2 ="Teamwork";
    public static final String SKILL3 ="Leadership";
    public static final String SKILL4 ="Efficiency";
    public static final String SKILL5 ="Endurance";
    public static final String SKILL6 ="Flexibility";
    public static final String SKILL7 ="Observant";
    public static final String SKILL8 ="Communication";
    public static final String SKILL9 ="Responsible";
    public static final String SKILL10 ="Creativity";
    public static final String SKILL11 ="Artistic";
    public static final String SKILL12 ="Patience";
    public static final String SKILL13 ="Analytical";

    public static final String PROJECT1_DESCRIPTION = "Our company wishes to rebrand itself this upcoming Winter, our first step in rebranding our company is to create a new logo\n" +
            "that will encourage more employees to apply to work here and will emulate trust amongst our clients\n" +
            "This project requires individuals to be creative, artistic and imaginative\n" +
            "The interns will be working with world-renowned graphic designer to create a new logo that represents\n" +
            "our company morals, values and overall message. Thus, a clear understanding of how our company operates is required\n" +
            "Please ensure that you pick a team where each intern possesses at least one skill that is required to complete this task.";

    public static final String PROJECT2_DESCRIPTION = "The software developers in the company all went on vacation and did not fully test the code for a project they were working\n" +
            "When they finally returned and tested their project, they realised their code was all riddled with bugs!\n" +
            "The interns for this project must work together with the software developers to find the root of the problem and\n" +
            "write more tests to exterminate all the bugs!\n" +
            "Pick interns that will be able to think quick on their feet and be flexible in accepting the tasks they are assigned.\n" +
            "The interns must also be able to work together, support each other and provide assistance to all members assigned to this task to achieve success.";

    public static final String PROJECT3_DESCRIPTION = "For this project, a client company is asking for assistance in creating, designing and coding new game characters\n" +
            "for the launch of their new video game in two months. The interns will be working with the client company's illustrators and 2 senior software engineers\n" +
            "from our company to invent original and unique fighting game characters.\n" +
            "The interns required for this project must be creative and artistic in their design choices.\n" +
            "Since they will also help in coding, they must also be confident in their decisions.";

    public static final String PROJECT4_DESCRIPTION = "Our company runs a Hackathon yearly for aspiring computer science students.\n" +
            "Each department must send 3 interns that will lead and mentor a team each in the Hackathon competition.\n" +
            "Hackathons are great opportunities to network, build experience and improve one's coding ability. The leaders must be \n" +
            "able to provide advice to the participants about the Hackathon competition itself and also about their future career paths.\n" +
            "Therefore, the interns you choose for this task must have immense leadership skills and confidence in leading their teams,\n" +
            "they must also be responsible to a certain extent to ensure they provide adequate advice to their junior aspiring developers.";

    public static final String PROJECT5_DESCRIPTION = "Protecting consumer data should be a top priority for any organization.\n" +
            "In this project, the interns will work with the cybersecurity department within the company to ensure the\n" +
            "safe storage of customer information. They will work on how to recognize and prevent common cyber threats\n" +
            "such as malware, phishing, SQL injection, and DNS tunneling. They will also help ensuring that our company's\n" +
            "encryption practices are up-to-date and adhere to the General Data Protection Regulation. ";

    public static final String PROJECT6_DESCRIPTION =
            "Computer programming for kids has many benefits that stretch far beyond the computer screen: critical thinking,\n" +
                    "creativity, and persistence, etc. That's why our company values teaching coding to children. Interns in this\n" +
                    "project will introduce k1-9 students to coding through the virtual classroom. They will work together to come\n" +
                    "up with activities, worksheets, games, homework, and simple interactive apps that pique children's interests\n" +
                    "meanwhile convey meaningful coding concepts. In addition, regular communications and accommodations to children's\n" +
                    "special needs will be crucial.";

    public static final String PROJECT7_DESCRIPTION =
            "In this project, interns will be required to create and publish contents on the company's Facebook, Instagram,\n" +
                    "and Twitter pages to promote the company's products and services. They will also monitor likes/dislikes, comments,\n" +
                    "reposts, and interact with the online communities to build relationships with them. Furthermore, they will be in\n" +
                    "charge of employing data analytics tools to track the progress, success, and engagement of ad campaigns. The end\n" +
                    "goal is to employ social media platforms effectively to attract potential customers and establish the company's\n" +
                    "desired social media \"culture\".";

    public static final String PROJECT8_DESCRIPTION =
            "Product management is the practice of strategically driving the development, market launch, and continual support\n" +
                    "and improvement of a company’s products. Chosen interns will be working with a team of engineers to develop the\n" +
                    "strategy and roadmap of a specific product for our company as well as to QA test the product. They will do so in\n" +
                    "accordance with the following phases: idea generation, idea screening, concept development, and testing, building\n" +
                    "a market strategy, product development, market testing, and market commercialization. Furthermore, product management\n" +
                    "interns also act as a bridge between the engineering and marketing teams, helping to do market research and come up\n" +
                    "with effective marketing strategies for our products.";

    public static final String FINAL_PROJECT1_DESCRIPTION =
            "Your job is to pick the best programmer among the existing interns to develop and improve, along with other programmers,\n" +
                    "the company's applications. Full-stack engineering intern will be combining the best of both worlds by working on both\n" +
                    "front-end and back-end technologies, seeing how data flows through the application and how it’s transferred and displayed.\n" +
                    "By being actively involved with the technological components the customer sees and with the back-end data that powers\n" +
                    "the site, the intern will quickly develop an understanding of the different technologies and will be able to implement\n" +
                    "optimizations to enhance performance. The selected intern should have experience in building a feature end-to-end.";

    public static final String FINAL_PROJECT2_DESCRIPTION =
            "The Machine Learning & AI Research team is looking for an assistant. The assistant will work under the supervision of\n" +
                    "the project manager. He/she likely will not have coding duties, but may help the programmers test or debug their code.\n" +
                    "He/she will also work with algorithms and the mathematical aspects of artificial intelligence. Additionally, certain\n" +
                    "project logistics will be a part of the assistant's responsibilities. The intern will receive step-by-step guidance\n" +
                    "from industry experts, and through intensive immersion, they will develop specialized, highly valued skills regarding\n" +
                    "Machine Learning & AI.";

    public static final String FINAL_PROJECT3_DESCRIPTION =
            "Our company's COVID-19 Tracking System needs a huge update! Before we were only able to do a survey to ensure employees\n" +
                    "were not feeling any of the COVID symptoms, however now we are able to ask for employees to upload their proof of\n" +
                    "vaccination. The intern assigned to this task must code an interface that is user-friendly so that our employees are\n" +
                    "able to upload their 'vaccine passports' without any hassle. The company also would like to add a new feature to the\n" +
                    "Tracking System that allows employees to request work from home whenever they feel any symptoms of a fever or cold.\n" +
                    "The intern assigned to this project must be efficient in their coding and responsible enough to take on this project by themselves.";

    public static final String PROJECT1_PROMPT = "Project Name: " + PROJECT1_NAME + "\n" +
            "Project Description: " + PROJECT1_DESCRIPTION + "\n" +
            "\n" +
            "Length of Project: 2 months \n" +
            "Team Size: 3\n" +
            "Skills Required: Creativity, Observant, Artistic" + "\n";

    public static final String PROJECT2_PROMPT = "Project Name: " + PROJECT2_NAME + "\n" +
            "Project Description: " + PROJECT2_DESCRIPTION + "\n" +
            "Length of Project: 2 months \n" + "Team Size: 3 \n" + "Skills Required: Teamwork, Flexibility, Efficiency" + "\n";

    public static final String PROJECT3_PROMPT = "Project Name: " + PROJECT3_NAME + "\n" +
            "Project Description: " + PROJECT3_DESCRIPTION + "\n" +
            "Length of Project: 2 months \n" + "Team Size: 3 \n" + "Skills: Creativity, Confidence, Artistic" + "\n";

    public static final String PROJECT4_PROMPT = "Project Name: " + PROJECT4_NAME + "\n" +
            "Project Description: " + PROJECT4_DESCRIPTION + "\n" +
            "Length of Project: 2 months \n" +
            "Team Size: 3 \n" +
            "Skills Required: Leadership, Confidence, Responsible" + "\n";

    public static final String PROJECT5_PROMPT = "Project Name: " + PROJECT5_NAME + "\n" +
            "Project Description: " + PROJECT5_DESCRIPTION + "\n" +
            "Length of Project: 2 months \n" +
            "Team Size: 3 \n" +
            "Skills Required: Teamwork, Creativity, Efficiency" + "\n";

    public static final String PROJECT6_PROMPT = "Project Name: " + PROJECT6_NAME + "\n" +
            "Project Description: " + PROJECT6_DESCRIPTION + "\n" +
            "Length of Project: 2 months \n" +
            "Team Size: 3 \n" +
            "Skills Required: Patience, Creativity, Responsible" + "\n";

    public static final String PROJECT7_PROMPT = "Project Name: " + PROJECT7_NAME + "\n" +
            "Project Description: " + PROJECT7_DESCRIPTION + "\n" +
            "Length of Project: 2 months \n" +
            "Team Size: 3 \n" +
            "Skills Required: Efficiency, Teamwork, Endurance" + "\n";

    public static final String PROJECT8_PROMPT = "Project Name: " + PROJECT8_NAME + "\n" +
            "Project Description: " + PROJECT8_DESCRIPTION + "\n" +
            "Length of Project: 2 months \n" +
            "Team Size: 3 \n" +
            "Skills Required: Leadership, Communication, Responsible" + "\n";

    public static final String FINAL_PROJECT1_PROMPT = "Project Name: " + FINAL_PROJECT1_NAME + "\n" +
            "Project Description: " + FINAL_PROJECT1_DESCRIPTION + "\n" +
            "Length of Project: 2 months \n" +
            "Team Size: 1 \n" +
            "Skills Required: Teamwork, Endurance, Creativity" + "\n";

    public static final String FINAL_PROJECT2_PROMPT = "Project Name: " + FINAL_PROJECT2_NAME + "\n" +
            "Project Description: " + FINAL_PROJECT2_DESCRIPTION + "\n" +
            "Length of Project: 2 months \n" +
            "Team Size: 1 \n" +
            "Skills Required: Efficiency, Communication, Analytical" + "\n";

    public static final String FINAL_PROJECT3_PROMPT = "Project Name: " + FINAL_PROJECT3_NAME + "\n" +
            "Project Description: " + FINAL_PROJECT3_DESCRIPTION + "\n" +
            "Length of Project: 2 months \n" +
            "Team Size: 1 \n" +
            "Skills Required: Efficiency, Patience, Responsible" + "\n";

    public static final String INTERN_ASSIGNING_SUCCESS = "The intern was successfully assigned to the project specified.";
    public static final String INTERN_REMOVING_SUCCESS = "The intern was successfully removed from the project specified.";
    public static final String FIRST_PROMPT_AFTER_INTERNS = "Type anything to begin the interviewing process!";

    public static final String END_OF_MONTH_REPORT_PROMPT = "That is the end of the report. Type anything to continue.";
    public static final String END_OF_FINAL_MONTH_REPORT_PROMPT = "That's the end of the final month report. Type anything to continue.";

    public final String REPORT_CONCLUSION = "That's all! Have a good day manager. :)";

    public static final String END_PROMPT = "Thank you so much for playing our game.\n" +
            "Creators:" + "\n" + "Mary Yijia Li\n" +
            "Farzana Rahman\n" +
            "Maggie Huang\n" +
            "Jizhong (Jacob) Lyu\n" +
            "Camille Marie Alve\n" +
            "Enam Hermon.\n";
}
