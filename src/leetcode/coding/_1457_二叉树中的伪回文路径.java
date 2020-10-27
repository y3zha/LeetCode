package leetcode.coding;

public class _1457_二叉树中的伪回文路径 {

    int res;

    // 注意范围，看到奇偶肯定要想到用异或的
    // 回文串只会有两种情况，要么出现次数全是偶数，异或结果为0，要么212或者121，也就是剩一个个数为奇数的数字，也就是n &(n-1)为0
    public int pseudoPalindromicPaths(TreeNode root) {
        res = 0;
        int temp = 0;
        dfs(root, temp);
        return res;
    }

    private void dfs(TreeNode root, int n) {
        if (root == null) {
            return;
        }
        n ^= (1 << root.val);
        if (root.left == null && root.right == null) {
            if (n == 0 || (n & (n - 1)) == 0) {
                res++;
            }
        }
        dfs(root.left, n);
        dfs(root.right, n);
    }
}