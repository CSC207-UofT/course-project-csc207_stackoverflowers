Class name: ReportLevel

Parent class: Level

Subclasses:

Responsibilities:
* To start reporting
* To end reporting
* To know the status of this level(AfterMonth, EndOfGame)
* To receive inputs from UI when in RPhase.
* To know which specific report is needed, and ask the wanted ReportMaker to make report components
* To ask ReportPresenter for the report as a formatted string and return that.

Collaborators:
* ReportMaker
* MonthReportMaker
* ProjectReportMaker
* ReportPresenter