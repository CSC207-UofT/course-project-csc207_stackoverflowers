import UseCases.ResponseTree;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ResponseTreeTest {

    @Test
    public void TestIsRoot() {
        ArrayList<String> data = new ArrayList<>();
        data.add("Hello");
        ResponseTree<ArrayList<String>> tree = new ResponseTree<>(data);
        assertTrue(tree.isRoot());
    }

    @Test
    public void TestIsRootFalse() {
        ArrayList<String> data1 = new ArrayList<>();
        data1.add("Hello");
        ResponseTree<ArrayList<String>> tree1 = new ResponseTree<>(data1);
        ArrayList<String> data2 = new ArrayList<>();
        data2.add("Hi");
        ResponseTree<ArrayList<String>> tree2 = new ResponseTree<>(data2);
        tree1.addChild(tree2);
        assertFalse(tree2.isRoot());
    }

    @Test
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

    @Test
    public void TestGetData() {
        ArrayList<String> data = new ArrayList<>();
        data.add("Hello");
        ResponseTree<ArrayList<String>> tree = new ResponseTree<>(data);
        assertEquals(data, tree.getData());
    }

    @Test
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

    @Test
    public void TestGetSize() {
        ArrayList<String> data1 = new ArrayList<>();
        data1.add("Hello");
        ResponseTree<ArrayList<String>> tree1 = new ResponseTree<>(data1);
        ArrayList<String> data2 = new ArrayList<>();
        data2.add("Hi");
        ResponseTree<ArrayList<String>> tree2 = new ResponseTree<>(data2);
        ArrayList<String> data3 = new ArrayList<>();
        data3.add("Hey");
        ResponseTree<ArrayList<String>> tree3 = new ResponseTree<>(data3);
        tree1.addChild(tree2);
        tree1.addChild(tree3);
        assertEquals(3, tree1.getSize());
    }
}