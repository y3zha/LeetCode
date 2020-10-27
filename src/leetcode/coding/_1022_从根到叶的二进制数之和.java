package leetcode.coding;

public class _1022_从根到叶的二进制数之和 {

    // dfs
    int mod = (int) (1e9 + 7);

    public int sumRootToLeaf(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int val) {
        if (root == null) {
            return 0;
        }
        val = (val * 2 + root.val) % mod;
        if (root.left == null && root.right == null) {
            return val;
        }
        return (dfs(root.left, val) + dfs(root.right, val)) % mod;
    }
}