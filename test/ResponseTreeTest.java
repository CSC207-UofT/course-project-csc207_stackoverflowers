import UseCases.ResponseTree;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

import static org.junit.Assert.assertEquals;

public class ResponseTreeTest {

    @Test(timeout = 1000)
    public void TestIsRoot() {
        ArrayList<String> data = new ArrayList<>();
        data.add("Hello");
        ResponseTree<ArrayList<String>> tree = new ResponseTree<>(data);
        assertTrue(tree.isRoot());
    }

    @Test(timeout = 1000)
    public void TestIsLeaf() {
        ArrayList<String> data1 = new ArrayList<>();
        data1.add("Hello");
        ResponseTree<ArrayList<String>> tree1 = new ResponseTree<>(data1);
        ArrayList<String> data2 = new ArrayList<>();
        data2.add("Hi");
        ResponseTree<ArrayList<String>> tree2 = new ResponseTree<>(data2);
        tree1.addChild(tree2);
        assertTrue(tree2.isLeaf());
        assertFalse(tree1.isLeaf());
    }

    @Test(timeout = 1000)
    public void TestGetData() {
        ArrayList<String> data = new ArrayList<>();
        data.add("Hello");
        ResponseTree<ArrayList<String>> tree = new ResponseTree<>(data);
        assertEquals(data, tree.getData());
    }

    @Test(timeout = 1000)
    public void TestAddChild() {
        ArrayList<String> data1 = new ArrayList<>();
        data1.add("Hello");
        ResponseTree<ArrayList<String>> tree1 = new ResponseTree<>(data1);
        ArrayList<String> data2 = new ArrayList<>();
        data2.add("Hi");
        ResponseTree<ArrayList<String>> tree2 = new ResponseTree<>(data2);
        tree1.addChild(tree2);
        assertTrue(tree1.getChildren().contains(tree2));
    }

    @Test(timeout = 1000)
    public void TestGetHeight() {
        ArrayList<String> data1 = new ArrayList<>();
        data1.add("Hello");
        ResponseTree<ArrayList<String>> tree1 = new ResponseTree<>(data1);
        ArrayList<String> data2 = new ArrayList<>();
        data2.add("Hi");
        ResponseTree<ArrayList<String>> tree2 = new ResponseTree<>(data2);
        tree1.addChild(tree2);
        assertEquals(2, tree1.getHeight());
    }
}
