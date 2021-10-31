package ControllersPresenters;


public class ReportPresenter implements Presenter {

    /**
     *  Create a ReportPresenter object.
     */
    public ReportPresenter () {}


    /**
     * Return a formatted report with the inputs.
     *
     * @param header the header of the report
     * @param intro the intro od the report
     * @param body the body of the report
     * @param end the conclusion of the report
     */
    public String displayOutput (String header, String intro, String body, String end){
        return (header + "\n" +
                "Intro:" + intro + "\n" +
                body + "\n" +
                end);
    }


    // This method is still work-in-progress?
    @Override
    public String displayOutput() {
        return null;
    }
}
