package ControllersPresenters;

//an abstract class
public class ReportPresenter implements Presenter {
    //TODO: instantiate
    public ReportPresenter () {}

    //TODO: method displayReport() that takes in multiple inputs and returns a formatted string.

    public String displayOutput (String header, String intro, String body, String end){
        return (header + "\n" +
                "Intro:" + intro + "\n" +
                body + "\n" +
                end);
    }

    @Override
    public String displayOutput() {
        return null;
    }
}
