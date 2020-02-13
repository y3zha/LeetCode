package leetcode.swordtooffer;

public class 面试题68_二叉树的最近公共祖先II {

    /**
     * 分治法,从左右子树分别查找
     * 1、A、B在root的左右子树中，返回它们的lca，这里分为3种情况
     *  1.1 AB都在左子树(left != null但right = null) ==> 一直return left
     *  1.2 AB都在右子树(left = null 但right != null) ==> 一直return right
     *  1.3 A在左，B在右，一边一个，也就是左边能找到A，右边能找到B，左右都不空，root就是我们要找的lca
     * 2、两边都是空的，return空
     */

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B) {
        if (root == null || root == A || root == B) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, A, B);
        TreeNode right = lowestCommonAncestor(root.right, A, B);

        if (left != null && right != null) {
            return root;
        }
        if (left != null) {
            return left;
        }
        if (right != null) {
            return right;
        }
        return null;
    }
}