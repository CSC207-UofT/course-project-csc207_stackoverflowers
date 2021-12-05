package UseCases;

import Entities.InterviewIntern;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ResponseTreeMaker {
    /* This class is responsible for generating and assigning a response tree to an Intern.
    It generates a unique ResponseTree based on the Intern's skills.
     */

    private final InterviewIntern intern;

    public ResponseTreeMaker(InterviewIntern intern) {
        this.intern = intern;
    }

    /**
     * This method assigns a response tree to an intern.
     */
    public void assignResponseToIntern() throws FileNotFoundException {
        intern.setResponseTree(generateInternResponses());
    }

    /**
     * This method will generate a unique tree of the intern's possible responses based on their skills.
     *
     * @return ResponseTree of the intern's possible dialogue.
     */
    public ResponseTree<ArrayList<String>> generateInternResponses() throws FileNotFoundException {
        // creating the root
        ResponseTree<ArrayList<String>> respTree = this.generateTreeRoot();

        // getting a nested list of the chosen nodes for the intern, formatted as:
        // [[n1, n2], [n3, n4, n5, n6], [n7, n8, n9, n10, n11, n12, n13, n14]]
        // where [n1, n2] = first options, [n3,...,n6] = second options, [n7, ... , n14] = third options
        ArrayList<ArrayList<ResponseTree<ArrayList<String>>>> nestedNodesList = getNestedNodes();

        // adding the first two questions to the root
        respTree.addChild(nestedNodesList.get(0).get(0));
        respTree.addChild(nestedNodesList.get(0).get(1));

        // adding the rest of the questions to the tree
        for (int i = 0 ; i < nestedNodesList.size() - 1 ; i ++) {   // accessing each nested list
            for (int j = 0 ; j < nestedNodesList.get(i).size() ; j++) {    // accessing each node
                nestedNodesList.get(i).get(j).addChild(nestedNodesList.get(i + 1).get(j));
                nestedNodesList.get(i).get(j).addChild(nestedNodesList.get(i + 1).get(nestedNodesList.get(i+1).size() - j - 1));
            }
        }
        return respTree;
    }


    // ==================== BELOW ARE ALL THE HELPER METHODS FOR generateInternResponses ====================


    /**
     * HELPER FOR generateInternResponses
     * This method will generate a root for the Intern's ResponseTree.
     * The root of a ResponseTree is different to the nodes; the ArrayList only contains one String [Intro] instead of
     * the usual [Question, Answer] format of the ResponseTree nodes.
     *
     * @return a ResponseTree containing the root.
     */
    private ResponseTree<ArrayList<String>> generateTreeRoot() {
        ArrayList<String> rootData = new ArrayList<>();
        String response = "Hello! This is my resume:\n" + this.intern.internToString();
        rootData.add(response);
        return new ResponseTree<>(rootData);
    }

    /**
     * HELPER FOR generateInternResponses
     * This method will generate an Arraylist of Strings from the given text file
     * This will be used to generate a list of all possible questions, answers, and its corresponding skill
     * (see method pairSkillToResponse)
     *
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
     * This method will pair the given list of questions to the given list of answers.
     * (see method pairSkillToResponse)
     *
     * @return an Arraylist in the format of [[Question1, Answer1], [Question2, Answer2], ...]
     */
    private ArrayList<ArrayList<String>> pairQuestionToAnswer(ArrayList<String> questions, ArrayList<String> answers) {
        ArrayList<ArrayList<String>> treeData = new ArrayList<>();
        for (int i = 0; i < questions.size(); i++) {
            ArrayList<String> innerList = new ArrayList<>();
            innerList.add(questions.get(i));
            innerList.add(answers.get(i));
            treeData.add(innerList);
        }
        return treeData;
    }

    /**
     * HELPER FOR generateInternResponses
     * This method creates a hashmap that pairs all skills to all of its corresponding questions and answers
     * from the text files.
     * Each line in corresponding_skills.txt, questions.txt and answers.txt correspond to each other; the question and
     * answer are related to the given skill.
     * This method then creates a hashmap where the keys are skills and the values are a nested ArrayList of all
     * questions and answers that correspond to that skill.
     * This map contains ALL the possible questions and answers (not just the responses  for the intern's tree).
     *
     * @return a hashmap with the format {skill1: [[q1, a1], [q2, a2], ...], skill2: [[q6, a6], ...], ...}
     */
    private HashMap<String, ArrayList<ArrayList<String>>> pairSkillToResponses() throws FileNotFoundException {
        // creating the list of questions, answers, and their corresponding skills
        ArrayList<String> questions = generateDialogueList("Resources/questions.txt");
        ArrayList<String> answers = generateDialogueList("Resources/answers.txt");
        ArrayList<String> skillList = generateDialogueList("Resources/corresponding_skills.txt");

        // pairing the questions and answers together
        ArrayList<ArrayList<String>> responseList = pairQuestionToAnswer(questions, answers);

        // now pairing the skills with the respective questions and answers
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
     * HELPER METHOD FOR generateInternResponses; used in getNestedNodes method
     * This is a helper method for generateInternResponses. It chooses the questions based on the intern's skills and
     * creates an ArrayList of all nodes we need for our ResponseTree.
     *
     * @return an ArrayList of all the nodes we need for our tree.
     */
    private ArrayList<ResponseTree<ArrayList<String>>> chooseResponses() throws FileNotFoundException{
        ArrayList<ResponseTree<ArrayList<String>>> chosenResponses = new ArrayList<>();

        // getting the set of the Intern's skills
        Set<String> internSkills = this.intern.getInternSkills().keySet();

        // randomly choosing the questions based on the intern's skills; check chooseResponsesHelper
        for (String skill : internSkills) {
            ArrayList<ResponseTree<ArrayList<String>>> questionList = chooseResponsesHelper(skill);
            chosenResponses.addAll(questionList);
        }

        // removing the last element since we only need 14 nodes
        chosenResponses.remove(chosenResponses.size() - 1);
        return chosenResponses;
    }

    /**
     * HELPER METHOD FOR chooseResponses.
     * This method takes in an intern's skill and chooses random questions and answers that pertain to the given skill.
     * It creates an arraylist of ResponseTree nodes that contain the chosen questions/answers.
     *
     * @param skill this is the intern's skill
     * @return an Arraylist in the format of [node1, node2, ...] where all the nodes contain responses which pertain to
     * the given skill.
     */
    private ArrayList<ResponseTree<ArrayList<String>>> chooseResponsesHelper(String skill) throws FileNotFoundException{
        Random randomizer = new Random();

        // getting the map of all skills with all its corresponding responses
        HashMap<String, ArrayList<ArrayList<String>>> questionMap = pairSkillToResponses();

        ArrayList<ResponseTree<ArrayList<String>>> questionList = new ArrayList<>();

        // getting the array of questions that correspond to the given skill
        ArrayList<ArrayList<String>> possibleQuestions = questionMap.get(skill);

        // choosing random questions
        while (questionList.size() < 5) {
            ArrayList<String> randomQuestion = possibleQuestions.remove(randomizer.nextInt(possibleQuestions.size()));
            ResponseTree<ArrayList<String>> node = new ResponseTree<>(randomQuestion);
            questionList.add(node);
        }
        return questionList;
    }

    /**
     * HELPER METHOD for generateInternResponse
     * Creates a nested list of nodes, where each sublist corresponds to the depth in our tree and so it is ordered
     * For example the first sublist will be the list = [child1, child2] where child1 and child2 are the first options
     * in the interview and the second sublist will be the list = [child3, child4...] where child3, child4, ...
     * are the second options in the interview and so on.
     *
     * @return An arraylist of arraylists of nodes which contain the Intern's responses
     */
    private ArrayList<ArrayList<ResponseTree<ArrayList<String>>>> getNestedNodes() throws FileNotFoundException {
        // getting the list of chosen responses
        ArrayList<ResponseTree<ArrayList<String>>> chosenResponses = chooseResponses();
        ArrayList<ArrayList<ResponseTree<ArrayList<String>>>> nested = new ArrayList<>();

        // creating the inner lists
        ArrayList<ResponseTree<ArrayList<String>>> sublist1 = new ArrayList<>(chosenResponses.subList(0, 2));
        ArrayList<ResponseTree<ArrayList<String>>> sublist2 = new ArrayList<>(chosenResponses.subList(2, 6));
        ArrayList<ResponseTree<ArrayList<String>>> sublist3 = new ArrayList<>(chosenResponses.subList(6, 14));

        // adding inner lists to the array
        nested.add(sublist1);
        nested.add(sublist2);
        nested.add(sublist3);

        return nested;
    }
}