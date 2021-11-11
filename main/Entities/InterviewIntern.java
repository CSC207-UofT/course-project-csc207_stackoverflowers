package Entities;

import UseCases.ResponseTree;

import java.util.ArrayList;
import java.util.HashMap;

public class InterviewIntern extends Intern{
    // this is an intern class for new Interns to the company, partaking in Interviews
    // these are the type of Entities.Intern in IntervieweeList

    private String interviewInternName;
    private int interviewInternAge;
    private HashMap<String, Integer> interviewInternSkills;
    private ResponseTree<ArrayList<String>> responseTree;

    /**
     * Construct an interviewIntern, giving them the given interviewInternName,
     * interviewInternAge and interviewInternSkills
     *
     * @param interviewInternName   The name of this Entities.Intern
     * @param interviewInternAge    The age of this Entities.Intern
     * @param interviewInternSkills A map of skills where the key is the name of the skill and the value is the percentage.
     */
    public InterviewIntern(String interviewInternName, int interviewInternAge,
                           HashMap<String, Integer> interviewInternSkills){
        super(interviewInternName, interviewInternAge, interviewInternSkills);
    }

    public void setResponseTree(ResponseTree<ArrayList<String>> responseTree) { this.responseTree = responseTree; }

    public ResponseTree<ArrayList<String>> getResponseTree() { return this.responseTree; }
}
