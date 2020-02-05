package leetcode.coding;

import java.util.LinkedList;
import java.util.Queue;

public class _116_填充每个节点的下一个右侧节点指针 {

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    //bfs层次遍历
    public Node connect_bfs(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node pre = null;
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                if (i == 0) {   //如果是第一个节点
                    pre = cur;
                } else if (i == size - 1) { //如果是最后一个节点，需要将它后面置为空
                    pre.next = cur;
                    cur.next = null;
                } else {    //中间节点
                    pre.next = cur;
                    pre = cur;
                }
            }
        }
        return root;
    }

    //递归写法
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        if (root.left != null) {
            root.left.next = root.right;
            if (root.next != null) {
                root.right.next = root.next.left;
            }
        }
        connect(root.left);
        connect(root.right);
        return root;
    }
}