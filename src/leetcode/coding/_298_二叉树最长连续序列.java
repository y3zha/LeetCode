package leetcode.coding;

public class _298_二叉树最长连续序列 {


    /**
     * 就是统计以某个节点为root和子树中最长链的信息，结果就是子树最长链 + root
     * 还要保证链要连续
     */
    int ans = 0;
    public int longestConsecutive(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        int tmp = 1;
        if (root.left != null && root.left.val == root.val + 1) {
            tmp = Math.max(tmp, left + 1);
        }
        if (root.right != null && root.right.val == root.val + 1) {
            tmp = Math.max(tmp, right + 1);
        }
        // 全局更新 ans
        ans = Math.max(ans, tmp);
        return tmp;
    }


}