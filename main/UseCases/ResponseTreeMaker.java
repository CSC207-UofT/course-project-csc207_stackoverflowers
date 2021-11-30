package UseCases;

import Entities.InterviewIntern;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ResponseTreeMaker {
    /* This ResponseTreeMaker class is responsible for generating and assigning a response tree
    to an Intern.
     */

    private final InterviewIntern intern;

    public ResponseTreeMaker(InterviewIntern intern) {
        this.intern = intern;
    }

    /**
     * This method will generate a tree of the intern's possible responses.
     * @return ResponseTree of the intern's possible dialogue.
     */
    public ResponseTree<ArrayList<String>> generateInternResponses() throws FileNotFoundException {
        /*
        PSEUDO-CODE:
        ** list = [[q1, q2], [q3, q4, q5, q6], [q7, q8, q9, q10, q11, q12, q13, q14]]
        For i in range len(list): (iterating through each nested list)
	        for j in range len(sublist): (each question in the sublist)
	            **list[i][j] = each individual question
		        list[i][j].add(list[i +1][j])
                list[i][j].add(list[i+1][len(list[i]) - j - 1)
         */
        ResponseTree<ArrayList<String>> respTree = this.generateTreeRoot();
        ArrayList<ArrayList<ResponseTree<ArrayList<String>>>> nestedNodesList = getNestedNodes();

        // adding the first two questions to the root
        respTree.addChild(nestedNodesList.get(0).get(0));
        respTree.addChild(nestedNodesList.get(0).get(1));

        // adding the rest of the questions to the tree
        for (int i = 0 ; i < nestedNodesList.size() - 1 ; i ++) {
            for (int j = 0 ; j < nestedNodesList.get(i).size() ; j++) {
                nestedNodesList.get(i).get(j).addChild(nestedNodesList.get(i + 1).get(j));
                nestedNodesList.get(i).get(j).addChild(nestedNodesList.get(i + 1).get(nestedNodesList.get(i+1).size() - j - 1));
            }
        }
        return respTree;
    }

    /**
     * HELPER METHOD for generateInternResponse
     * @return
     * @throws FileNotFoundException
     */
    private ArrayList<ArrayList<ResponseTree<ArrayList<String>>>> getNestedNodes() throws FileNotFoundException {
        ArrayList<ResponseTree<ArrayList<String>>> chosenQs = chooseQuestions();
        ArrayList<ArrayList<ResponseTree<ArrayList<String>>>> nested = new ArrayList<>();
        ArrayList<ResponseTree<ArrayList<String>>> sublist1 = new ArrayList<>(chosenQs.subList(0, 2));
        ArrayList<ResponseTree<ArrayList<String>>> sublist2 = new ArrayList<>(chosenQs.subList(2, 6));
        ArrayList<ResponseTree<ArrayList<String>>> sublist3 = new ArrayList<>(chosenQs.subList(6, 14));
        nested.add(sublist1);
        nested.add(sublist2);
        nested.add(sublist3);
        return nested;
    }

    /**
     * HELPER FOR generateInternResponses
     * This method will generate a root for the Intern's ResponseTree.
     * The root of a ResponseTree is different to the nodes; the ArrayList only contains one String instead of the usual
     * [Question, Answer] format of the ResponseTree nodes.
     * @return ResponseTree containing the root.
     */
    private ResponseTree<ArrayList<String>> generateTreeRoot() {
        ArrayList<String> rootData = new ArrayList<>();
        String response = "Hello! This is my resume:\n" + this.intern.internToString();
        rootData.add(response);
        return new ResponseTree<>(rootData);
    }

    /**
     * HELPER FOR generateInternResponses
     * This method will generate an Arraylist of Strings; list of all questions
     * Will also be used to generate a list of all possible answers
     * @return an Arraylist of Strings containing each question
     */
    private ArrayList<String> generateDialogueList(String file) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(file)).useDelimiter("\n");
        ArrayList<String> infoList = new ArrayList<>();
        while (scan.hasNext()){
            infoList.add(scan.next());
        }
        scan.close();
        return infoList;
    }

    /**
     * HELPER FOR generateInternResponses
     * This method will generate an ArrayList of Arraylists of Strings; list of all possible tree data
     * @return an Arraylist in the format of [[Question1, Answer1], [Question2, Answer2], ...]
     */
    private ArrayList<ArrayList<String>> generateTreeDataList(ArrayList<String> questions, ArrayList<String> answers) {
        ArrayList<ArrayList<String>> treeData = new ArrayList<>();
        for (int i = 0; i < questions.size(); i++) {
            ArrayList<String> innerList = new ArrayList<>();
            innerList.add(questions.get(i));
            innerList.add(answers.get(i));
            treeData.add(innerList);
        }
        return treeData;
    }

    // return [node 1 = [q1, a1], node2 = [q2, a2], ...] based on the given intern's skills
    // [[],[]...[]]
    // [node1, node2, node3, ...]

    /**
     * HELPER METHOD FOR convertToNested method
     * This is a helper method for generateInternResponses. Creates an arraylist of all nodes we need for our dialogue
     * tree.
     * @return
     * @throws FileNotFoundException
     */
    private ArrayList<ResponseTree<ArrayList<String>>> chooseQuestions() throws FileNotFoundException{
        ArrayList<ResponseTree<ArrayList<String>>> output = new ArrayList<>();
        Set<String> internSkills = this.intern.getInternSkills().keySet();
        for (String skill : internSkills) {
            ArrayList<ResponseTree<ArrayList<String>>> questionList = chooseQuestionsHelper(skill);
            output.addAll(questionList);
        }
        // removing the last element since we only need 14 nodes
        output.remove(output.size() - 1);
        return output;
    }

    /**
     * HELPER METHOD FOR chooseQuestions
     * This method takes in an intern's skill and creates an arraylist of arraylists where each sublist is a question
     * and answer corresponding to an intern's skill
     * @param skill
     * @return an Arraylist in the format of [[Question1, Answer1], [Question2, Answer2], ...]
     */
    private ArrayList<ResponseTree<ArrayList<String>>> chooseQuestionsHelper(String skill) throws FileNotFoundException{
        Random randomizer = new Random();
        HashMap<String, ArrayList<ArrayList<String>>> questionMap = pairSkillToQuestion();
        ArrayList<ResponseTree<ArrayList<String>>> questionList = new ArrayList<>();
        ArrayList<ArrayList<String>> possibleQuestions = questionMap.get(skill);
        while (questionList.size() < 5) {
            ArrayList<String> randomQuestion = possibleQuestions.get(randomizer.nextInt(possibleQuestions.size()));
            ResponseTree<ArrayList<String>> node = new ResponseTree<>(randomQuestion);
            questionList.add(node);
        }
        return questionList;
    }

    /**
     * HELPER FOR generateInternResponses
     * This  method pairs each skill to a list of question and answers from the textfiles. I.e
     * corresponding_skills.txt is a text file of skills that corresponds to questions and answers in the respective
     * textfiles. It then creates a hashmap with the keys as skills and the values are a list of all questions and
     * answers that correspond to one another.
     * @return a hashmap with the format {skill1: [[q1, a1], [q2, a2], ..., [q5, a5]], ..., skill13: [[q1, a1], [q2, a2], ..., [q5, a5]]}
     * @throws FileNotFoundException
     */
    private HashMap<String, ArrayList<ArrayList<String>>> pairSkillToQuestion() throws FileNotFoundException {
        ArrayList<String> questions = generateDialogueList("Resources/questions.txt");
        ArrayList<String> answers = generateDialogueList("Resources/answers.txt");
        ArrayList<String> skillList = generateDialogueList("Resources/corresponding_skills.txt");
        ArrayList<ArrayList<String>> responseList = generateTreeDataList(questions, answers);
        HashMap<String, ArrayList<ArrayList<String>>> output = new HashMap<>();
        for (int i = 0; i < skillList.size(); i++) {
            String skill = skillList.get(i);
            ArrayList<String> response = responseList.get(i);
            if (!output.containsKey(skill)) {
                ArrayList<ArrayList<String>> outerList = new ArrayList<>();
                outerList.add(response);
                output.put(skill, outerList);
            }
            else {
                output.get(skill).add(response);
            }
        }
        return output;
    }

    /**
     * This method assigns a response tree to an intern.
     * @throws FileNotFoundException
     */
    public void assignResponseToIntern() throws FileNotFoundException {
        intern.setResponseTree(generateInternResponses());
    }
}