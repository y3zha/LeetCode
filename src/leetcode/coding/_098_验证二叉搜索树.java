package leetcode.coding;

import lintcode.算法基础班._05_dfs.TreeNode;

import java.util.*;

/**
 * 验证二叉搜索树，二叉搜索树就是节点的值大于左子树，小于右子树，并且左右子树也是二叉搜索树
 * 两种思路：
 *      1、比较巧妙的解法，利用二叉树的中序遍历，如果中序遍历结果是升序，那么说明这就是一棵二叉搜索树，反之则不是
 *      2、递归判断(也是用了二叉树中序遍历的性质)
 */
public class _098_验证二叉搜索树 {

    //方法一、利用中序遍历
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        ArrayList<Integer> list = new ArrayList<>();
        dfs(root, list);
        return isUp(list);
    }

    private void dfs(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        dfs(root.left, list);
        list.add(root.val);     //其实每次add的时候就可以优化
        dfs(root.right, list);
    }

    //判断判断list中的元素是否是升序
    private boolean isUp(ArrayList<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) <= list.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    //对方法一的优化(迭代)
    public boolean isValidBST2(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode crt = root;
        TreeNode pre = null;
        while (crt != null || !stack.isEmpty()) {
            //把左边一长条全部加进去
            while (crt != null) {
                stack.push(crt);
                crt = crt.left;
            }
            //开始出栈
            crt = stack.pop();
            //当前节点和前面的节点比较大小，如果当前节点的值<=前面节点的值，那就返回false
            if (pre != null && crt.val <= pre.val) {
                return false;
            }
            //指针移动
            pre = crt;
            crt = crt.right;    //有没有右节点，有的话要把右子树的一长条再加进去
        }
        return true;
    }

    TreeNode prev = null;
    //递归写法,也是利用左根右的遍历方式
    public boolean isValidBST3(TreeNode root) {
        if (root == null) {
            return true;
        }
        //左根右，先看左边合不合法
        if (!isValidBST3(root.left)) {
            return false;
        }
        //再看根节点合不合法
        if (prev != null && root.val <= prev.val) {
            return false;
        }
        prev = root;
        //最后看右合不合法
        return isValidBST3(root.right);
    }
}