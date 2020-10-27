package leetcode.coding;

public class _426_将二叉搜索树转化为排序的双向链表 {

    // 利用中序遍历即可

    Node pre = null;
    Node head = null;
    Node tail = null;

    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return root;
        }
        helper(root);
        head.left = tail;
        tail.right = head;
        return head;
    }

    private void helper(Node node) {
        if (node == null) {
            return;
        }
        helper(node.left);
        // 处理当前节点
        if (pre == null) {
            // 如果没有pre，说明是head
            head = node;
            node.left = pre;
        } else {
            // 否则就是中间过程
            pre.right = node;
            // 其实这句话可以和上面一起提出去
            node.left = pre;
        }
        // 往后走
        pre = node;
        tail = node;
        helper(node.right);
    }


    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    };
}