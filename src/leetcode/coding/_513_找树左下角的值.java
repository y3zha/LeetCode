package leetcode.coding;

import lintcode.算法基础班._05_dfs.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * bfs 广度优先遍历保存每层第一个节点
 * 您可以假设树（即给定的根节点）不为 NULL。
 */
public class _513_找树左下角的值 {

    public int findBottomLeftValue(TreeNode root) {
        int res = -1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (i == 0) {
                    res = node.val;
                }
            }
        }
        return res;
    }
}