package leetcode.coding;

import java.util.HashMap;
import java.util.Map;

/**
 * easy bfs
 *
 * 题目中：每个节点的值都是唯一的、范围为 1 到 100 的整数。
 *
 * 思路：需要用两个map，一个map记录当前值的深度，另一个map保存当前值的父亲
 */
public class _993_二叉树的堂兄弟节点 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    Map<Integer, Integer> depthMap;
    Map<Integer, TreeNode> parentMap;

    public boolean isCousins(TreeNode root, int x, int y) {
        depthMap = new HashMap<>();
        parentMap = new HashMap<>();
        dfs(root, null);

        return depthMap.get(x) == depthMap.get(y) && parentMap.get(x).val != parentMap.get(y).val;
    }

    private void dfs(TreeNode node, TreeNode parent) {
        if (node == null) {
            return;
        }
        depthMap.put(node.val, parent == null ? 0 : depthMap.get(parent.val) + 1);
        parentMap.put(node.val, parent);
        dfs(node.left, node);
        dfs(node.right, node);
    }
}