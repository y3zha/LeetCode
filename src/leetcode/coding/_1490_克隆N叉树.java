package leetcode.coding;

import java.util.ArrayList;
import java.util.List;

public class _1490_克隆N叉树 {

    private class Node{
        public int val;

        private List<Node> children;
        public Node() {
            children = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            children = new ArrayList<Node>();
        }

        public Node(int _val,ArrayList<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public Node cloneTree(Node root) {
        return dfs(root);
    }

    private Node dfs(Node root) {
        if (root == null) {
            return null;
        }
        Node clone = new Node(root.val);

        for (Node child : root.children) {
            clone.children.add(dfs(child));
        }
        return clone;
    }
}