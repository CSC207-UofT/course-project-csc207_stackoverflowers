package UseCases;

import java.util.ArrayList;

public class ResponseTree<T> {
    /*
    A ResponseTree stores all the questions the player can ask and the Intern's respective responses during an interview.
    The data is stored in an ArrayList of strings, formatted as [Question, Answer].
     */

    public ArrayList<String> data;
    public ResponseTree<T> parent;
    public ArrayList<ResponseTree<T>> children;

    public ResponseTree(ArrayList<String> data) {
        this.data = data;
        this.children = new ArrayList<>();
    }

    public boolean isRoot() {
        return parent == null;
    }

    public boolean isLeaf() { return children.size() == 0; }

    public void addChild(ResponseTree<T> child) {
        child.parent = this;
        this.children.add(child);
    }

    public ArrayList<String> getData() {
        return this.data;
    }

    public ArrayList<ResponseTree<T>> getChildren() {
        return this.children;
        // hi
    }

    public int getHeight() {
        if (this.isRoot())
            return 0;
        else
            return parent.getHeight() + 1;
    }
}