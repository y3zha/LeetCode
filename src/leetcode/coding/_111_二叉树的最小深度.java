package leetcode.coding;

import lintcode.算法基础班._05_dfs.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的最小深度 ==> 树的根节点到叶子节点的最小路径长度,bfs
 * 有一种情况当root根节点没有左子树或者右子树的情况，需要只考虑对应有的左子树或右子树计算深度，只需考虑一种
 *     3
 *      \
 *      20
 *     /  \
 *    15   7
 *
 *    这种情况考虑root的左右子树两边情况。
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class _111_二叉树的最小深度 {

    //非dfs，但是写法非常简单
    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //计算左右子树的最小高度
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        //只要有一边到达了叶子节点，那就立即返回
        if (left == 0 || right == 0) {
            return left + right + 1;
        }
        return Math.min(left, right) + 1;
    }

    //自己写的bfs
    public static int minDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int depth = 0;
        while (!queue.isEmpty()) {
            //遍历这一层
            depth++;     //不能在这加，得遍历完这一层再加
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                // 每次对同一深度的节点一起处理，判断其中是否有叶子节点，有则返回当前层的深度。
                if (node.left == null && node.right == null) {      //如果有叶子节点（左右都为空）
                    return depth;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        root.right = node1;

        int i = minDepth2(root);
        System.out.println(i);

    }
}