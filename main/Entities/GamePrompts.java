package Entities;

public class GamePrompts {
    /* this is the class for only game prompts, no method, similar to the java exception class in week 2 slides

    the prompt that asks player to enter name called ASK_FOR_NAME;

    the first display prompt called FIRST_PROMPT_BEFORE_NAME and FIRST_PROMPT_AFTER_NAME (See UseCases.GameMaker.firstPrompt to
    see what content is in which):
    - "new manager at the company"
    -"here are the list of interns..."
    - format: name, age, skills
     */
    public final String ASK_FOR_NAME = "Please enter your name:\n";

    public final String FIRST_PROMPT_BEFORE_NAME = "Welcome! My friend ";

    public final String FIRST_PROMPT_AFTER_NAME = ", you have just been hired as a new manager. Here are your potential interns. Choose wisely ...\n";
}
