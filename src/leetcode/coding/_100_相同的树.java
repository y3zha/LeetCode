package leetcode.coding;

import lintcode.算法基础班._05_dfs.TreeNode;

/**
 * 相同的树
 */
public class _100_相同的树 {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val == q.val) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
        return false;
    }
}