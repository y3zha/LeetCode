package leetcode.coding;

/**
 * 无脑。。
 */
public class _889_根据前序和后序遍历构造二叉树 {

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        int n = pre.length - 1;
        int m = post.length - 1;
        return build(pre, post, 0, n, 0, m);
    }

    private TreeNode build(int[] pre, int[] post, int i, int j, int k, int h) {
        if (i > j || k > h) return null;
        TreeNode root = new TreeNode(pre[i]);
        //下一个根为i的下一个
        if (i + 1 <= j) {
            int nextRoot = pre[i + 1];
            //在post中找到这个nextRoot的位置
            int idx = 0;
            for (int _i = 0; _i < post.length; _i++) {
                if (post[_i] == nextRoot) {
                    idx = _i;
                    break;
                }
            }
            int len = idx - k + 1;
            root.left = build(pre, post, i + 1, i + len, k, idx);
            root.right = build(pre, post, i + len + 1, j, idx + 1, h - 1);
            return root;
        } else {
            return root;
        }
    }
}