package leetcode.coding;

import lintcode.算法基础班._05_dfs.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class _938_二叉搜索树的范围和 {

    //中序遍历到数组中去

    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        }
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        int res = 0;
        for (Integer i : list) {
            if (i >= L && i <= R) {
                res += i;
            }
        }
        return res;
    }

    private void inorder(TreeNode root,List<Integer> list) {
        if (root == null) {
            return;
        }
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

}