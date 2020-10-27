package leetcode.coding;

import lintcode.算法基础班._05_dfs.TreeNode;

/**
 * 利用前序和中序遍历结果构造二叉树
 * 前序：根左右
 * 中序：左根右
 * 所以前序中的第一个是根，找到根在中序中的位置，划分为两部分，根的左边是左子树，根的右边是右子树
 * 然后利用中序的左右子树对前序的数组来划分
 * 划分需要一个范围，用preLeft、preRight代表在先序中的范围，inLeft，inRight代表在中序中的范围
 */
public class _105_利用前序和中序遍历构造二叉树 {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        TreeNode treeNode = helper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
        return treeNode;

    }

    private TreeNode helper(int[] preorder, int preLeft, int preRight, int[] inorder, int inLeft, int inRight) {
        //递归终止条件
        if (preLeft > preRight || inLeft > inRight) {
            return null;
        }
        //重建根节点
        TreeNode treeNode = new TreeNode(preorder[preLeft]);
        //找到这个节点在中序中的位置(树中没有重复的元素)
        int index = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (preorder[preLeft] == inorder[i]) {
                index = i;
                break;
            }
        }
        //根据index，利用前序遍历重建左右子树(这个需要看下我的笔记)
        //preLeft变为起点的后一个，而末位置是中序中左子树的长度，为index - inLeft，中序的范围是左边那棵树，就是inLeft到index-1
        treeNode.left = helper(preorder, preLeft + 1, preLeft + index - inLeft, inorder, inLeft, index - 1);
        //preorder的右子树变为上面左子树末位置再加1到末位置preRight   inorder 的右子树就是index+1到末位置inRight
        treeNode.right = helper(preorder, preLeft + index - inLeft + 1, preRight, inorder, index + 1, inRight);
        return treeNode;
    }

}