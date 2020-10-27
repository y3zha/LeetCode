package leetcode.coding;

public class _1339_分裂二叉树的最大乘积 {

    static final int MOD = (int) (10e8 + 7);
    long sum;
    long res;

    public int maxProduct(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 求整棵子树的和
        sum = dfs(root);
        // System.out.println(sum);
        res = 0;
        // 再次调用，更新res
        dfs(root);
        return (int) (res % MOD);
    }

    private long dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        long left = dfs(root.left);
        long right = dfs(root.right);
        // System.out.println(left + " " + right);
        res = Math.max(res, Math.max(left * (sum - left), right * (sum - right)));
        // System.out.println(res);
        return root.val + left + right;
    }
}