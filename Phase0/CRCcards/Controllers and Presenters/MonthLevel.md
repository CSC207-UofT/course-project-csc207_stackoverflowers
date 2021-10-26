Class name: MonthLevel

Parent class: Level

Subclasses:

Responsibilities:
* To start a month
* To show that a month ended
* To know the status of the current month (start, during, end)
* To know the commands accepted in this month.
* To ask MonthMaker to make a month and know which command leads to which method in MonthMaker.
  * getStartOfMonthPrompt()
  * checkProjectInfo()
  * checkInternInfo()
* To ask MonthMaker to perform the certain commands the player has asked for:
  * assignInternTo()
  * removeInternFrom()
  * confirmFinalDecision()
* To ask MonthPresenter to format the prompts into strings, then return that to ControllersPresenters.GameManager.
Collaborators:
* MonthMaker
* MonthPresenter