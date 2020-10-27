package leetcode.swordtooffer;

public class 面试题36_二叉搜索树与双向链表 {

    /**
     * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
     *
     * 思路
     *      中序遍历本身就是升序的，所以我们一开始就能得到 1 2 3 4 5这样的升序序列，需要保存头尾指针
     */

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
    }

    /**
     * 思路，中序遍历来改变指向即可，需要全局保存一个头尾指针连接
     */
    Node pre = null,head = null, tail = null;
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        //中序遍历修改中间的
        inorderTraversal(root);
        //中间改完改头和尾
        tail.right = head;
        head.left = tail;
        return head;
    }

    private void inorderTraversal(Node node) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left);
        if (pre == null) {
            head = node;
        } else {
            pre.right = node;
        }
        node.left = pre;
        pre = node;
        tail = node;
        inorderTraversal(node.right);
    }

}