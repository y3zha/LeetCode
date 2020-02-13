package leetcode.swordtooffer;

/**
 * 根据前序遍历和中序遍历重建二叉树
 */
public class 面试题7_重建二叉树 {

    // 前序遍历 preorder = [3,9,20,15,7] 根左右
    // 中序遍历 inorder = [9,3,15,20,7] 左根右
    // 根据前序遍历中的根去中序遍历中找划分点，递归建立左右子树即可
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        return build(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int[] inorder, int pl, int pr, int il, int ir) {
        //首先找到前序遍历中的根在中序遍历中的下标
        if (pl > pr || il > ir) {
            return null;
        }
        int idx = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == preorder[pl]) {
                idx = i;
                break;
            }
        }
        // 得到根节点
        TreeNode root = new TreeNode(preorder[pl]);
        int len1 = idx - il;// root左子树区间长度
        root.left = build(preorder, inorder, pl + 1, pl + len1, il, idx - 1);
        root.right = build(preorder, inorder, pl + len1 + 1, pr, idx + 1, ir);
        return root;
    }
}