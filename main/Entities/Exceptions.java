package Entities;
/*
This class will hold all the exception messages needed in the Game.
 */
//TODO: figure out how to use this class throughout our Game. Feel free to add to this.
public class Exceptions {
    public static final String PROJECTS_FILE_NOT_FOUND = "There is no file found to generate Projects from.";
    public static final String INTERNS_FILE_NOT_FOUND = "There is no file found to generate Interns from.";
    public static final String INTERN_ASSIGNING_FAILURE = "The intern could not be assigned to this project.";
    public static final String INTERN_UPGRADING_FAILURE = "The skill could not be assigned to this intern.";
    public static final String INTERN_REMOVING_FAILURE = "The intern could not be removed from the project.";
    public static final String UNIVERSAL_COMMAND_NOT_FOUND = "The command was not a valid universal command.";
    public static final String INVALID_COMMAND = "Sorry, the command you typed is invalid. Please check and try again.";
}
