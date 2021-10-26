import java.util.HashMap;

public abstract class InterviewIntern extends Intern{
    /*
    this class will have everything intern has
    - regular interns are part of the interviewee list
    - hired intern will be able to take part in projects, have update_skill method and can get fired


     */
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
