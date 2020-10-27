package leetcode.coding;

public class _530_二叉搜索树的最小绝对差 {

    // 中序遍历即可,与783题一样
    int min;
    TreeNode pre;
    public int getMinimumDifference(TreeNode root) {
        min = Integer.MAX_VALUE;
        helper(root);
        return min;
    }

    private void helper(TreeNode root) {
        if (root == null) {
            return;
        }
        helper(root.left);
        if (pre == null) {
            pre = root;
        } else {
            min = Math.min(min, root.val - pre.val);
            pre = root;
        }
        helper(root.right);
    }
}