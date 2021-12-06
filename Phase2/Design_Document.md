#Design Document for StackOverflowers

## SOLID

How well does your design adhere to the SOLID design principles?
Give us specific examples of how your design adheres to the SOLID principles.
If you found that something in your design wasn't good, tell us about that too!
Pretending part of your design is good — when you know it isn't — can potentially hurt your mark significantly!
Acknowledging bad design can earn you marks and demonstrates understanding — especially if you discuss how you could fix it if you had more time!

- Abstract class Level and its subclasses (InterviewLevel, MonthLevel, ReportLevel) all adhere to the Open/Closed Principle. InterviewLevel, MonthLevel and ReportLevel all extend Level.
  GameManager has an instance attribute that stores the current level, and its methods will work regardless of whether it's an InterviewLevel, MonthLevel or a ReportLevel.
  Following the Open/Closed Principle for abstract class level has allowed us to easily add a new level subclass named StartLevel that was able to easily extend the abstract class.

- Classes FinalReportMaker, ProjectReportMaker and MonthReportMaker all implement the ReportMaker interface and adhere to the Open/Closed and Liskov Substitution Principle.
  The ReportMaker Interface ensures that all necessary methods are implemented in each of the different ReportMakers. The ReportMakers all behave in the same way (or similarly enough) that
  the methods in ReportLevel work regardless of the type of ReportMaker being used. It also adheres to the Open/Closed Principle since it is extended by the LSP.

- We have created new classes PMSystem, StartLevel and GameGenerators so that our code could follow the Single Responsibility Principle. Previously, HRSystem was in charge of many methods,
  however, by splitting the HRSystem class to PMSystem, we were able to ensure each class had only one responsibility. GameMaker was split to GameGenerators so that
  GameGenerator's only responsibility was to generate anything that would be required for the game. StartLevel class was also created so that we could take some burden off of class GameManager
  thus our code now adheres to the Single Responsibility Principle.

- We have ensured that our code has not violated the Interface Segregation Principle; for example, every method in the abstract class ReportMaker is used in all of its
  subclasses.

- Finally, we have tried our best to also follow Dependency Inversion Principle. For example, GameManager only asks abstract class Level for details
  instead of its subclasses, therefore, GameManager relies on the abstraction instead of concrete details.

## Clean Architecture

Is your program consistent with Clean Architecture?
Show us with something like a CRC model or UML diagram.
Describe a scenario walk-through and highlight how it demonstrates Clean Architecture.

Mary will show UML diagram produced from intelliJ in lab. 

## Scenario Walkthrough: 

Starting the game -> Interview Level -> Assign interns to projects

STEP 1: Starting SPhase

When SPhase is first run, SPhase creates a ControllersPresenters.GameManager.
When ControllersPresenters.GameManager is initialized, it creates a new UseCases.GameMaker and UseCases.GameGenerators.
When UseCases.GameMaker and Usecases.GameGenerators is initialized, it creates an HRsystem and Entities.GamePrompts needed for the game (the actual
prompts of the game are stored in it as instances). ControllersPresenters.GameManager then randomly generates the list of interns, projects and final project needed for the game from UseCases.GameGenerators where GameGenerator also stores them as a list in UseCases.HRSystem. SPhase then asks Entities.GamePrompts for the ask-for-name prompt and presents it to the player. The player’s name is then stored in UseCases.HRSystem. Then, SPhase takes the User’s input and asks ControllersPresenters.GameManager for the first prompt. ControllersPresenters.GameManager will then askUseCases.GameMaker to construct the first prompt with the player’s input.

UseCases.GameMaker will use prompts in Entities.GamePrompts to construct the greeting part of the prompt, and then it will
ask UseCases.HRSystem to make the list of inters into a prompt. UseCases.HRSystem will do so by asking the Entities.InterviewIntern Class
to make each intern into a string, and format that. Then, UseCases.GameMaker collects and returns the prompt to ControllersPresenters.GameManager, which then returns it to SPhase for it to output to the player.The output welcomes the player to the game along with the interns and their age and skills(from HRsystem). These are candidates the player will interview in their first level of the game. 

STEP 2: Interview level 

During the Interview portion of the game, a UseCases.InterviewMaker is created which initializes an HRSystem as well as an
Entities.InterviewIntern needed to run the Interview. ControllersPresenters.InterviewLevel oversees the creation of the initial InterviewMaker,
ensuring that no part of CLEAN architecture is violated. The interview level begins by ControllersPresenters.InterviewLevel
displaying the first Entities.InterviewIntern to interview initially from the list of Entities.InterviewIntern, indicating the start of an interview. ControllersPresenters.InterviewLevel then displays options 'a' or 'b' of two possible responses the player could ask and the player's choice of input determines what response is given by the Entities.InterviewIntern.
These possible choices and responses are gotten from the UseCases.ResponseTreeMaker associated with an intern.

About ResponseTreeMaker: Each InterviewIntern has its own unique ResponseTree, which stores all the possible dialogue during an interview.
It shows the questions the Player can ask, and the respective response from the intern. These ResponseTrees are
generated in the ResponseTreeMaker class. The ResponseTreeMaker takes in an intern and generates a ResponseTree
based on the intern's skills. In Phase 1, we had settled on generating a tree with a fixed number of questions and a fixed dialogue, but now
we can successfully generate a unique ResponseTree for every intern. We implemented ResponseTreeMaker so that each question pertains to
an intern's skill, so now you can learn more about the intern's strengths and personality during the interview. The dialogue will now be
much more diverse than it was before as since we've added more potential questions and answers. For our InterviewLevel, we're only
asking three questions for every intern, but with our ResponseTree structure, we can easily change the number of questions we can ask an intern during the interview,
and we can easily add even more questions so that you can get more in-depth information about the intern.

The InterviewIntern responds by HRSystem obtaining
the InterviewIntern's corresponding response for the question from the response tree in ResponseTreeMaker (which has been assigned to each intern in
Entities.Intern). The player is then prompted to input "yes" if they wish to hire the InterviewIntern or "no" otherwise. This is recorded
and upon hiring, HRSystem updates the list of HiredInterns by adding the newly hired intern. After confirming a Player's decision to hire an intern,
the Player now interviews the next intern from the InterviewInternList until all interns have been interviewed. This regular flow of interview occurs when we have hired
less than 6 interns. Since the maximum number of interns to be hired is 6, ControllersPresenters.InterviewLevel prompts the player
if they would like to end the level early once tha maximum has been reached. If not, the currentHRSystem initialized in InterviewMaker fires the most recently interviewed intern from the list of HiredIntern and 
hires the new intern that the player wishes to hire. This goes on until all 10 InterviewInterns have been interviewed unless 
the player inputs the prompt 'end interview'. At either the end of round of interviews or at the stage where the player decides
to end an interview early, Entities.GamePrompts signifies the end
of an interview by outputting an end of interview prompt. 


STEP 3: Month level (Assign Interns to Project)

In the first two months (month is the time unit we use in this game; each month is considered as a level), 2 projects
will be generated per month which the player assigns interns to (so in total 4 projects will be generated). Controller.
MonthLevel will get the status of the current month (start, during, end), and asks UseCase.MonthMaker to randomly
generate two projects. Each project has a name, description, length (2 months), team size (3 interns), and skills compatibility.
Skills compatibility is a hashmap that maps a skill with a number b/n 0.00 and 1.00, which indicates the skill's
compatability with this project (so the higher the percentage associated with a particular skill, the more this project
requires this skill)

STEP 4: Check monthly report

There is a report for every month in game. MonthlyReportMaker is used as the reportMaker when the currentMonth is 1,3 or 5. This is determined by the java logic (month < 6 & month % 2 == 1). ProjectReportMaker is used as the reportMaker when the currentMonth is 2 or 4. This is determined by java logic (month < 4 & month % 2 == 0).  FinallReportMaker is used as the reportMaker when the currentMonth is 6. All versions of reportMakers helps ReportLevel generate Strings and other variables in different situations. ReportLevel's getOutPutString is called in getOutPut in GameManager. The report is shown when function is called, and upon seeing the report, player's next input until finishedUpgrading(currentMonth) is true are considered as commands to ReportLevel. 

The report format:

  Here is your report for the end of x month 

  Project name: xxx 

  Project progress: an integer

  Assigned interns: hhh|yyy|zzz 

  Interns performances:
    - hhh: (int)
    - yyy: (int)
    - zzz: (int)
  That's all! Have a good day manager. :)

  After the report has been displayed, the player is able to choose a HiredIntern to upgrade a skill. The skill that is given is randomly generated by the reportMaker, and can be assigned to any intern wanted. After that, the player can confirm their decisions to move onto the next part of the game.
  
Step 5: quit game



## Design Patterns
Most of our code structure was already implemented in phase 1, and so we did not see the need to introduce any more design patterns. 

1. FACTORY DESIGN PATTERN 

We used the factory design pattern using ReportLevel to evaluate the player's response, and the interface class ReportMaker that is implemented by the ProjectReportMaker, FinalReportMaker and MonthReportMaker subclasses. We created an interface class ReportMaker and let the concrete classes MonthReportMaker, 
FinalReportMaker and ProjectReportMaker implement this interface. The interface holds the virtual constructor that defines the general description of the methods needed in each subclass (makeReportHeader(), makeReportIntro() etc.) and the subclasses hold other needed requirements. For example, MonthReportMaker shows progress of each month in the game whereas ProjectReportMaker shows the outcome of the project in each level. GameManager then instantiates the concrete 
classes, and it can get a report object by checking the player’s input. So if a player wants to check the month report, ReportLevel simply outputs the month report without having to reach into the specific MonthReportMaker class. This was implemented as soon as Phase 0 was over, when DQ1 was due. 


2. BUILDER PATTERN DESIGN 

For ReportMaker's, we used the design pattern Builder, as they are three different builders (ReportMakers) that have build a report using the same structure. Using The Builder pattern, we are able to assemble each component of the Report in ReportMaker, so that the ReportLevel only needs to generally ask the currentReportMaker for a report, instead of using a bunch of if-statements each time. 

3. OBSERVER DESIGN PATTERN 

We also kind of got inspiration from the Observer Design pattern, as our Abstract Class Level changes statuses, and those statuses need to be reported to GameManager. Right now, we are making GameManager check each time if the status of the currentLevel has changed, so that it fits more into the Observer design pattern. 


4. TEMPLATE DESIGN PATTERN 

We incorporated a template design pattern when creating an abstraction for our Entities.Intern class, which was formerly a single class. 
With our current project, we initially created an entity for an Intern (which is a character in our HR System Simulator that is assigned projects after being interviewed). 
Upon design of the code, we realized there is a need for distinction between two types of Intern, i.e. a HiredIntern (an intern who has passed the interview process and can be assigned with projects) and an InterviewIntern (which engages in the Interview Phase of the project). 
Thus, Intern was abstracted, implementing the invariant methods, leaving variant methods to be implemented by the unique characteristics of the subclasses HiredIntern and InterviewIntern. 
In this way, Intern is our template that can be extended in the instance where a new type of Intern needs to be defined with its own unique behaviours.
This was implemented after completion of Phase 0, during planning for new classes to be implemented in Phase 1.


## Use of GitHub Features, Code Style and Documentation

For Phase 2, we were much more careful about our pull requests by ensuring that everyone had properly updated their main. We also utilised git issues 
by posting as many as we could, even minor ones. Due to this, we were able to use git issues as a reminder board for the whole team. In phase 1, we noticed our commits 
were quite unbalanced, so we encouraged members to try to balance their commits with one another and this helped in getting everyone more involved in the code. 


## Testing
We tried to cover more bases in Phase 2 by testing some of our controllers/presenters for phase 2. We also tested our new UseCase class PMSystem to ensure it was working 
effectively. 

## Refactoring
At the beginning of our project in phase 0, HRSystem was initially a Entity class as it stored information. As we were coding, we realised that HRSystem was manipulating entities \
rather than acting as one. So, to keep our design follow CLEAN, we did a refactoring of making HRSystem a UseCase instead of an entity. See pull request #4.

During coding there were also small refactors here and there to change method names, parameters, and also extract helper methods to make code more readable. See pull request #3 for GameMaker,  #19 for three ReportMakers, and #21 for MonthLevel.

For Phase 2, a major refactoring we did was to create a new UseCase class PMSystem and Controller/Presenter class StartLevel. We created PMSystem to follow the Single Responsibility Principle 
so that UseCase HRSystem is now only responsible for handling interns and PMSystem is responsible for handling anything related to project in our game. StartLevel was also 
created to take some burden off of class GameManager. 

class ResponseTreeMaker also had major changes for phase 2, in phase 1 we were only able to traverse through one 'side' (child1->c3 or child2 -> c6) of the tree so our options were very limited. Now, the tree is 
set up according to the intern's skill so the interview feels a lot more immersive, and we are able to traverse through both 'sides' in a branch. (child1 -> c3/c4 or child2 -> c5/c6). 

            root
          /     \
        child1   child2 
      /      \    /     \
    c3       c4   c5      c6 

Some minor changes include: changing code so that intern's skill now shows only 2 decimal places so it is easier to read and deleting text files from our main code and 
moving all required text files to resources folder. 


## Code Smells We Fixed From Phase 1
- For phase 2, we split up GameManager by creating class StartLevel. And GameMaker was also split to GameGenerators. Therefore, both classes are not as bloated as they used to be in phase 1.
- We also split HRSystem to PMSystem. Before we were worried that class HRSystem is violating the Single Responsibility Principle, however, after the split, this problem was solved.  
- We removed any unused methods.
- In phase 1, SPhase was reliant on GamePrompts which was an entity, but now it only depends on the Controller GameManager. This adheres to the CLEAN Architecture.


## Code Organization
Our code is now organized by the four layers of CLEAN architecture. Since right now there are four main sections of the code, and there aren't many classes, it is quite easy to find the class wanted based on our naming method. 
We found that this way of organizing code helped us be aware of dependencies: as we could clearly see if we violated the dependency rule by looking at our imports.

We have also created a save folder now, all saved games can be found in the saved folder. All text files are also now under Resources folder which makes it easier for us 
to access and edit them. 


## Functionality
We tries the best to match the description and walk-through that we've first settled on. 
I believe that the functionality of our code is sufficient, since we decided to implement variations of Levels (Final months and Final reports), and also have an interactive conversation tree. Although our player's choice of input may be limited compared to others, our group's aim is to follow CLEAN architecture and find means to collaborate well to make what we have written not only work, but also presentable. We focused on the structure and the ability to extend and present a non-intimidating program.
Our code has implemented the save and load function. We also have the command quit, and since now we don't ask for confirmation to quit, we are basically saving and quitting at the same time for quit.

Our load function is able to now save player with duplicate name, which is an improvement from Phase 1. Another aspect of our code is that during interview level in phase 1, player would have to type out word-for-word the question 
player wishes to ask the intern, however now in phase 2, player can simply type in 'a' or 'b' which is much more user-friendly. We have also made it so that player can either type in 
capital A/B or lowercase a/b and both will work during the interview.

