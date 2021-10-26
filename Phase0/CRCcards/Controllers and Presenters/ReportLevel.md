Class name: ReportLevel

Parent class: 

Subclasses:

Responsibilities:
* To know the status of this level(Month, Project, EndOfGame)
* To receive inputs and return the formatted report.
* To know which specific report is needed, and ask the wanted ReportMaker to make report components
* To ask ReportPresenter for the report as a formatted string and return that.
* If needed, receive Player's input to modify the player's skill

Collaborators:
* ReportMaker
* MonthReportMaker
* ProjectReportMaker
* ReportPresenter