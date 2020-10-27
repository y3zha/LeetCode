package leetcode.coding;

import com.sun.org.apache.regexp.internal.RE;

import java.util.ArrayList;
import java.util.List;

public class _545_二叉树的边界 {

    /**
     * 拆解即可
     * root 就是上界
     * <p>
     * root 有左子树，就遍历左子树，一直向左遍历即可，左边遍历不到了，就从当前节点的右子树开始遍历
     * 右边也是同样的
     * 叶子结点也是遍历
     */
    private List<Integer> ans = new ArrayList<>();

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        if (root == null){ return new ArrayList<>();}
        if (!isLeaf(root)) {
            ans.add(root.val);
        }
        dfsLeft(root.left);
        dfsLeaf(root);
        dfsRight(root.right);

        return ans;
    }

    private void dfsLeaf(TreeNode node) {
        if (node == null) return;
        if (isLeaf(node)) {
            ans.add(node.val);
            return;
        }
        dfsLeaf(node.left);
        dfsLeaf(node.right);
    }

    private void dfsRight(TreeNode node) {
        if (node == null) return;

        if (node.right != null) {
            dfsRight(node.right);
        } else {
            dfsRight(node.left);
        }
        // 右边的要从下往上添加，所以递归过程放在最后
        if (!isLeaf(node)) {
            ans.add(node.val);
        }
    }

    private void dfsLeft(TreeNode node) {
        if (node == null) return;
        if (!isLeaf(node)) {
            ans.add(node.val);
        }
        if (node.left != null) {
            dfsLeft(node.left);
        } else {
            dfsLeft(node.right);
        }
    }

    private boolean isLeaf(TreeNode node){
        return node.left == null && node.right == null;
    }



}