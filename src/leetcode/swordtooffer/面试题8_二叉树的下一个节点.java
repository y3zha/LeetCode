package leetcode.swordtooffer;

public class 面试题8_二叉树的下一个节点 {

    class TreeLinkNode{
        int val;
        TreeLinkNode left;
        TreeLinkNode right;
        TreeLinkNode next;

        public TreeLinkNode(int val) {
            this.val = val;
        }
    }

    public TreeLinkNode getNext(TreeLinkNode node) {
        if (node == null) return null;
        // 如果有右子树
        if (node.right != null) {
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }
        // 没有右子树，如果有父亲，并且它是他父亲的右儿子，就一直走，直到走到它是他父亲的左儿子
        while (node.next != null && node == node.next.right) {
            node = node.next;
        }
        return node.next;
    }
}