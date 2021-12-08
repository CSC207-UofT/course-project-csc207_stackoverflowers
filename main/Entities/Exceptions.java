package Entities;

import java.io.Serializable;

/*
This class will hold all the exception messages needed in the Game.
 */

public class Exceptions {
    public static final String PROJECTS_FILE_NOT_FOUND = "There is no file found to generate Projects from.";
    public static final String INTERNS_FILE_NOT_FOUND = "There is no file found to generate Interns from.";
    public static final String INTERN_ASSIGNING_FAILURE = "The intern could not be assigned to this project.";
    public static final String INTERN_UPGRADING_FAILURE = "The skill could not be assigned to this intern.";
    public static final String INTERN_REMOVING_FAILURE = "The intern could not be removed from the project.";
    public static final String UNIVERSAL_COMMAND_NOT_FOUND = "The command was not a valid universal command.";
    public static final String INVALID_COMMAND = "Sorry, the command you typed is invalid. Please check and try again.";
    public static final String INVALID_NAME_EMPTY = "Sorry, you entered a empty name. Please try again.";
    public static final String INVALID_NAME_SPACE = "Sorry, you entered a name with spaces. Please try again.";
    public static final String INVALID_NAME_CONTENT = "Sorry, you entered a name with invalid contents. Please try again.";
    public static final String NOT_LOADING_AT_START = "Load only permitted at start of the game";
}
