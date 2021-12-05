package UseCases;

import java.io.Serializable;
import java.util.ArrayList;

public class ResponseTree<T> implements Serializable {
    /*
    A ResponseTree stores all the questions the player can ask and the Intern's respective answers during an interview.
    The data is stored in an ArrayList of strings, formatted as [Question, Answer].
    * NOTE: the root is formatted differently; the ArrayList only contains one string [Intro].

    Instance Attributes:
    - data: an ArrayList of strings containing the [Question, Answer] or the [Intro] if it's the root.
    - parent: the parent of this response tree
    - children: an ArrayList containing the children

    SAMPLE TREE STRUCTURE:

                  [Intro]                  <- Root
               /           \
        [Q1, A1]           [Q2, A2]        <- Options for Question 1
         /    \             /     \
    [Q1, A1] [Q2, A2]   [Q1, A1] [Q2, A2]   <- Options for Question 2
     */

    public ArrayList<String> data;
    public ResponseTree<T> parent;
    public ArrayList<ResponseTree<T>> children;

    /**
     * The constructor for a ResponseTree
     *
     * @param data an ArrayList containing a question and the Intern's corresponding answer
     */
    public ResponseTree(ArrayList<String> data) {
        this.data = data;
        this.children = new ArrayList<>();
    }

    /**
     * Checks if this ResponseTree is a root
     *
     * @return true if ResponseTree is a root
     */
    public boolean isRoot() {
        return parent == null;
    }

    /**
     * Checks if ResponseTree is a leaf
     *
     * @return true if ResponseTree is a leaf
     */
    public boolean isLeaf() { return children.size() == 0; }

    /**
     * Adds a child to this ResponseTree
     *
     * @param child the ResponseTree that will be added as a child
     */
    public void addChild(ResponseTree<T> child) {
        child.parent = this;
        this.children.add(child);
    }

    /**
     * Returns the data in a ResponseTree (a specific node; not the whole tree)
     *
     * @return an ArrayList containing the question and answer.
     */
    public ArrayList<String> getData() {
        return this.data;
    }

    /**
     * Returns an ArrayList of the children of this ResponseTree
     *
     * @return an Arraylist of ResponseTrees (the children)
     */
    public ArrayList<ResponseTree<T>> getChildren() {
        return this.children;
    }

    /**
     * Returns the number of nodes in this ResponseTree
     *
     * @return an int which is the number of nodes in this ResponseTree
     */
    public int getSize() {
        int num = 1;  //count the root
        for (ResponseTree<T> child : this.children) {
            num += child.getSize(); // gets num of children from each child
        }
        return num;
    }

}