package leetcode.coding;

import lintcode.算法基础班._05_dfs.TreeNode;

/**
 * 路径总和 dfs
 */
public class _112_路径总和 {

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        sum -= root.val;
        //递归终止条件
        if (root.left == null && root.right == null) {
            return sum == 0;
        }
        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    }

}