Class name: GameManager

Parent class:

Subclasses:

Responsibilities:
* to receive output for game-level commands:(start game, quit game, restart game)
  * startGame
  * quitGame
  * restartGame
* to throw exceptions (if the command doesn't exist based on the level we are in)
* to give first prompt
* to know which game we are in(as private variable?)
* To progress the game by feeding input and receiving output from the wanted levels

Collaborators:
* GameMaker
* InterviewLevel
* MonthLevel
* ReportLevel