#Design Document for group StackOverflowers

## SOLID

How well does your design adhere to the SOLID design principles?
Give us specific examples of how your design adheres to the SOLID principles.
If you found that something in your design wasn't good, tell us about that too!
Pretending part of your design is good — when you know it isn't — can potentially hurt your mark significantly!
Acknowledging bad design can earn you marks and demonstrates understanding — especially if you discuss how you could fix it if you had more time!

- Abstract class Level and its subclasses (InterviewLevel, MonthLevel, ReportLevel) adhere to the OPEN/CLOSED Principle. InterviewLevel, MonthLevel and ReportLevel all extend Level. GameManager has an instance attribute that stores the current level, and its methods will work regardless of whether its an InterviewLevel, MonthLevel or a ReportLevel. 
- We decided to add Start Level and it was able to extend from Level interface
- 
- If we wanted to add a new level to our game, we would be able to do so easily by creating a new subclass that extends Level.
- Classes FinalReportMaker, ProjectReportMaker and MonthReportMaker all implement the ReportMaker interface and adhere to the OPEN/CLOSED and LISKOV SUBSTITUTION Principle. The ReportMaker Interface ensures that all necessary methods are implemented in each of the different ReportMakers. The ReportMakers all behave in the same way (or similarly enough) that the methods in ReportLevel work regardless of the type of ReportMaker being used. It also adheres to the Open/Closed Principle since it is extended by the LSP.
- Parts of our code don't really adhere to the SINGLE RESPONSIBILITY Principle; for example GameMaker and GameGenerator has many responsibilities. GameMaker was initially in charge of setting up the game (generating interns, projects, etc.) and saving/loading. Now, GameGenerators is in charge of generating interns and projects. Since generating interns and projects are very different, it may be helpful to split up GameGenerators as having classes with multiple responsibilities can be difficult because they often change and make your software hard to maintain. HRSystem also has lots of responsibilities, so it could also be split up.
- PM System and HR  system 

For Interface Segregration Principle: we have not violated this principle because every method in our interfaces are used in the subclasses. 

For Dependency Inversion Principle: we tried our best to follow this principle. CLass Level is an abstract class and GameManager only asks Level instead of the concrete classes so it is dependent on abstarctions rather than concrete 

## Clean Architecture

Is your program consistent with Clean Architecture?
Show us with something like a CRC model or UML diagram.
Describe a scenario walk-through and highlight how it demonstrates Clean Architecture.

Mary will show UML diagram produced from intelliJ in lab. 

## SCENARIO WALKTHROUGH: 

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
displaying a prompt asking Player to choose who they would like to interview initially, indicating the start of an interview. The Player's response is then recorded and updated in the HRSystem created by UseCases.InterviewMaker.
After selecting a specific Entities.InterviewIntern, the InterviewIntern's questions associated with their unique response tree from ResponseTreeMaker
are outputted, allowing player to choose which question to ask the Entities.InterviewIntern. 

ABOUT RESPONSETREEMAKER: Each InterviewIntern has its own unique ResponseTree, which stores all the possible dialogue during an interview.
So it shows the questions the Player can ask, and the respective response from the intern. These ResponseTrees are
generated in the ResponseTreeMaker class. The ResponseTreeMaker takes in an intern and generates a ResponseTree
based on the intern's attributes. As of now, we've settled on a fixed number of questions and a fixed dialogue, however,
with this ResponseTree structure, we can easily change the number of questions we can ask an intern during the interview,
and we can easily add different questions so the dialogue is more diverse.

The InterviewIntern responds by HRSystem obtaining
the InterviewIntern's corresponding response for the question from the response tree in ResponseTreeMaker (which has been assigned to each intern in
Entities.Intern). The player is then prompted to input "yes" if they wish to hire the InterviewIntern or "no" otherwise. This is recorded
and upon hiring, HRSystem updates the list of HiredInterns by adding the newly hired intern. After confirming a Player's decision to hire an intern,
the Player now interviews the next intern from the InterviewInternList until all interns have been interviewed. Then, Entities.GamePrompts signifies the end
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
- There is a report for every month in game. MonthlyReportMaker is used as the reportMaker when the currentMonth is 1,3 or 5. This is determined by the java logic (month < 6 & month % 2 == 1). ProjectReportMaker is used as the reportMaker when the currentMonth is 2 or 4. This is determined by java logic (month < 4 & month % 2 == 0).  FinallReportMaker is used as the reportMaker when the currentMonth is 6. All versions of reportMakers helps ReportLevel generate Strings and other variables in different situations. ReportLevel's getOutPutString is called in getOutPut in GameManager. The report is shown when function is called, and upon seeing the report, player's next input until finishedUpgrading(currentMonth) is true are considered as commands to ReportLevel. 
- The report format:
  Here is your report for the end of " + month 

  Project name: xxx 

  Project progress: an integer

  Assigned interns: hhh|yyy|zzz 

  Interns performances:
    - hhh: (int)
    - yyy: (int)
    - zzz: (int)
  That's all! Have a good day manager. :)
  
  Step 5: Project Report
  - PLEASE FILL IN 




## Design Patterns

1. FACTORY DESIGN PATTERN 

We used the factory design pattern using GameManger to evaluate the player's response and the interface class ReportMaker that is implemented by the ProjectReportMaker, FinalReportMaker and MonthReportMaker subclasses. We created an interface class ReportMaker and let the concrete classes MonthReportMaker, 
FinalReportMaker and ProjectReportMaker implement this interface. The interface holds the virtual constructor that defines the general description of the methods needed in each subclass (makeReportHeader(), makeReportIntro() etc.) and the subclasses holds the other requirements needed for example MonthReportMaker show progress of each month in the game whereas ProjectReportMaker will show the outcome of the project in each level. GameManager then instantiates the concrete 
classes, and it can get a report object by checking the player’s input. So if a player wanted to check the month report, GameManager simply outputs the month report  it without having to reach into the specific MonthReportMaker class. This was implemented as soon as Phase 0 was over, when DQ1 was due. 

Currently, GameManager is responsible for instantiating many classes and is the sole class that evaluates the player's inputs. In the future, we would like to create
a separate factory class ReportFactory that is responsible for only evaluating player inputs regarding the reports. 


2. BUILDER PATTERN DESIGN 

- Also for ReportMaker's, we used the design pattern Builder, as they are three different builders(ReportMakers) that have build a report using the same structure. Using The Builder pattern, we are able to assemble each component of the Report in ReportMaker, so that the ReportLevel only needs to generally ask the currentReportMaker for a report, instead of using a bunch of if- statements each time. 

3. OBSERVER DESIGN PATTERN 
- We also kind of got inspiration from the Observer Design pattern, as our Abstract Class Level will change statuses, and those statuses need to be reported to GameManager. Right now we are making GameManager check each time if the status of the currentLevel has changed, so that it fits more into the Observer design pattern. 


4. TEMPLATE DESIGN PATTERN 

We incorporated a template design pattern when creating an abstraction for our Entities.Intern class which was formerly a single class. 
With our current project, we initially created an entity for an Intern (which is a character in our HR System Simulator that is assigned projects after being interviewed). 
Upon design of the code, we realized there is a need for distinction between two types of Intern, i.e. a HiredIntern (an intern who has passed the interview process and can be assigned projects) and an InterviewIntern (which engages in the Interview Phase of the project). 
Thus, Intern was abstracted, implementing the invariant methods, leaving variant methods to be implemented by the unique characteristics of the subclasses HiredIntern and InterviewIntern. 
In this way, Intern is our template that can be extended in the instance where a new type of Intern needs to be defined with its own unique behaviours.
This was implemented after completion of Phase 0, during planning for new classes to be implemented in Phase 1.


## Use of GitHub Features, Code Style and Documentation
Warnings were fixed as we went, and we tried our best to review each other's pull request. We didn't use the issue feature much since a lot of issues were solved through discord. But issues that weren't solved on the spot were put up on Git as reminders. We also had Maggie that was in charge of putting Javadocs for most classes. 

- this time we were more careful abvout pull requests and issues so we ran into less problems 
- tried to balance our commits 

## Testing
Since there is a time crunch, our group decided that we would focus on writing test cases for the Controller/Presenters and UseCases. 
Things that are hard to test:
    - Deserializing was hard to test, since there was no equalsTo overriden in our classes.
    - ADD MORE HERE!!!!
A significant portion of your code should be tested to earn full marks for this (run your tests with coverage to check).

- covered more bases. 

## Refactoring
Is there evidence that your team has refactored code in a meaningful way during the project?
- At the beginning, our team thought that HRSystem would be a entity, as it stored information. However, as we went, we found that it was needed alot by the UseCases, and also that it more so "manipulated" entities than be one of them. So, to keep our design follow CLEAN, we did a refactoring of making HRSystem a UseCase instead of a entity. See pull request #4.
- During coding there were also small refactors here and there to change method names, parameters, and also extract helper methods to make code more readable. See pull request #3 for GameMaker,  #19 for three ReportMakers, and #21 for MonthLevel.


-PM System 
- Start Level 
- - Text files moved and deleted from main to resources 
- Double changed so that it's easier to see 
- chnaged responsetree maker 

## Are there any obvious code smells still in your code that you missed fixing?
- Kind of worried about duplicate code
- GameManager and GameMaker are quite bloated classes: we could work on fixing that
- GameManager can be split into sections. Currently, GameManager is the only class that evaluates player input. For example, we can take any methods that are related to reports from GameManager into a new class.
- A method in ResponseTreeMaker (generateInternResponses) is really long as every tree node was created manually - this will be fixed in Phase 2.
- There is a duplicate method in GameGenerators and ResponseTreeMaker. Making an interface won't get rid of the duplicate code, and the two classes shouldn't have the same parent class as they don't have much in common other than this duplicate method.



fixed boated classes 
- fixed HR system 
- removed unused methods 
- single responsibility rules -> start level -> sphase instead of game manager 
- GameManager is no ok 
- GameManager separated to start level we took off the burder 
- no more unused methods 

## Code Organization
Our code is now organized by the four layers of CLEAN architecture. Since right now there are four main sections of the code, and there aren't many classes, it is quite easy to find the class wanted based on our naming method. 
We found that this way of organizing code helped us be aware of dependencies: as we could clearly see if we violated the dependency rule by looking at our imports.

-> save folder 
all of the text files are in resources, and all the saved games in saved folder 

## Functionality
Our code tries the best to match the description and walk-through that we've first settled on. However, we were a little ambitious about adding Covid related features into the code, which will not be included for now.
I believe that the functionality of our code is sufficient, since we decided to implement variations of Levels (Final months and Final reports), and also have an interactive conversation tree. Although our player's choice of input may be limited compared to others, our group's aim is to follow CLEAN architecture and find means to collaborate well to make what we have written not only work, but also presentable. We focused on the structure, and also focused on the ability to extend and present a non-intimidating structure.
Our code has implemented the save and load function. We also have the command quit, and since now we don't ask for confirmation to quit, we are basically saving and quitting at the same time for quit.


- a and b and stuff 
- load is now able to save player with duplicate name 
