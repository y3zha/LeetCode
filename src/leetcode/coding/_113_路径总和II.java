package leetcode.coding;

import lintcode.算法基础班._05_dfs.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 * dfs
 */
public class _113_路径总和II {

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, sum, new ArrayList<>(), res);
        return res;
    }

    private void dfs(TreeNode node, int sum, ArrayList<Integer> list, List<List<Integer>> res) {
        if (node == null) {
            return;
        }
        //node不为null
        list.add(node.val);
        //判断要不要放进res（叶子节点，且sum最终变为0）
        if (node.left == null && node.right == null && sum - node.val == 0) {
            res.add(new ArrayList<>(list));
            //return;     //不要return。。。。要让它状态重置
        }
        //不node符合继续找左右子树
        dfs(node.left, sum - node.val, list, res);
        dfs(node.right, sum - node.val, list, res);
        //状态重置
        list.remove(list.size() - 1);
    }



}