package leetcode.competition.week174;

import comic.Chapter_03.src.Heap;
import lintcode.线段树和树状数组.线段树._248_统计比给定整数小的数的个数;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class _5330_分裂二叉树的最大乘积 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    final int MOD = 1_000_000_007;
    long sum;
    long ans;

    public int maxProduct(TreeNode root) {
        sum = 0;
        long ans = 0;
        dfsSum(root);
        dfsTree(root);
        return (int) (ans % MOD);
    }

    private long dfsTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        long temp = root.val;
        temp += dfsTree(root.left);
        temp += dfsTree(root.right);
        if (ans < temp * (sum - temp)) {
            ans = temp * (sum - temp);
        }
        return temp;    //子树的和
    }

    private void dfsSum(TreeNode root) {
        if (root == null) {
            return;
        }
        sum += root.val;
        dfsSum(root.left);
        dfsSum(root.right);
    }




}