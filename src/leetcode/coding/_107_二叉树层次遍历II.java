package leetcode.coding;

import lintcode.算法基础班._05_dfs.TreeNode;

import java.util.*;

/**
 * 二叉树层次遍历2，返回自底向上的层次,（直接把102题的res，reverse一下）
 */
public class _107_二叉树层次遍历II {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
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
            res.add(list);
        }
        Collections.reverse(res);
        return res;
    }


}