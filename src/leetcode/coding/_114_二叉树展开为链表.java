package leetcode.coding;

import lintcode.算法基础班._05_dfs.TreeNode;

import java.util.Stack;

/**
 * 和leetcode 114一样
 * 给定一棵二叉树，原地转换成链表，二叉树的 right 指针表示链表中的 next 指针。
 */
public class _114_二叉树展开为链表 {

    //思路一：分治法，我们要将其变成那样的结果，首先处理左子树，把它变成一长条，然后处理右子树，把它变成右边一长条
    //然后 左子树那一长条的最后一个节点先要去连接当前节点的右子树一长条的头节点(root.right)，然后将左子树的这一长条的头节点变为根的右子树。
    public void flatten(TreeNode root) {
        // helper函数需要return的是当前子树的最后一个节点。在选择return值的时候应该考虑三种基本情况：
        // 1)叶子节点;  ===> 最后一个节点就是自己(root)
        // 2)只有右子树或左右都有（比如[1,null,2]，或[1,2,3]); ===> 这种情况返回右子树即可，因为已经处理完了
        // 3)只有左子树（比如[1,2])。===> 这种情况返回lastLeft
        helper(root);
    }

    private TreeNode helper(TreeNode root) {
        if (root == null) {
            return null;
        }
        //divide
        TreeNode leftLast = helper(root.left);
        TreeNode rightLast = helper(root.right);

        //连接
        if (leftLast != null) {
            leftLast.right = root.right;    //左子树最后一个连接右子树
            root.right = root.left;     //根连接左子树的头
            root.left = null;   //别忘记置空左子树
        }
        //左子树为空，或者左右都有（左子树已经处理完了），直接返回右子树
        if (rightLast != null) {
            return rightLast;
        }
        //只有左子树，返回处理完的左子树
        if (leftLast != null) {
            return leftLast;
        }
        //叶子节点
        return root;
    }

    //思路二，treversal，题目中，后序遍历结果为654321，实际上就是右左根的遍历结果，直接来
    TreeNode lastNode = null;       //记录上一次遇到的点
    public void flatten2(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten2(root.right);       //先右
        flatten2(root.left);        //再左
        root.right = lastNode;      //处理根的连接，lastNode记录的是上一次遇到的节点
        root.left = null;
        lastNode = root;    //每次处理完当前root后将lastNode置为root, 然后去遍历下一个点
    }

    //利用前序遍历非递归
    public void flatten3(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            //前序遍历
            TreeNode node = stack.pop();
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
            //进行连接
            node.left = null;
            if (stack.empty()) {
                node.right = null;
            } else {
                node.right = stack.peek();
            }
        }
    }
}