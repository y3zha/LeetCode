package leetcode.interviewbook;

public class 面试题04_05_合法二叉搜索树 {

    /**
     * 其实记录二叉搜索树的前驱节点就好了，和前驱节点比较是不是升序的即可
     *
     * 中序遍历
     */

    TreeNode pre = null;

    public boolean isValidBST(TreeNode node) {
        if (node == null) {
            return true;
        }
        //左根右，先看左边合不合法
        if (!isValidBST(node.left)) {
            return false;
        }
        // 再看根合不合法
        if (pre != null && pre.val >= node.val) {
            return false;
        }
        pre = node;
        return isValidBST(node.right);
    }

}