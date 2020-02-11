package leetcode.coding;

/**
 * 题目并非层次遍历，求和那么简单
 *
 * 有一个用例
 * 输入：[2,1,3,null,4]
 * 输出：7
 * 这是因为第一层的最右和第二层的最左是可以连起来求和的，所以结果不是5
 *
 * 正确的思路是，有两种偷取方式
 *      1、对于root这样的节点，偷了之后，必定不能再偷其左右节点
 *      2、如果不偷root，可以尝试偷其左右节点
 *      这两种方式取较大者即可
 */
public class _337_打家劫舍III {


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int rob(TreeNode root) {
        return dfs(root);
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        //尝试偷取该节点，再加上其四个孙子
        int res1 = node.val;
        if (node.left != null) {
            res1 += dfs(node.left.left) + dfs(node.left.right);
        }
        if (node.right != null) {
            res1 += dfs(node.right.left) + dfs(node.right.right);
        }
        //不偷该节点
        int res2 = dfs(node.left) + dfs(node.right);
        return Math.max(res1, res2);
    }
}