package leetcode.swordtooffer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class 面试题54_二叉搜索树的第k大节点 {

    /**
     * 中序遍历+sort
     */
    public int kthLargest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            list.add(cur.val);

            cur = cur.right;
        }
        Collections.sort(list, Collections.reverseOrder());
        return list.get(k - 1);
    }
}