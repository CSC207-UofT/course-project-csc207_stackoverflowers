HR MANAGER SIMULATOR GAME 
- Structure/layer:
    - Entities: GamePromts, Entities.HRSystem, Entities.Intern, Entities.Project
    - Use cases: UseCases.GameMaker, UseCases.InterviewMaker, MonthMaker, ReportMaker
    - Controllers and Presenters: ControllersPresenters.GameManager, InterviewLevel, InterviewPresenter, Level, MonthLevel, MonthPresenter,
      ControllersPresenters.Presenter, ReportLevel, ReportPresenter
    - UI: Iphase, Mphase, RPhase, SPhase

- Running the project will start a shell that prompts the user to enter their name.
    - Every time the game is first started, new interns are randomly generated and new projects are generated for the player.
    - the player will see the new interns that they must interview
    - the player will be able to check the intern information such as their skills and age

- While running the game, we have some game prompts:
    - quit - quit the game
    - restart - restarts the whole game with new interns and new projects
    - all the game prompts will be stored in the Entities.GamePrompts class


- The game will be comprised of 3 levels.

- In the Interview level, the player will be able to interview the interns,
    - the interns will have automated responses to the interview question and the player will also be able to
      pick their own responses which will determine how the interview level progresses.
    - the player will be able to choose if they will hire the intern after each interview
    - the hired interns will be put in the company HR system
    - Therefore, the ‘hired’ list in the Entities.HRSystem must be updated to reflect the player’s choices.

- In the project level, the player will be able to assign interns to projects
    - based on the description and requirements of the project, the player will be able to check the intern skills and
      assign interns to projects

- In the monthlyProgress level, the monthly report will show how each project is progressing
    - monthly report will show each project and the interns assigned to it and their progress
    - will show any irregularities that occur during the months ( intern on sick leave, intern failed task etc)
    - month is referred to as a stage in the game, not a specific date
    - the course of the game runs for 6 months, therefore each ‘month’ is just consider a period of the time
      for the game to progress
