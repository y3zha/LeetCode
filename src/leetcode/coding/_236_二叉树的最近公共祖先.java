package leetcode.coding;

import javafx.util.Pair;

public class _236_二叉树的最近公共祖先 {

    /*
    题目意思：
    1、如果p和q都在树中，且有公共祖先，返回公共祖先
    2、如果p和q只有一个在树中，返回存在的那个
    3、如果p和q没有都不在树中，返回null
    本题默认p和q都在树中

    返回p和q最深的祖先，最深的！

    分析：
    1、如果root为null，或者p为root或者q为root，直接返回root
    2、D&C，递归左右子树拿到结果，left和right
        1）如果 left 和 right 都非空，因为只给了 p 和 q 两个结点，都非空，说明一边一个，因此 root 是他们的最近公共祖先
        2）此时若left为空，那最终结果只要看 right；若 right 为空，那最终结果只要看 left

     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

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

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        return helper(root, p, q).lca;
    }

    private ResultType helper(TreeNode root, TreeNode p, TreeNode q) {
        ResultType rt = new ResultType(false, false, null);
        if (root == null) {
            return rt;
        }
        ResultType left = helper(root.left, p, q);
        ResultType right = helper(root.right, p, q);
        // 如果左子树找到了lca，就直接返回，如果右子树找到了lca，就直接返回
        if (left.lca != null) {
            return left;
        }
        if (right.lca != null) {
            return right;
        }
        // 如果左右子树的lca都为null，那就说明当前节点是lca
        rt.hasP = root == p || left.hasP || right.hasP;
        rt.hasQ = root == q || left.hasQ || right.hasQ;
        if (rt.hasP && rt.hasQ) {
            rt.lca = root;
            return rt;
        }
        return rt;
    }


    class ResultType{
        boolean hasP;
        boolean hasQ;
        TreeNode lca;

        public ResultType(boolean hasP, boolean hasQ, TreeNode lca) {
            this.hasP = hasP;
            this.hasQ = hasQ;
            this.lca = lca;
        }
    }
}
