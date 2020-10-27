package leetcode.interviewbook;

public class 面试题04_08_首个共同祖先 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //递归出口
        if (root == null || p == root || q == root) {
            return root;
        }
        //首先获取左子树和右子树的
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        //如果左子树不为空，且右子树的祖先不为空，那就说明是root了
        if (left != null && right != null) {
            return root;
        }
        //如果左子树不为空,那就是左边的
        if (left != null) {
            return left;
        }
        return right;
    }
}