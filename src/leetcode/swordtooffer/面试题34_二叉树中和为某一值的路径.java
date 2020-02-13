package leetcode.swordtooffer;

import java.util.ArrayList;
import java.util.List;

public class 面试题34_二叉树中和为某一值的路径 {

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, res, new ArrayList<>(), sum);
        return res;
    }

    private void dfs(TreeNode node, List<List<Integer>> res, ArrayList<Integer> list, int sum) {
        if (node == null) {
            return;
        }
        list.add(node.val);
        sum -= node.val;
        //如果是结果就放进去，否则继续找
        if (node.left == null && node.right == null && sum == 0) {
            res.add(new ArrayList<>(list));
        } else {
            dfs(node.left, res, list, sum);
            dfs(node.right, res, list, sum);
        }
        //状态重置
        list.remove(list.size() - 1);
    }
}