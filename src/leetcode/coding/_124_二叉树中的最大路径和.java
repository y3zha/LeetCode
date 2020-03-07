package leetcode.coding;

/**
 * 还是powcai哥的题解最好！
 * https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-30/
 *
 * 这个题本质上就是树的高度的延伸，我们先求左子树的结果，再求右子树的结果，然后更新这个根的结果即可
 * 全局用一个res来记录最大值！
 *
 *       8
 *     /  \
 *    -3   7
 *  /    \
 * 1      4
 *  \    / \
 *   3  2   6
 *
 * 怎么考虑【包含】根节点的最大路径等于多少？
 * 考虑左子树 -3 的路径的时候，我们有左子树 1 和右子树 4 的选择，但我们不能同时选择
 * 如果同时选了，路径就是 ... -> 1 -> -3 -> 4 -> ... 就无法通过根节点 8 了
 * 所以我们只能去求左子树能返回的最大值，右子树能返回的最大值，选一个较大的
 *
 * 现在我们只考虑了包含最初根节点 8 的路径。那如果不包含当前根节点，而是其他的路径呢？
 * 可以发现在 dfs 函数中，我们每次都求了当前给定的节点的左子树和右子树的最大值，和我们 maxPathSum 函数的逻辑是一样的。
 * 所以我们利用一个全局变量，在考虑 dfs 函数中当前 root 的时候，同时去判断一下包含当前 root 的路径的最大值。
 *
 * 这样在递归过程中就考虑了所有包含当前节点的情况。
 *
 */
public class _124_二叉树中的最大路径和 {

    int res = 0x80000000;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return res;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        //拿到左右子树的最大路径和
        int left = Math.max(dfs(node.left), 0);
        int right = Math.max(dfs(node.right), 0);

        //更新全局的最大路径和
        res = Math.max(res, left + right + node.val);
        //考虑包含根节点的路径的最大值时，并不能单纯的用 root.val + left + right
        ////选择左子树和右子树产生的值较大的一个
        return Math.max(left, right) + node.val;
    }

}