package leetcode.coding;

import java.util.List;

/**
 * 三角形最小路径和（lintcode 109）
 *
 * 本题 初始化最后一行，从三角形的最后一层开始，一直推到顶点，每层记录来自下一层的最小值
 */
public class _120_三角形最小路径和 {

    //dp
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];

        //首先初始化dp的最后一行
        for (int j = 0; j < n; j++) {
            dp[n - 1][j] = triangle.get(n - 1).get(j);
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }

    //自顶向下，记忆化搜索（分治）
    public int minimumTotal2(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];
        //初始化dp，每个都是没访问过的
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        //0,0是起点
        return search(triangle, dp, 0, 0);
    }

    private int search(List<List<Integer>> triangle, int[][] dp, int i, int j) {
        //一直到底才return
        if (i >= triangle.size()) {
            return 0;
        }
        if (dp[i][j] != Integer.MAX_VALUE) {
            return dp[i][j];
        }
        dp[i][j] = Math.min(search(triangle, dp, i + 1, j), search(triangle, dp, i + 1, j + 1)) + triangle.get(i).get(j);
        return dp[i][j];
    }

}