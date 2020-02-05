package leetcode.coding;


import java.util.List;
import java.util.Stack;

public class _897_递增顺序查找树 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //非递归中序遍历树
    static TreeNode dummy = new TreeNode(0);
    static TreeNode p = dummy;

    public static TreeNode increasingBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.add(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            p.right = new TreeNode(cur.val);
            p = p.right;
            cur = cur.right;

        }
        return dummy.right;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        root.left = node1;
        root.right = node2;
        increasingBST(root);
    }
}