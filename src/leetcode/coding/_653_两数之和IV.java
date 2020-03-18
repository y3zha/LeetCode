package leetcode.coding;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class _653_两数之和IV {
    //BST树上找两数之和，我没有想到什么特别巧妙的方法，只想到把节点都存起来再找，下面这几种解法都是官方的

    //dfs x + y = k,利用一个set，在遍历的时候，如果set中有 k - x，那么就找到了
    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        return dfs(root, k, set);
    }

    private boolean dfs(TreeNode root, int k, Set<Integer> set) {
        if (root == null) {
            return false;
        }
        if (set.contains(k - root.val)) {
            return true;
        }
        set.add(root.val);
        //只要左边或者右边找到就可以了
        return dfs(root.left, k, set) || dfs(root.right, k, set);
    }

    //bfs 和上面一样的思路
    public boolean findTarget2(TreeNode root, int k) {
        if (root == null) return false;
        Set<Integer> set = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            //加这个是因为有个 1 [2]的用例
            if (set.contains(k - poll.val) && k - poll.val != poll.val) {
                return true;
            }
            set.add(poll.val);
            if (poll.left != null) queue.offer(poll.left);
            if (poll.right != null) queue.offer(poll.right);
        }
        return false;
    }

}