package leetcode.coding;

import lintcode.算法基础班._05_dfs.TreeNode;

/**
 * 思路一，递归遍历所有节点,,这个方案并没有利用完全二叉树的性质
 * 思路二：利用完全二叉树的性质
 *        它是一棵空树或者它的叶子节点只出在最后两层，若最后一层不满则叶子节点只在最左侧。
 *        满二叉树：如果满二叉树的层数为h，则总节点数为：2^h - 1.
 *        所以对root的左右子树做高度统计，left和right有两种结果
 *        1、left == right。这说明，左子树一定是满二叉树，因为节点已经填充到右子树了，左子树必定已经填满了。所以左子树的节点总数我们可以直接得到，是2^left - 1，加上当前这个root节点，则正好是2^left。再对右子树进行递归统计。
 *        2、left != right。说明此时最后一层不满，但倒数第二层已经满了，可以直接得到右子树的节点个数。同理，右子树节点+root节点，总数为2^right。再对左子树进行递归查找。
 *
 */
public class _222_完全二叉树的节点个数 {

    //思路一
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    //思路二
    public int countNodes2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //获取左右层数
        int left = getLevel(root.left);
        int right = getLevel(root.right);
        if (left == right) {
            //相等情况，左右加和，左边包括了root的
            return (1 << left) + countNodes2(root.right);
        } else {
            //不相等的情况，右侧是知道的，再加左侧即可
            return (1 << right) + countNodes2(root.left);
        }
    }

    //获取当前节点的层数(递归)
    public int getLevel(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // return 1 + Math.max(getLevel(node.left), getLevel(node.right));
        //其实直接走左边就可以！因为左边一定比右边深
        return 1 + getLevel(node.left);
    }

    //获取当前节点的层数(非递归)----对于完全二叉树，可以利用其特点，不用递归直接算，左边是最大深度
    public int getLevel2(TreeNode node) {
        int level = 0;
        while (node != null) {
            level++;
            node = node.left;
        }
        return level;
    }


}