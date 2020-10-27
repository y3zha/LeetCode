package leetcode.interviewbook;

public class 面试题17_12_BiNode {

    /**
     * 就是把二叉搜索树转成单向链表。。
     * 还是中序遍历吧，得要有个前驱节点的
     */


    TreeNode head = new TreeNode(0);    //用于返回
    TreeNode pre = head;
    public TreeNode convertBiNode(TreeNode root) {
        if (root == null) {
            return null;
        }
        dfs(root);
        return head.right;
    }

    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        //处理当前节点
        node.left = null;
        pre.right = node;
        pre = pre.right;
        dfs(node.right);
    }
}