package leetcode.coding;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给你一个二叉树的根节点 root。设根节点位于二叉树的第 1 层，而根节点的子节点位于第 2 层，依此类推。
 *
 * 请你找出层内元素之和 最大 的那几层（可能只有一层，也可能有多层）的层号，如果有多层，返回其中 层号最小 的那个。
 *
 */
public class _1161_最大层内元素和 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int maxLevelSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int res = 0;
        int level = 0;
        int maxLevelSum = 0;
        while (!queue.isEmpty()) {
            level++;
            int size = queue.size();
            int curLevelSum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                curLevelSum += node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            if (curLevelSum > maxLevelSum) {
                maxLevelSum = curLevelSum;
                res = level;
            }
        }
        return res;
    }

}