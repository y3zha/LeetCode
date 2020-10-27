package leetcode.coding;

public class _270_最接近的二叉搜索树值 {

    // 利用性质求解
    // 如果 target > node.val 舍弃node的左子树
    // 如果 target < node.val 舍弃node的右子树
    int res;
    double min;
    public int closestValue(TreeNode root, double target) {
        res = 0;
        min = Double.MAX_VALUE;
        helper(root, target);
        return res;
    }

    private void helper(TreeNode root, double target) {
        if (root == null) {
            return;
        }
        if (target > root.val) {
            double temp = Math.abs(target - root.val);
            if (temp < min) {
                min = temp;
                res = root.val;
            }
            helper(root.right, target);
        } else if (target < root.val) {
            double temp = Math.abs(target - root.val);
            if (temp < min) {
                min = temp;
                res = root.val;
            }
            helper(root.left, target);
        } else {
            res = root.val;
        }
    }
}