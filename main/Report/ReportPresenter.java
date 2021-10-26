package Report;

import java.util.List;

//an abstract class
public class ReportPresenter implements ReportMaker {
    //TODO: instantiate
    public ReportPresenter () {}

    //TODO: void methods
    void displayReport (){
        String header = makeReportHeader();
        String intro = makeReportIntro();
        String body = makeReportBody();
        String end = endReport();
        System.out.println(header + "\n" +
                "Project Intro:" + intro + "\n" +
                body + "\n" +
                end);
    }
}
