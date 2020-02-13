package leetcode.swordtooffer;

/**
 * 利用二叉搜索树的特性
 * 只要点p和q在不同的两棵子树上，那么这个根就是我们要的。只要找到这个分割点
 */
public class 面试题68_二叉搜索树的最近公共祖先I {

    //递归写法
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }
    }
}