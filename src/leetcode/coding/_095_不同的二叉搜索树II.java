package leetcode.coding;

import lintcode.算法基础班._05_dfs.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 不同的二叉搜索树II，这次需要生成节点了
 * https://leetcode-cn.com/problems/unique-binary-search-trees-ii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-2-7/
 */
public class _095_不同的二叉搜索树II {

    //方法一, 递归，自顶向下
    public static List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        return getRes(1, n);
    }

    private static List<TreeNode> getRes(int start, int end) {
        ArrayList<TreeNode> res = new ArrayList<>();
        //递归出口1：如果当前只有一个数字，那么它就是一棵树，加入到结果集中
        if (start == end) {
            TreeNode node = new TreeNode(start);
            res.add(node);
            return res;
        }

        //递归出口2：如果start大于end,用于生成空树
        if (start > end) {
            res.add(null);
            return res;
        }
        //尝试将每个数字作为根节点
        for (int i = start; i <= end; i++) {
            //得到所有可能的左右子树
            List<TreeNode> leftTrees = getRes(start, i - 1);
            List<TreeNode> rightTrees = getRes(i + 1, end);
            //遍历左右子树组合
            for (TreeNode leftTree : leftTrees) {
                for (TreeNode rightTree : rightTrees) {
                    //当前的根节点i
                    TreeNode root = new TreeNode(i);
                    root.left = leftTree;
                    root.right = rightTree;
                    //把root添加到结果集中
                    res.add(root);
                }
            }
        }
        return res;
    }

    //解法2，dp自底向上，利用同构的思路（对应文章中的解法3：https://leetcode.wang/leetCode-95-Unique-Binary-Search-TreesII.html）
    public static List<TreeNode> generateTrees2(int n) {
        ArrayList<TreeNode>[] dp = new ArrayList[n + 1];
        //初始化
        dp[0] = new ArrayList<>();
        if (n == 0) {
            return dp[0];
        }
        //如果n不为0，添加一棵空树
        dp[0].add(null);

        //有多少个节点，长度从1到n
        for (int len = 1; len <= n; len++) {
            dp[len] = new ArrayList<>();
            //哪个节点作为根节点
            for (int i = 1; i <= len; i++) {
                //左右子树长度
                int left = i - 1;
                int right = len - i;
                /**
                 * 说下下面为什么要克隆。
                 * 假设n为5，root是3，那么左边有2个节点，所以去dp[2]里面找，右边也有两个节点4,5。所以还去dp[2]里面找。
                 * 因为只有dp[2]里面都是2个节点的数。但是dp[2]中的数只有1和2，我们要的是4,5。
                 * 我们不妨将1,2加上root，你会发现正好是4,5。（1+3 = 4，2+3 = 5）
                 * 所以得到结论，当左子树的值直接找前面节点数一样的dp索引，右子树的值也找前面一样的dp索引时,
                 * 需要加上root才能保证val是你需要的，所以右子树要重新创建，不然会破坏前面的树。
                 */
                //遍历左右子树
                for (TreeNode leftTree : dp[left]) {
                    for (TreeNode rightTree : dp[right]) {
                        //新建根
                        TreeNode root = new TreeNode(i);
                        root.left = leftTree;
                        //克隆右子树并加上偏差
                        root.right = clone(rightTree, i);
                        dp[len].add(root);
                    }
                }
            }
        }
        return dp[n];
    }

    private static TreeNode clone(TreeNode node, int offset) {
        if (node == null) {
            return null;
        }
        TreeNode newNode = new TreeNode(node.val + offset);
        //递归克隆
        newNode.left = clone(node.left, offset);
        newNode.right = clone(node.right, offset);
        return newNode;
    }


    public static void main(String[] args) {
        generateTrees(3);
    }
}