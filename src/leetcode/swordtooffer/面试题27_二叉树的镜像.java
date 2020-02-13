package leetcode.swordtooffer;

public class 面试题27_二叉树的镜像 {

    /**
     * 递归交换左右子树即可
     */
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        //必须先取出来，不能直接赋给root.left和root.right
        TreeNode left = mirrorTree(root.right);
        TreeNode right = mirrorTree(root.left);
        root.left = left;
        root.right = right;
        return root;
    }
}