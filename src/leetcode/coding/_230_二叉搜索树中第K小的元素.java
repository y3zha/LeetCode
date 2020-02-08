package leetcode.coding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * 最笨的办法，中序遍历+排序
 */
public class _230_二叉搜索树中第K小的元素 {

    class TreeNode{
        int val;
        TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    //笨办法：中序遍历+排序，不是很好
    public int kthSmallest(TreeNode root, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        inorderTraversal(root, list);
        // int[] arr = list.stream().sorted().mapToInt(Integer::valueOf).toArray();
        // return arr[k - 1];
        Collections.sort(list);
        return list.get(k - 1);
    }

    private void inorderTraversal(TreeNode root, ArrayList<Integer> list) {
        //非递归中序遍历
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.add(cur);
                cur = cur.left;
            }
            //cur为空了，开始pop
            cur = stack.pop();
            list.add(cur.val);
            cur = cur.right;
        }
    }

    //优化，我们找到k个就立马停止
    int res, count;
    public int kthSmallest2(TreeNode root, int k) {
        count = k;
        inorder(root);
        return res;
    }

    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        if (--count == 0) {
            res = root.val;
        }
        inorder(root.right);
    }

}