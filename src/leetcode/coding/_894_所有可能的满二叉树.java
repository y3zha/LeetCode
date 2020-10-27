package leetcode.coding;

import java.util.ArrayList;
import java.util.List;

public class _894_所有可能的满二叉树 {


    /*
    递归做
    首先偶数肯定是不能构成的，只有奇数才能构成
    把一棵树分成左根右，如7个node可以分成115；313；511（左,根,右），5再继续分，可以分为 113、311，3又可以分为111
    根永远只有一个
    只有一个就直接返回了
     */
    public List<TreeNode> allPossibleFBT(int N) {
        List<TreeNode> ans = new ArrayList<>();
        if ((N & 1) == 0) {
            return ans;
        }
        if (N == 1) {
            ans.add(new TreeNode(0));
            return ans;
        }
        for (int i = 1; i < N; i += 2) {
            // 拿到左右
            List<TreeNode> leftNodes = allPossibleFBT(i);
            List<TreeNode> rightnodes = allPossibleFBT(N - i - 1);
            // 排列组合
            for (TreeNode left : leftNodes) {
                for (TreeNode right : rightnodes) {
                    TreeNode root = new TreeNode(0);
                    root.left = left;
                    root.right = right;
                    ans.add(root);
                }
            }
        }
        return ans;
    }
}