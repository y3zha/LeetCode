package leetcode.coding;

public class _437_路径总和III {

    //解决了以根节点开始的所有路径后，就要找以根节点的左孩子和右孩子开始的所有路径，三个节点构成了一个递归结构；
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        return dfs(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    //此时的root必然作为中继节点的
    private int dfs(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        int res = 0;
        if (root.val == sum) {
            res += 1;
        }
        res += dfs(root.left, sum - root.val);
        res += dfs(root.right, sum - root.val); //看一下494题，暴力法
        return res;
    }
}