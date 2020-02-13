package leetcode.swordtooffer;

public class 面试题55_平衡二叉树II {

    //判断该树是不是平衡二叉树
    //如果返回-1说明是不平衡的

    public boolean isBalanced(TreeNode root) {
        return dfs(root) != -1;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //获取最右子树的结果
        int left = dfs(root.left);
        int right = dfs(root.right);

        //如果左子树不平衡 或者右子树不平衡，或者高度差大于1，就返回不平衡
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }
        //否则返回树的正常高度
        return 1 + Math.max(left, right);
    }
}