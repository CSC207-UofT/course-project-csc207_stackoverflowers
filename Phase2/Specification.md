
## Name of game: HR manager simulator 

This project will imitate a game that allows the player to interview interns, assign interns to projects, assess the intern's performance and more. It is comprised 
of aproximately 3 levels. The first level where the player will interview some randomly generated interns, the second level where player will assign interns that 
they hired from the first level to projects that will be generated from our game. The final level is when the player will assign the intern they believe performed
the best to one final project. 

As soon as the game is started (when our SPhase starts running), SPhase will ask the player for it's name to start the game. A GameManager will also be created to manage all inputs and outputs of the game. After receiving the User's first input, GameManager will instantiate useCase class GameGenerator, which will create the list of 
interns and projects needed for this game. It will store this information in HRSystem, and also store the name of the Player.
Then, it will prompt the player to start the interview Level. 

- While running the game, we have some game prompts:
    - quit - saves and quits the game
    - save - saves the game as is.
    - load - loads the game 
    - all the game prompts will be stored in the Entities.GamePrompts class
    
## First Level of the Game = Interview Level     

In the Interview level, the player will be able to interview the list of InterviewInterns, the interns will have automated responses to the interview question based off of their skills and the player will also be able to pick their own responses which will determine how the interview level progresses. The player will be able to choose if they will hire the intern after each interview is over. The hired interns will be put in the company HR system. Therefore, the ‘hired’ list in the UseCases.HRSystem will be updated to reflect the player’s choices.

## Second Level of the Game = Project Level 

In the second level of the game, after the player has hired 6 interns from the list of 10 provided, the player will then be able to assign interns to projects 
based off of the intern's skills and the skills required to complete the projects. 

Each project runs for approximately 2 months in our game, and note that month therefore is just a unit of time we are using.
Level two will run for a total of 4 months with a total of 4 projects in which 2 projects will be shown to player in the beginning of month 1 and 
2 more projects will be shown to player at the beginning of month 3.
At those times, the player will be able to assign, remove a certain intern from the project, check their assignments so far, and also check interns and projects info.
After all projects have been assigned interns, the "month" part of the project has ended, and the report part will start. 
- The intern's compatibility with the project will be calculated based off 
of their skills which will be displayed to the player in the project reports they will receive every time the one project is finished. During the 2 months of 
each project, the player will also be shown month reports that allow them to see the progress of each project after every month. 

The monthly report will show how each project and the interns assigned to it and their progress is, and will also show any irregularities that occur during the months (intern on sick leave, intern failed task etc). 

The project report that is shown after every 2 months is over will show all things from month report as well as the conclusion of the project (whether it went well or not). 

## Final Level of the Game = Final Project Level 
 
After the initial 2 months are over which will mark the end of the first 2 projects, another 2 month cycle will occur and the player will be able to assign 
interns to the remaining two projects. When the second level of the game is over, the player will enter the final stage where they will assign one intern to one 
final project. Which is the 5th and 6th month.

## Structure/Entities 

The Entities are comprised of class Entities.GamePrompts that stores all prompts we will display to the player for our game and class Entities.Exceptions 
that stores prompts that are exception that will be displayed to the player. Also comprised of class Intern which is an abstract class that allows us to 
distinguish between interns the player will interview, Entities.InterviewIntern, and interns that the player will hire, Entities.HiredIntern, after the 
interview/ first level of the game. Finally, we have Entities.Project which is the class for the 'projects' the player will be able to assign the HiredInterns to
for the second and final level of the game.


