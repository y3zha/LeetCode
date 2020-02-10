package leetcode.coding;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 1、递归
 * 2、非递归-stack
 * 3、morris遍历 就是使用线索二叉树，对于每个节点要知道其前去predecessor和后继successor
 */
public class _094_二叉树的中序遍历 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //递归
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        return list;
    }

    private void dfs(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        //左根右
        dfs(root.left, list);
        list.add(root.val);
        dfs(root.right, list);
    }


    //非递归-栈
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            //先把左边一长条加进去
            while (cur != null) {
                stack.add(cur);
                cur = cur.left;
            }
            //此时cur为null，弹出节点添加进list，开始看这个节点右儿子
            cur = stack.pop();
            list.add(cur.val);
            cur = cur.right;
        }
        return list;
    }

    /**
     * morris遍历，实际上就是利用了线索二叉树
     * 如果是中序遍历，当前节点node的前驱是 node的左儿子，然后一直往右走到底
     *
     *  时间复杂度：一共n个节点，找每个节点的前驱和树的高度有关，为logn，所以是nlogn
     *            但事实上，morris遍历中，只有左孩子非空的结点才会经过两次，而其它结点只会经过一次，也就是说遍历的次数小于 2*N
     *            因此使用morris遍历得到先序、中序序列的时间复杂度自然也是 O(N) ；
     */

    /**
     * Morris先序遍历：
     * 对于只到达一次的节点（无左子树节点），root到达时直接打印，根左右，只要到达就打印
     * 对于可以到达两次的节点（有左子树节点），root第一次到达时打印，第二次到达时不打印
     *
     * Morris中序遍历：
     * 对于只到达一次的节点（也就是无左子树节点），root到达时直接打印，因为左根右嘛，左边没有当然访问根了
     * 对于可以到达两次的节点（有左子树节点），root第一次到达时不打印，第二次到达时打印，左根右，有左先打印左
     *
     * 后序遍历较为复杂（不推荐）
     * https://www.cnblogs.com/wyb666/p/10335726.html
     * morris遍历都会经过它两次，而我们后序遍历实在是在第三次来到该结点时打印该结点的。
     * 想得到后序序列，仅仅改变在morris遍历时打印结点的时机是无法做到的。
     * morris实现后序遍历：如果在每次遇到第二次经过的结点时，将该结点的左子树的右边界上的结点
     * 从下到上打印，最后再将整颗树的右边界从下到上打印，终就是这个数的后序序列
     */

    //不破坏原有树结构的morris遍历
    public static List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        //当前节点和前驱节点
        TreeNode cur = root;
        TreeNode pre = null;

        //当cur不为null的时候
        while (cur != null) {
            //如果当前节点没有左儿子，就走右儿子,添加到res中，因为左边没有就直接是根了
            if (cur.left == null) {
                list.add(cur.val);
                cur = cur.right;
            } else {
                //当前节点有左儿子，那么就要找到当前节点的前驱，就是从它的左儿子，然后一直往右走到底。且 不为当前cur节点
                pre = cur.left;
                while (pre.right != null && pre.right != cur) {
                    pre = pre.right;
                }
                // 如果前驱节点的右节点已经被设置，也就是我们设置过线索了，需要将它右孩子重新设置为空，输出当前节点。
                // cur更新为cur.right，因为因为我们走的是cur.left，如果设置过线索，就说明左边都访问过了，要看右边
                if (pre.right == cur) {
                    list.add(cur.val);
                    pre.right = null;
                    cur = cur.right;
                } else {
                    //如果是第一次访问该节点，也就是还没有设置线索，要设置线索，它的right即为当前节点，然后我们继续看cur.left，因为左边还没设置
                    pre.right = cur;
                    cur = cur.left;
                }

            }
        }
        return list;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        root.left = node1;
        root.right = node2;
        inorderTraversal3(root);
    }

}