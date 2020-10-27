package leetcode.coding;

import java.util.Random;

public class _404_左叶子之和 {

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int lSum = sumOfLeftLeaves(root.left);
        int rSum = sumOfLeftLeaves(root.right);
        if (root.left != null && root.left.left == null && root.left.right == null) {
            return root.left.val + lSum + rSum;
        } else {
            return lSum + rSum;
        }
    }

    public static void main(String[] args) {
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(rand.nextInt(9999) % 3);
        }
    }
}