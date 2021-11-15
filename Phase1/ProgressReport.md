# Progress report for group StackOverflowers:

## Overview
### First Meeting Oct22
Once Phase0 ended, we had a group meeting on Oct22 to discuss the following, and started on work.

1. Meeting and deciding on how to deal with Mickaels comments. (Who will be in charge of fixing what)
    - Yijia: Fixing errors and warnings in part of the code.
    - Farzana: Fixing the specification of the project.
    - Enam: Dealing with Intern so that it could be open to extensions: to respond to Micheal's employee suggestion.
2. Deciding together what we want our program to achieve after Phase 1, (aka updating the walkthrough of our program) so we know how to write the TODOs for each needed class.
    - Our overall goal was to match the initial walkthrough of our program, and focus on making things CLEAN.
3. Deciding  to split up into two groups to write the TODOs. The deadline for that was the end of week1. We split our game into roughly four parts by the 4 phases,: the starting phase, the interview phase, the month(assigning) phase and the report phase. Below is how we divided work.
    - Group 1
        - Members: Farzana, Enam, Camille
        - Responsibilities: finish writing TODOs for everything related to starting the game and during the interview phase.
    - Group 2(month phase and report phase)
        - Members: Yijia, Jason, Maggie
        - Responsibilities: finish writing TODOs for everything related to month phase and report phase.

After the meeting, the two groups split up work so that everyone had responsibilties of specific classes. During the week, group members met in clusters as well to finished highly related and TODOs that needed more discussion.Branches were not yet created since we were only writing TODOs and wanted to make sure that the overall structure and dependencies of code was clear.
We also found it helpful to modify and clarify the CRC cards even more so that it was a good reference for what was needed to be done.
##Meeting Oct29
During this meeting, we made sure that everyone looked through each other's TODOs and all things fit together. We agreed to aim at getting most things done before Nov.8.
- Our responsibilities for the code are split by classes follows:
    - Farzana: GameManager, GameMaker (-> GameGenerator: Generating Interns and projects for the game) Project, Intern
    - Enam: InterviewLevel, InterviewMaker, HRSystem, Intern, HiredIntern
    - Camille: GameMaker -> InternResponseTreeGenerator
    - Yijia: GameManager, GameMaker, Level, MonthLevel, MonthMaker, Exceptions
    - Maggie: ReportLevel, GameMaker (-> GameGenerator: Generating Interns and projects for the game), Project, Intern
    - Jacob: ReportMaker, (MonthReportMaker, ProjectReportMaker, FinalReportMaker)
    - And for classes GamePrompts and Exceptions, all were free to add to them when needed.

After that meeting, branches were started to being created, and group members starting using github features more, with pull requests and issues.

##Meeting Nov5
This was when some group members were getting busy. However, we were still able to establish our progress since everyone sent a progress on how things were going. All group members were actively reaching out and resolving issues as we went.
Here was the progress so far:
- Yijia:
    - Classes in charge of:
        - SPhase
        
        - GameManager
        
        - GameMaker
        
        - Level
        
        - MonthLevel
        
        - MonthMaker
        
        - MonthPresenter

    - Progress so far:
    
        - Finished most TODOs in MonthLevel, MonthMaker and MonthPresenter
        
        - Figured out how the project will switch from phase to phase
        
    - Things need to do:
        - Finish Month related stuff
        
        - Serialization: Figure out how the game will handle "universal commands"(save, quit)
        
        - Make the whole thing run
        
        - Exceptions at the highest level(What exceptions and how Phase will display all that)
        
        - Refactoring (code smells, CLEAN)
        
    - Things need to coordinate:
    
        - Tests

        - Exceptions = how return correct error message to player
   
        - Levels -> abstract to children
        
Farzana: 
- Progress so far:
       - Writing out all prompts in GamePrompts
       
       - Wrtiing all descriptions for our 'projects' in the game 
       
       - Created UseCase class GameGenerator and added new methods to it. 
       
       - Worked on SkillCompatibility in class Project with Maggie. 
       
       - Worked on class Project 
       
       - Working on some methods in GameMaker tht correspond to class Project
       
       - Writing up other descriptions for our games (projects, final projects, conversations)
       
       - Working on all needed txt files (for generating projects and interns)
       
       - Worked on GameGeneratorsTest
       
       - Wrote up the specification
       
       - Factory Method Design Pattern in design document 

       - Step 1 of Scenario Walkthrough in design document 
       
       - Helped Camille with SOLID principles 
       
       - Worked with Mary and Enam on fixing many errors of our code for scenario walkthrough. 

Maggie:
- Progress so far:
       - Helped out with class reportMakers and monthPresenters

       - Wrote out prompts in GamePrompts

       - Wrote up other descriptions for our games (projects, final projects, conversations)
       
       - Worked on all needed txt files (for generating projects and interns)
       
       - Wrote specifications for our 'projects' in the game

       - Implemented class Project (Worked on SkillCompatibility with Farzana)

       - Wrote test for GameGenerators
       
       - Wrote Step 3 of scenario walkthrough in design document 

       - Added javadocs to methods

Jacob:
    - Classes in charge of:
        - all reportMakers
       
        - monthPresenter
        
    - Progress so far:
        - Finished most TODOs in reportMakers
        
        - Intgrated most of the code for reportMaker
    - Things need to do:
        - Finish integrating our algorithm for calculating individual performance
        
        - Start on monthPresenter
        
        - CLEAN
        
        - Link all strings to gamePrompts

Camille:
    - Classes in charge of:
    
        - GameMaker
        
        - ResponseTree
       
        - ResponseTreeMaker
        
    - Progress so far:
    
        - Design a new class to represent the tree object
        
        - Figure out how to parse through tree to get interactive response from player
        
    - Things need to do:
    
        - figure out internResponseTreeFactory and how it related to generating interns
        
        - CLEAN
        
        - tests


Enam:
    - Classes in charge of:
    
        - HRSystem
        
        - interviewLevel
        
        - interviewMaker
        
    - Progress so far:
    
        - finished most TODOs in interviewLevel, HRSystem
        
    - Things need to do:
    
        - figure out how interviewLevel will start and end
        
        - finish implementing all classes
        
        - write test for responsible classes
        
        - test all related tests

After that meeting, we set on wrapping up everything and to have a final meeting on Nov 8. During the few days, a lot of work was finished and there were multiple calls between group members. We also used branches, pull requests, and issues to aid us in organizing our code.
## Meeting Nov8
During the last meeting, we set the final deadline to be Nov 10. All code will be written, tested, and we would have a final walk-through of the program using SPhase.
## Deadline pushed back 
- many issues with pull requests, merging and repository.
- one member did not have access to WIFI so that pushed back our deadline 
- many of the members suffered from issues with the repository and updating the main branch would not work. 
- When we would reclone our repository this would cause even more issues so this pushed our deadline further. 
