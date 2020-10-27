package leetcode.swordtooffer;

public class 面试题26_树的子结构 {

    /**
     * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
     * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
     * <p>
     * 思路
     * 碰到节点值一样的，就开始递归判断
     */

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        return isSub(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);

    }

    private boolean isSub(TreeNode A, TreeNode B) {
        //node2为null，node1可以有，也可以没有，node1为null，node2必须为null
        if (A == null) {
            return B == null;
        }
        //值不相等必定不是当前node的子树
        if (A.val != B.val) {
            return false;
        }
        //值相等，继续找
        return isSub(A.left, B.left) && isSub(A.right, B.right);
    }


}