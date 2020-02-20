package leetcode.interviewbook;

public class 面试题04_10_检查子树 {

    public boolean checkSubTree(TreeNode t1, TreeNode t2) {
        if (t1 == null || t2 == null) {
            return false;
        }
        //先判断这个节点，如果不对，判断左边有没有这棵子树，如果还不对，判断右子树有没有这棵子树
        return isSub(t1, t2) || checkSubTree(t1.left, t2) || checkSubTree(t1.right, t2);
    }

    private boolean isSub(TreeNode node1, TreeNode node2) {
        //node2为null，node1可以有，也可以没有，node1为null，node2必须为null
        if (node1 == null) {
            return node2 == null;
        }
        //如果值不同，必定不是啊
        if (node1.val != node2.val) {
            return false;
        }
        //如果值相同，继续看
        return isSub(node1.left, node2.left) && isSub(node1.right, node2.right);
    }
}