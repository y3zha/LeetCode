package leetcode.coding;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * （一个节点也可以是它自己的祖先）
 *
 * 思路：
 *      迭代：只要点p和q在不同的两棵子树上，那么这个根就是我们要的。只要找到这个分割点
 */
public class _235_二叉搜索树的最近公共祖先 {

    class TreeNode{
        int val;
        TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int pVal = p.val;
        int qVal = q.val;
        TreeNode node = root;

        while (node != null) {
            int n = node.val;
            //利用二叉搜索树的特性，如果p和q的值都大于node值，那么去右子树找，如果p和q的值都小于node值，那么去左子树找
            //如果都不是，那么它就是我们要找的分割点
            if (pVal > n && qVal > n) {
                node = node.right;
            } else if (pVal < n && qVal < n) {
                node = node.left;
            } else {
                return node;
            }
        }
        return null;
    }

    //改成递归
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor2(root.left, p, q);
        } else if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor2(root.right, p, q);
        } else {
            return root;
        }
    }

}