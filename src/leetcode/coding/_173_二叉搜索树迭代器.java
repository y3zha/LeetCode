package leetcode.coding;

import java.util.Stack;

//lintcode 86 实际上就是中序遍历，差不多

/**
 * 设计实现一个带有下列属性的二叉查找树的迭代器：next()返回BST中下一个最小的元素,元素按照递增的顺序被访问（比如中序遍历）
 * next()和hasNext()的询问操作要求均摊时间复杂度是O(1)
 *
 * 思路：每次 next 的时候把 stack 的头拿出来，如果这个节点有右子树的话，把右子树里的左边这条 path 全都给加到 stack 里面。（BST 节点的是顺序是这样的）
 * 比如：  5
 *       / \
 *      2   8
 *     /   /
 *    1   6
 * 上面这个树，在 initialize 的时候我们得到节点5，然后把左边这一条 path 所有节点[5,2,1]全都放进去。next 的时候的顺序就会是1->2->5
 * 当再次走到5的时候，发现有右子树。所以我们把右子树的左边 path [8,6] 就丢进去。然后5之后的 next 就是 5->6->8
 *
 * 步骤就是
 * 1、把当前root及其左边一条放进去
 * 2、pop，pop一个检查它有没有右儿子，有的话也要放进去
 */
public class _173_二叉搜索树迭代器 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    class BSTIterator {
        Stack<TreeNode> stack;

        public BSTIterator(TreeNode root) {
            stack = new Stack<>();
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
        }

        //你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 中至少存在一个下一个最小的数。
        public int next() {
            /*if (stack.isEmpty()) {
                return -1;
            }*/
            TreeNode node = stack.pop();
            TreeNode temp = node.right;
            while (temp != null) {
                stack.push(temp);
                temp = temp.left;
            }
            return node.val;
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return !stack.isEmpty();
        }
    }
    
}