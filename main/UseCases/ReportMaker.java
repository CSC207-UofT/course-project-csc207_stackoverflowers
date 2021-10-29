package UseCases;

import Entities.Intern;
import Entities.Project;

import java.util.ArrayList;

//An interface class that shows all common methods used to make reports:
//TODO: * makeReportHeader()
//TODO:  * makeReportIntro()
//TODO:  * makeReportBody()
//TODO:  * makeReportConclusion()
public interface ReportMaker {

    String makeReportHeader (int month);



    String makeReportIntro ();



    String makeReportBody (String projectName, int projectProgress, ArrayList<Intern> interns, Project project);



    String makeReportConclusion();



    String bakeProjectName (String projectName);



    String bakeProgress (int projectProgress);



    String bakeInterns (ArrayList<Intern> interns);



    int calculateInternPerformance (Intern intern, Project project);



    String bakeInternsPerformances (ArrayList<Intern> interns, Project project);



}
