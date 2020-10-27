package leetcode.coding;

public class _250_统计同值子树 {

    // 叶子节点肯定是同值子树
    // 也就是node.left == null && node.right == null
    // 对于非叶节点，它的值要和叶子节点的值一样才是同值子树,那么有两种情况
    // 1、只有左儿子，那么就是
    //   isSame(node.left) && node.val == node.left.val
    // 2、只有右儿子，那么就是
    //   isSame(node.right) && node.val == node.right.val
    // 3、左右儿子都有，那么就是
    //   上面两个条件都要满足
    int cnt = 0;

    public int countUnivalSubtrees(TreeNode root) {
        isSame(root);
        return cnt;
    }

    private boolean isSame(TreeNode node) {
        if (node == null) {
            return true;
        }

        // if (node.left == null && node.right == null) {
        //     cnt++;
        //     return true;
        // }
        // 下面的过程包含叶子节点了
        boolean leftOk = false;
        boolean rightOk = false;
        if (node.left == null || isSame(node.left) && node.val == node.left.val) {
            leftOk = true;
        }
        if (node.right == null || isSame(node.right) && node.val == node.right.val) {
            rightOk = true;
        }
        if (leftOk && rightOk) {
            cnt++;
            return true;
        }
        return false;
    }

}