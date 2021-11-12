package UseCases;

import Entities.InterviewIntern;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ResponseTreeMaker {
    /* This ResponseTreeMaker class is responsible for generating and assigning a response tree
    to an Intern.
     */

    private final InterviewIntern intern;

    public ResponseTreeMaker(InterviewIntern intern) {
        this.intern = intern;
    }

    // TODO: Change this method so that nodes are not constructed manually
    /**
     * This method will generate a tree of the intern's possible responses.
     * @return ResponseTree of the intern's possible dialogue.
     */
    public ResponseTree<ArrayList<String>> generateInternResponses() throws FileNotFoundException {
        ArrayList<String> questions = generateDialogueList("treeConvoTextFiles/questions.txt");
        ArrayList<String> answers = generateDialogueList("treeConvoTextFiles/answers.txt");
        ArrayList<ArrayList<String>> dataList = generateTreeDataList(questions, answers);

        //constructing tree nodes manually; will change to a loop/recursion for phase 2
        ResponseTree<ArrayList<String>> respTree = this.generateTreeRoot();
        ResponseTree<ArrayList<String>> node1 = new ResponseTree<>(dataList.get(0));
        ResponseTree<ArrayList<String>> node2 = new ResponseTree<>(dataList.get(1));
        ResponseTree<ArrayList<String>> node3 = new ResponseTree<>(dataList.get(2));
        ResponseTree<ArrayList<String>> node4 = new ResponseTree<>(dataList.get(3));
        ResponseTree<ArrayList<String>> node5 = new ResponseTree<>(dataList.get(4));
        ResponseTree<ArrayList<String>> node6 = new ResponseTree<>(dataList.get(5));
        // adding first two nodes to root

        // making smaller trees
        node3.addChild(node5);
        node3.addChild(node6);
        node4.addChild(node5);
        node4.addChild(node6);

        // adding smaller trees to node1 and node 2
        node1.addChild(node3);
        node1.addChild(node4);

        node2.addChild(node3);
        node2.addChild(node4);

        // adding first two nodes to root
        respTree.addChild(node1);
        respTree.addChild(node2);

        // returning the tree
        return respTree;
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
        String response = "Hello! My name is " + this.intern.getInternName() + ", I am "
                + this.intern.getInternName() + " years old and my skills include [skill1], [skill2] and skill[3].";
        StringBuilder skills = new StringBuilder();
        for (String skill : this.intern.getInternSkills().keySet()) {
            skills.append(skill);
            skills.append(", ");
        }
        String finalResponse = response + skills;
        rootData.add(finalResponse);
        return new ResponseTree<>(rootData);
    }

    /**
     * HELPER FOR generateInternResponses
     * This method will generate an Arraylist of Strings; list of all questions
     * Will also be used to generate a list of all possible answers
     * @return an Arraylist of Strings containing each question
     */
    private ArrayList<String> generateDialogueList(String file) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(file));
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

    /**
     * This method will assign a ResponseTree to an intern.
     */
    public void assignResponseToIntern(ResponseTree<ArrayList<String>> resTree) {
        this.intern.setResponseTree(resTree);
    }
}