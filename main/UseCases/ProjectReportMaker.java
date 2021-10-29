package UseCases;
import Entities.GamePrompts;

public class ProjectReportMaker implements ReportMaker{
    //TODO: Make every string a call to GamePrompt
    @Override
    public String makeReportHeader(int month) {
        return "Here is your report for the end of " + month + "\n";
    }

    @Override
    public String makeReportIntro() {
        return "This is an end report of this completed project." + "\n";
    }


    /*
    project report format
    Project name: xxx (need to access project.projectName)
    Project overall result rating: 8/10 (randomly generated)

    Assigned interns: hhh, yyy, zzz (how do we know who are working on this project? Class Project needs an instance variable
                                     that stores the assigned interns, which first refers to None and later gets updated
                                     to refer to the list of assigned interns?)
    Interns ratings and evaluations (comments need to be related to their performances in the monthly report & their ratings):
        - hhh: 10/10: excellent leadership, great teamwork skills
        - yyy: 7/10: a diligent worker, needs to improve on efficiency
        - zzz: 3/10: poor attendance, bad teamwork

    That's all! Have a good day manager. :)
    */
    @Override
    public String makeReportBody() {
        return null;
    }

    @Override
    public String makeReportConclusion() {
        return REPORT_CONCLUSION;;
    }
}
