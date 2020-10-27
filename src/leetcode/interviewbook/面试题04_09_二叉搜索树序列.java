package leetcode.interviewbook;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 面试题04_09_二叉搜索树序列 {

    /**
     * 使用一个队列来存储下一个可能的所有节点，选择其中一个作为路径的下一个元素，一直递归到队列为空，那就能添加进结果集
     * 二叉搜索树是没有重复元素的，并且每次递归用的元素不一样，手机自动去重
     *
     */
    public List<List<Integer>> BSTSequences(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            res.add(new ArrayList<>());
            return res;
        }
        List<Integer> list = new ArrayList<>();
        list.add(root.val);
        // 为什么使用LinkedList而不使用Queue？看状态重置
        LinkedList<TreeNode> queue = new LinkedList<>();
        helper(root, list, res, queue);
        return res;
    }

    private void helper(TreeNode root, List<Integer> list, List<List<Integer>> res, LinkedList<TreeNode> queue) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            queue.add(root.left);
        }
        if (root.right != null) {
            queue.add(root.right);
        }
        if (queue.isEmpty()) {
            res.add(new ArrayList<>(list));
            return;
        }
        //一定要先取出来，因为queue.size它是动态变化的，你不能直接i < queue.size()
        int n = queue.size();
        for (int i = 0; i < n; i++) {
            TreeNode node = queue.get(i);
            queue.remove(i);
            list.add(node.val);
            // dfs
            // 这里的细节是，不能直接传queue的引用进去，传引用就会被修改了，就跟res.add(new ArrayList<>(list));为什么要new一个道理
            // 我们的queue只是来保存下一个节点的信息的
            // helper(node, list, res, queue); 错误写法
            helper(node, list, res, new LinkedList<>(queue));

            // 状态重置,但是这里要放回原位，所以要使用 LinkedList，而不是Queue
            queue.add(i, node);
            list.remove(list.size() - 1);
        }
    }


}