 # Farzana: 
My role during Phase 2 was to work on ResponseTreeMaker with Camille and Maggie. We talked through the structure, wrote up new text files for the tree, and created a new algorithm together which helped Camille 
in implementing the code. I also worked on editing our code as the week went by, for example I changed the code so that classes InterviewLevel, InterviewMaker and HRSystem would recognize both 
uppercase and lowercase A or B input from player. I also worked on editing minor issues we had in the code for ResponseTreeMaker. I also wrote up most of the aspects for our Design Document and Specification 
for phase 2. 

Significant pull request: I was responsible for creating UseCase class GameGenerators and I think this was significant because it helped our code in following the SINGLE RESPONSIBILITY PRINCIPLE. (see pull request
12: https://github.com/CSC207-UofT/course-project-csc207_stackoverflowers/pull/12). 

# Enam: 

My role in Phase 2 was primarily to carry out the changes in the flow of interviewing through changing interview Level and interview Maker as
well as the Use Cases it relies on, i.e. HR System, PM System and Game prompts. I also refactored HR System to both HR System and PM System.
By deciding to split up HR System into PM System as well,
we ensured the single use principle was kept as responsibilities related to interns and project were split. In interviewing, I added the limit on
the number of interns a player could hire i.e. 6 and added functionality to fire an intern after the threshold has been reached or end it early as well as adding an
option to type 'a or 'b' instead of the full question, allowing better player usability. Also edited minor changes like javadocs and testing.

Significant pull request: My significant pull request was where the usability features mentioned above were added to interview level. (Pull request #101) which 
can be seen here: https://github.com/CSC207-UofT/course-project-csc207_stackoverflowers/pull/101


# Mary(Yijia): 
My role during Phase 2 was to work on general debugging and testing. Since I wrote code related to loading and saving, I mainly changed stuff according to TA's feedback, and also raised issues as soon as I found issues in SPhase. I was also in charge of writing the accessibility report with Jacob.

Significant pull request: My significant pull request is pull request #79, where I was fixing and refactoring things. Although I enjoyed writing the bulk of code, I think that this pull request reflects on all the detailed modifying of code that I did: I helped refactoring and also delete code that was worked on by others so that our whole system would fit together. It also helped out code rely on the DEPENDENCY INVERSION PRINCIPLE, as I made gameManager depend on the getOutput() method by an abstract class Level. (See pull request #79: https://github.com/CSC207-UofT/course-project-csc207_stackoverflowers/pull/79/commits)


# Maggie:
During Phase 2, I worked on completing ResponseTreeMaker with Camille and Farzana. ResponseTreeMaker is the class that's 
in charge of player's (interviewer) question choice and the interviewee's response during the interview process. This class 
makes the interview process interactive. Together, we came up with the data structure, algorithm, methods, and text files
for this class. In addition, I worked on polishing formats of certain game prompts.

Significant pull request: My significant pull request is linked right here: https://github.com/CSC207-UofT/course-project-csc207_stackoverflowers/pull/9 
and https://github.com/CSC207-UofT/course-project-csc207_stackoverflowers/pull/15.
In these two pull requests, I finished and further improved the implementation for class Project, which is an essential 
entity class that represents a project in the game.


# Camille:
During Phase 2, I mainly worked on fixing ResponseTreeMaker with Maggie and Farzana. We created new questions and answers, and assigned corresponding skills for them
so that each intern would have a unique ResponseTree generated based on their skills. I implemented the helper methods in the class, and we all worked on figuring
out how to generate the ResponseTrees together. I also wrote up a few tests for the helper methods in ResponseTreeMakerTest, but I commented
them out since they are private methods. I also worked on our Design document.

Significant pull request: My significant pull request was adding all the new changes to ResponseTreeMaker, along with the 
text files. ResponseTreeMaker had a lot of issues in Phase 1 since all the intern's response trees were exactly the same, and the nodes were constructed manually. After these changes were added,
the dialogue during the interviews are now much more diverse. Here is the link: https://github.com/CSC207-UofT/course-project-csc207_stackoverflowers/pull/97

# Jacob: 
For Phase 2, my job was to test and debug the project with Mary. I wrote the code for reports, hence I raised issues and fixed problems related to parts of our code related to reports. I participated in the writing of accessibility report. I also raised several issues inregard to IDE settings for our project.

Significant pull request: 
