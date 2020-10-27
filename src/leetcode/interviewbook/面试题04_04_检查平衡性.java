package leetcode.interviewbook;

public class 面试题04_04_检查平衡性 {

    /**
     * 实现一个函数，检查二叉树是否平衡。
     * 需要得到左右子树的高度 如果高度还差大于1就是不平衡的
     *
     * 不平衡标记为-1
     */
    public boolean isBalanced(TreeNode root) {
        return dfs(root) != -1;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        //得到左右子树的高度
        int left = dfs(node.left);
        int right = dfs(node.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }
        return 1 + Math.max(left, right);
    }
}