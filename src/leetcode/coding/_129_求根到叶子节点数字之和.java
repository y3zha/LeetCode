package leetcode.coding;

/**
 * dfs或分治
 */
public class _129_求根到叶子节点数字之和 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //回溯
    static int sum;
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root, root.val);
        return sum;
    }

    private void dfs(TreeNode root, int crtSum) {
        //叶子节点就是递归的出口
        if (root.left == null && root.right == null) {
            sum += crtSum;
            return;
        }
        //左子树
        if (root.left != null) {
            dfs(root.left, crtSum * 10 + root.left.val);
        }
        //右子树
        if (root.right != null) {
            dfs(root.right, crtSum * 10 + root.right.val);
        }
    }


    //分治
    //要求一个树所有的路径和，我们只需要知道从根节点出发经过左子树的所有路径和和从根节点出发经过右子树的所有路径和，加起来就可以了。
    //int helper(TreeNode root, int sum)代表从最初根节点经过当前 root 节点达到叶子节点的和
    //首先计算已经累计的和，如果是叶子节点，就是递归出口，否则计算左子树的和，计算右子树的和，返回左右子树的和
    public static int sumNumbers2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return helper(root, 0);
    }

    private static int helper(TreeNode root, int crtSum) {
        int sum = crtSum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return sum;
        }
        int temp = 0;   //计算左右子树和
        if (root.left != null) {
            temp += helper(root.left, sum);
        }
        if (root.right != null) {
            temp += helper(root.right, sum);
        }
        return temp;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(0);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(1);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        sumNumbers2(root);
    }


}