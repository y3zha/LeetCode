package leetcode.coding;

import lintcode.算法基础班._05_dfs.TreeNode;

/**
 * 思路一，如果是对称二叉树 前序遍历结果=后序遍历结果反过来
 * 思路二：leetcode的官方题解的思路特别好，如果是对称的，那么就像站在镜子前，你的左手=镜子里的右手
 */
public class _101_对称二叉树 {

    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    private boolean isMirror(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null) {
            return false;
        }
        return node1.val == node2.val && isMirror(node1.left, node2.right) && isMirror(node1.right, node2.left);
    }


}