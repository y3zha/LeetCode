package leetcode.coding;

public class _1038_从二叉搜索树到更大和树 {

    // 中序遍历前 2 3 4 5 6 7 8
    // 中序遍历后35 33 30 26 21 15 8
    // 后者是前一个从后累加的结果，只需要 把左根右变为右根左来遍历 ，然后累加结果即可。

    int sum = 0;

    public TreeNode bstToGst(TreeNode root) {
        if (root == null) {
            return root;
        }
        bstToGst(root.right);
        sum += root.val;
        root.val = sum;
        bstToGst(root.left);
        return root;
    }
}