package ControllersPresenters;

import UseCases.ReportMaker;

//an abstract class
public class ReportPresenter {
    //TODO: instantiate
    public ReportPresenter () {}

    //TODO: method displayReport() that takes in multiple inputs and returns a formatted string.
    void displayReport (String header, String intro, String body, String end){
        System.out.println(header + "\n" +
                "Entities.Project Intro:" + intro + "\n" +
                body + "\n" +
                end);
    }
}
