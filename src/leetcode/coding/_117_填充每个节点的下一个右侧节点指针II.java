package leetcode.coding;

public class _117_填充每个节点的下一个右侧节点指针II {

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

    public Node connect(Node root) {
        /**
         * 直接拿116题的bfs过了。。。一样的题
         */
        return null;
    }

}