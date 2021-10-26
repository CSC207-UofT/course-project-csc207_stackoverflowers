import java.util.HashMap;

public class InterviewIntern extends Intern{
    // this is an intern class for new Interns to the company, partaking in Interviews
    // these are the type of Intern in IntervieweeList

    private String interviewInternName;
    private int interviewInternAge;
    private HashMap<String, Integer> interviewInternSkills;

    /**
     * Construct an interviewIntern, giving them the given interviewInternName,
     * interviewInternAge and interviewInternSkills.
     *
     * @param interviewInternName   The name of this Intern
     * @param interviewInternAge    The age of this Intern
     * @param interviewInternSkills A map of skills where the key is the name of the skill and the value is the percentage.
     */
    public InterviewIntern(String interviewInternName, int interviewInternAge,
                           HashMap<String, Integer> interviewInternSkills){
        super(interviewInternName, interviewInternAge, interviewInternSkills);
    }

}
