package leetcode.coding;

/**
 * 二叉搜索树中的两个节点被错误地交换。请在不改变其结构的情况下，恢复这棵树。
 *
 * 思路一：拿到所有节点，排序，然后插入树，这是最笨的方法
 *      我们只需要找到排列中国交换位置的两个数即可，交换位置也有两种情况
 *      1、相邻两个数做交换：比如12345变成了13245，3和2就是一组逆序对，把这一组交换呢即可
 *      2、不相邻两个数做交换：比如15342，这样就生产了两组逆序对，53和42，要把这两组交换
 *          只需要遍历数组，然后找到这两组逆序对，然后把第一组前一个数字和第二组后一个数字进行交换即完成了还原。
 *          再举个例子：18345672->最多就两组逆序对
 *     设定两个指针，中序遍历的时候看pre指针指向的值是不是大于当前节点的值，如果是的话，那就是我们要找的逆序的数字，分别用first和second保存即可
 *     如果找到第二组逆序对，second就是当前节点，最后把first和second的数字交换即可
 *
 * 进阶：使用O(n)的时间复杂度和常数级空间
 * 思路二：morris版中序遍历
 *
 *
 */
public class _099_恢复二叉搜索树 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //方法一：中序遍历+递归
    TreeNode first = null;
    TreeNode second = null;

    public void recoverTree(TreeNode root) {
        inorderTraversal(root);     //得到first和second
        // 交换值
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    TreeNode pre = null;
    private void inorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        //左
        inorderTraversal(root.left);

        //根
        if (pre != null && pre.val > root.val) {
            //如果是第一次碰到
            if (first == null) {
                first = pre;
                second = root;
            } else {    //如果碰到第二组了，需要把second改成后一组的后一个，此时的后一个也就是当前的
                second = root;
            }
        }
        //移动pre
        pre = root;

        //右
        inorderTraversal(root.right);
    }


    //方法二：morris序列
    public void recoverTree2(TreeNode root) {
        //TODO
    }
}