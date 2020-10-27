package leetcode.coding;

public class _510_二叉搜索树中的中序后继II {
    /*
    分析，其实也就两种情况
    1. 如果当前节点的父亲有右子树，那么中序遍历的下一个节点是它右子树最小的那个节点（一直往左走即可）
    2. 如果当前节点没右子树
        - 如果这个节点是它父节点的左儿子，那么它中序遍历的下一个节点就是它的父亲
        - 如果这个节点是他父亲的右儿子，那么它中序遍历的下一个节点就是一直往上找，直到找到一个节点p，这个p是p父亲的左儿子
        上面这两种情况其实可以合并
     */

    public Node inorderSuccessor(Node node) {
        if (node == null) return node;
        if (node.right != null) {
            node = node.right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }
        // 一直往上找，有父亲且这个节点是他父亲的右儿子
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        return node.parent;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    };

}