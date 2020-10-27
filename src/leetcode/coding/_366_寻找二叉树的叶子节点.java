package leetcode.coding;

import java.util.ArrayList;
import java.util.List;

public class _366_寻找二叉树的叶子节点 {

    List<List<Integer>> res;
    public List<List<Integer>> findLeaves(TreeNode root) {
        res = new ArrayList<>();
        helper(root);
        return res;
    }

    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 拿到左右子树的高度
        int left = helper(root.left);
        int right = helper(root.right);
        int height = Math.max(left, right);
        // 如果左右子树的高度为结果集里的长度,也就是可以添加的
        if (res.size() == height) {
            res.add(new ArrayList<>());
        }
        res.get(height).add(root.val);
        // 返回当前节点开始的树的高度
        return height + 1;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        _366_寻找二叉树的叶子节点 test = new _366_寻找二叉树的叶子节点();
        test.findLeaves(node1);
    }
}