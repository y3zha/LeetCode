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


    /**
     * 实际上就是枚举所有的节点，以该节点作为中继点，就会有一条最大路径和，然后维护一个全局的最大路径和
     * 分别求出该节点以左子树和右子树为起点的的最大路径和。然后算出以该节点为中继的值来更新全局最大路径和。
     * 有可能都不选左右两颗子树，因为有可能左右两颗子树是负数，所以有不选的情况。
     *
     *
     */

    /*
    搞一个类 ResultType 它记录这个节点的 pathSum和全局的max。
    一开始是从root开始，那么对于root，有包含和不包含两种情况
    我先去看root的左子树和右子树，得到左右子树的最大路径和（这个是root不作为中继节点的最大路径和，也即是它不作为结果的根的时候）
    还有种情况是 root 作为左右子树的中继节点（也即是作为整个结果的根的时候），再来更新全局的最大路径和
    */

    class ResultType {
        int pathSum = 0x80000000;
        int maxSum = 0x80000000;
    }

    public int maxPathSum2(TreeNode root) {
        return helper(root).maxSum;
    }

    private ResultType helper(TreeNode root) {
        ResultType rt = new ResultType();
        if (root == null) {
            return rt;
        }
        //divide
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);

        //更新当前root的rt
        //情况1：root不作为中继节点（也即是不作为整个结果的根的时候）
        rt.pathSum = Math.max(Math.max(left.pathSum, right.pathSum), 0) + root.val;
        //情况2：root作为中继节点，需要更新下全局的最大值,此时left和right肯定不能作为中继节点，所以用的是pathSum
        int temp = Math.max(left.pathSum, 0) + Math.max(right.pathSum, 0) + root.val;
        rt.maxSum = Math.max(temp, Math.max(left.maxSum, right.maxSum));  //拿左右子树已经存的maxSum来比比
        return rt;
    }

}