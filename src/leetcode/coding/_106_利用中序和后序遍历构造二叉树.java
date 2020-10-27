package leetcode.coding;

import lintcode.算法基础班._05_dfs.TreeNode;

/**
 * 利用中序和后序遍历构造二叉树,思路和105一样
 * 中序：左根右
 * 后序：左右根
 * 所有后序的最后一个是根
 * 思路是利用后序遍历建树
 */
public class _106_利用中序和后序遍历构造二叉树 {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length == 0 || postorder.length == 0) {
            return null;
        }
        TreeNode treeNode = helper(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
        return treeNode;
    }

    private TreeNode helper(int[] inorder, int inLeft, int inRight, int[] postorder, int postLeft, int postRight) {
        //递归出口
        if (inLeft > inRight | postLeft > postRight) {
            return null;
        }
        //不是出口，当前根节点肯定是后序的最后一个
        TreeNode treeNode = new TreeNode(postorder[postRight]);
        //找到这个treeNode在中序中的位置
        int index = 0;
        while (postorder[postRight] != inorder[index]) {
            index++;
        }
        //中序中的左右子树很好划分，postorder要利用中序中的左右子树的长度，中序左子树长度为index-inLeft-1，右子树长度为inRight - index-1
        treeNode.left = helper(inorder, inLeft, index - 1, postorder, postLeft, postLeft + index - inLeft - 1);
        treeNode.right = helper(inorder, index + 1, inRight, postorder, postLeft + index - inLeft, postRight - 1);
        return treeNode;
    }
}