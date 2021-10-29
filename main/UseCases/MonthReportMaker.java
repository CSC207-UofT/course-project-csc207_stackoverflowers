package UseCases;
import Entities.GamePrompts;

public class MonthReportMaker implements ReportMaker {
    //TODO: Make every string a call to GamePrompt
    @Override
    public String makeReportHeader(int month) {
        return "Here is your report for the end of " + month + "\n";
    }


    @Override
    public String makeReportIntro() {
        return "This is an intermediate report of this ongoing project." + "\n";
    }


    /* format for the report
    Time: xxx (time of month)(int month)
    Project name: xxx (need to access project.projectName)
    Project progress: excellent/fair/mediocre (average of internPerformance)
    Assigned interns: hhh, yyy, zzz (how do we know who are working on this project? Class Project needs an instance variable
                                     that stores the assigned interns, which first refers to None and later gets updated
                                     to refer to the list of assigned interns?)
    Interns performances (calculated by the skills * projectCompatability(A Dictionary)):
        - hhh: excellent, exhibits great leadership skills
        - yyy: great, but needs to be more efficient
        - zzz: poor, could be potentially fired
    Irregularities (randomly generated, but kinda need to be related to their performances):
        - hhh: none
        - yyy: none
        - zzz: 3 sick leaves

    That's all! Have a good day manager. :)
     */
    @Override
    public String makeReportBody() {
        return null;
    }


    @Override
    public String makeReportConclusion() {
        return REPORT_CONCLUSION; //why tf is this still red?
    }
}
