package leetcode.swordtooffer;

public class 面试题33_二叉搜索树的后序遍历序列 {

    /**
     * 后序遍历，最后一个为root，二叉搜索树是左小右大的，可以判断这个节点是否为BST的根节点
     * 递归判断即可
     *
     * 比如后续遍历结果： 2，7，9，12，11，8
     * 我们现找到根 为8
     * 然后从前往后找左区间，直到nums[index]大于rootVal的时候停下来，2 7 是左区间，此时index停在9上
     * 然后我们要对右区间进行判断，如果右区间出现了比rootVal小的，那肯定不对啊。
     * 如果符合，继续比较下面的即可。
     */
    public boolean verifyPostorder(int[] postorder) {
        if (postorder == null || postorder.length <= 1) {
            return true;
        }
        return isBST(postorder, 0, postorder.length - 1);
    }

    private boolean isBST(int[] postorder, int left, int right) {
        if (left < 0 || right < 0 || left >= right) {
            return true;
        }
        int rootVal = postorder[right];
        //找到左子树和右子树分割点
        int index = 0;
        while (index < right && postorder[index] < rootVal) {
            index++;
        }
        //现在index应该是指向右子树的第一个节点，检查是否都大于rootVal，如果不是，return false
        for (int i = index; i <= right - 1; i++) {
            if (postorder[i] < rootVal) {
                return false;
            }
        }
        return isBST(postorder, left, index - 1) && isBST(postorder, index, right - 1);
    }
}