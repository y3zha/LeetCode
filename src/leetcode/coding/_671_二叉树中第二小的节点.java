package leetcode.coding;

import java.util.*;

/**
 * 如果不存在第二小的节点，返回-1
 */
public class _671_二叉树中第二小的节点 {

    class TreeNode{
        int val;
        TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    int first;  //第一小
    int second; //第二小
    Set<Integer> set;  //统计二叉树一共有多少种不同的数字，如果只有一种数字的话，return -1

    public int findSecondMinimumValue(TreeNode root) {
        first = Integer.MAX_VALUE;
        second = Integer.MAX_VALUE;
        set = new HashSet<>();
        helper(root);
        return set.size() > 1 ? second : -1;
    }

    private void helper(TreeNode root) {
        if (root == null) {
            return;
        }
        //如果比最小值小，把当前最小值给次小值，然后更新最小值。如果只是比次小值小，只要更新次小值即可
        if (root.val < first) {
            second = first;
            first = root.val;
            set.add(root.val);
        } else if (root.val > first && root.val <= second) {    //小于等于，他有个用例是[2,2,2147483647]

            second = root.val;
            set.add(root.val);
        }
        helper(root.left);
        helper(root.right);
    }
}