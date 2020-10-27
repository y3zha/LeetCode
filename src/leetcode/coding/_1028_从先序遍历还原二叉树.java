package leetcode.coding;

public class _1028_从先序遍历还原二叉树 {


    /**
     * 由先序遍历的特性可以知道，上一个遍历到的比自己深度浅1的节点必为自己的父节点，所以直接把当前节点加到这父节点下便可以把树连接起来了。
     * 遍历字符串，数字前有多少个-就是在第几层
     */
    private static int idx;
    private static int n;

    private static int curDepth;
    // 全局curD用的很妙,特别是每次进入方法后和寻找'-'时一起重置,由于递归栈的特性,当前方法的depth是不会变的,所有新的curD一定会回退到比它小的那个depth,从而完成连接

    public static TreeNode recoverFromPreorder(String s) {
        char[] nodes = s.toCharArray();
        n = nodes.length;
        return dfs(0, nodes);
    }

    // 错误写法
    private static TreeNode dfs(int preDepth, char[] nodes) {
        // 计算val
        int val = 0;
        while (idx < n && Character.isDigit(nodes[idx])) {
            val = val * 10 + nodes[idx] - '0';
            idx++;
        }
        // 计算当前深度，也就是'-'的个数
        curDepth = 0;
        while (idx < n && nodes[idx] == '-') {
            curDepth++;
            idx++;
        }
        TreeNode node = new TreeNode(val);
        if (curDepth > preDepth) {
            node.left = dfs(curDepth, nodes);
        }
        if (curDepth > preDepth) {
            node.right = dfs(curDepth, nodes);
        }
        return node;
    }

    public static void main(String[] args) {
        recoverFromPreorder("1-2--3---4-5--6---7");
    }
}