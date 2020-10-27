package leetcode.coding;

public class _617_合并二叉树 {

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        TreeNode left = mergeTrees(t1.left, t2.left);
        TreeNode right = mergeTrees(t1.right, t2.right);
        t1.val = t1.val + t2.val;
        t1.left = left;
        t1.right = right;
        return t1;
    }

}