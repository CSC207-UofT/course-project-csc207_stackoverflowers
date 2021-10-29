package UseCases;
import Entities.GamePrompts;

public class FinalReportMaker implements ReportMaker {
    //TODO: Make every string a call to GamePrompt
    @Override
    public String makeReportHeader(int month) {
        return "Here is your final project report!" + "\n";
    }

    @Override
    public String makeReportIntro() {
        return null;
    }

    @Override
    public String makeReportBody() {
        return null;
    }

    @Override
    public String makeReportConclusion() {
        return REPORT_CONCLUSION;
    }
}
