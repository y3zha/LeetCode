package leetcode.coding;

import java.util.*;

public class _501_二叉搜索树中的众数 {

    //先把中序遍历的节点都放进去，再遍历数组找candidate
    //这个不能用投票算法，因为可能有多个众数
    public int[] findMode(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        //num -> freq
        HashMap<Integer, Integer> map = new HashMap<>();
        dfs(root, list, map);
        ArrayList<Map.Entry<Integer, Integer>> l = new ArrayList<>(map.entrySet());
        l.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));
        Iterator<Map.Entry<Integer, Integer>> it = l.iterator();
        int last = 0;
        List<Integer> res = new ArrayList<>();
        while (it.hasNext()) {
            Map.Entry<Integer, Integer> next = it.next();
            int val = next.getValue();
            if (last == 0 || val == last) {
                res.add(next.getKey());
                last = val;
            } else break;
        }
        return res.stream().mapToInt(Integer::valueOf).toArray();
    }

    private void dfs(TreeNode root, List<Integer> list, HashMap<Integer, Integer> map) {
        if (root == null) {
            return;
        }
        dfs(root.left, list, map);
        list.add(root.val);
        map.put(root.val, map.getOrDefault(root.val, 0) + 1);
        dfs(root.right, list, map);
    }

    /*
    上面这个思路用了额外的搜索空间，如果不用额外搜索空间呢
    二叉树的中序遍历是一个升序的序列、逐个对比当前节点与前一个节点的值，更新当前节点值出现次数以及最大出现次数
    如果 curTimes = maxTimes，就将 root.val 添加到 结果集合中
    如果 curTimes > maxTimes，先将结果集清空，再将 root.val 添加到 结果集合中
     */
    private int maxTimes = 0;
    private int curTimes = 1;
    private TreeNode pre = null;    // 全局变量保存当前遍历到的 pre
    private List<Integer> res;

    public int[] findMode2(TreeNode root) {
        if (root == null) {
            return new int[]{};
        }
        res = new ArrayList<>();
        dfs(root);
        return res.stream().mapToInt(Integer::valueOf).toArray();
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        // inorder traversal
        dfs(root.left);
        if (pre != null) {
            curTimes = root.val == pre.val ? curTimes + 1 : 1;
        }
        if (curTimes == maxTimes) {
            res.add(root.val);
        } else if (curTimes > maxTimes) {
            res.clear();
            res.add(root.val);
            maxTimes = curTimes;
        }
        pre = root;
        dfs(root.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(2);
        root.right = node1;
        node1.left = node2;
        new _501_二叉搜索树中的众数().findMode2(root);
    }


}