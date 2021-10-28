package ControllersPresenters;

//an abstract class
public class ReportPresenter extends Presenter {
    //TODO: instantiate
    public ReportPresenter () {}

    //TODO: method displayReport() that takes in multiple inputs and returns a formatted string.
    String displayReport (String header, String intro, String body, String end){
        return (header + "\n" +
                "Entities.Project Intro:" + intro + "\n" +
                body + "\n" +
                end);
    }
}
