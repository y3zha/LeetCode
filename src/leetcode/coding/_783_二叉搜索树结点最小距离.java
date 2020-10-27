package leetcode.coding;

public class _783_二叉搜索树结点最小距离 {

    // 中序遍历即可
    int min;
    TreeNode pre;
    public int minDiffInBST(TreeNode root) {
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