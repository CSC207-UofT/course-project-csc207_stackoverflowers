package UseCases;
import Entities.GamePrompts;

public class FinalReportMaker implements ReportMaker {
    @Override
    public String makeReportHeader(int month) {
        return "Here is your final project report!" + "\n";
    }

    @Override
    public String makeReportIntro() {
        return null;
    }

    //这个格式和其他那两个差不多，但别忘了这是最后的project，所以只有一个intern（见mary在discord上发的照片）
    @Override
    public String makeReportBody() {
        return null;
    }

    @Override
    public String makeReportConclusion() {
        return REPORT_CONCLUSION;
    }
}
