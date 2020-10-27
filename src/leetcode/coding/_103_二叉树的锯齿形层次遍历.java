package leetcode.coding;

import lintcode.算法基础班._05_dfs.TreeNode;

import java.util.*;

/**
 * bfs
 */
public class _103_二叉树的锯齿形层次遍历    {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;      //奇数曾从左到右，偶数层从右到左
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            //如果是偶数层，则要反转下
            if ((level & 1) == 0) {
                Collections.reverse(list);
            }
            res.add(list);
        }
        return res;
    }
}