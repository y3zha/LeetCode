package leetcode.coding;

public class _700_二叉搜索树中的搜索 {

    // 要利用二叉搜索树的他特性，只要走一边
    public TreeNode searchBST(TreeNode root, int val) {
        while (root != null && root.val != val) {
            root = root.val < val ? root.right : root.left;
        }
        return root;
    }

    public TreeNode searchBST2(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val == val) {
            return root;
        } else if (root.val < val) {
            return searchBST2(root.right, val);
        } else {
            return searchBST2(root.left, val);
        }
    }
}