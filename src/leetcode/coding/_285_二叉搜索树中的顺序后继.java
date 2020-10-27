package leetcode.coding;

public class _285_二叉搜索树中的顺序后继 {

    /*
    思路一：中序遍历，拿到所有节点的列表，再找到当前节点取下一个
    思路二：递归执行中序遍历，在递归的过程中获取它的下一个
            如果当前值 <= p.val，那么只需要在右子树中找
            如果当前值 > p.val，如果当前节点没左子树，那么当前节点就是 p 的下一个，否则就是在当前节点左子树中找
            时间 logN,空间调用栈 logN（）
    思路三：
     */
    // 思路二
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null) return null;

        if (root.val <= p.val) {
            return inorderSuccessor(root.right, p);
        }
        TreeNode node = inorderSuccessor(root.left, p);
        if (node != null) {
            return node;
        } else {
            return root;
        }
    }


}