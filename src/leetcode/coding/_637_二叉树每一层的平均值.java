package leetcode.coding;

import lintcode.算法基础班._05_dfs.TreeNode;

import java.util.*;

/**
 * 求二叉树每一层的平均值，，，，和102、107一样的题
 */
public class _637_二叉树每一层的平均值 {

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            double size = queue.size();     //注意是double
            double sum = 0;         //注意是double
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            res.add(sum / size);
        }
        return res;
    }


}