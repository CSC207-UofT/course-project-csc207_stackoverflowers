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
