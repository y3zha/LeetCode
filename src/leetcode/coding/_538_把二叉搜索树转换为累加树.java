package leetcode.coding;

import lintcode.算法基础班._05_dfs.TreeNode;

public class _538_把二叉搜索树转换为累加树 {

    int sum;

    public TreeNode convertBST(TreeNode root) {
        sum = 0;
        dfs(root);
        return root;
    }

    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        //右根左
        dfs(node.right);
        //修改根节点值
        sum += node.val;
        node.val = sum;
        dfs(node.left);
    }
}