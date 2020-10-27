package leetcode.coding;

public class _669_修剪二叉搜索树 {

    /**
     * 当 node.val > R，那么修剪后的二叉树必定出现在节点的左边。
     * <p>
     * 类似地，当 node.val < L那么修剪后的二叉树出现在节点的右边。否则，我们将会修剪树的两边。
     */
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) {
            return root;
        }
        if (root.val > R) {
            return trimBST(root.left, L, R);
        }
        if (root.val < L) {
            return trimBST(root.right, L, R);
        }
        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);
        return root;
    }
}