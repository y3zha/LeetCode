package leetcode.coding;

public class _256_粉刷房子 {

    // 序列+状态 dp

    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        int n = costs.length;
        int[][] dp = new int[n + 1][3];
        dp[0][0] = dp[0][1] = dp[0][2] = 0;

        //枚举每一栋房子
        for (int i = 1; i <= n; i++) {
            //当前这栋房子刷成什么颜色
            for (int j = 0; j < 3; j++) {
                dp[i][j] = 0x7fffffff;
                //枚举之前那栋房子刷成什么颜色
                for (int k = 0; k < 3; k++) {
                    if (j != k) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + costs[i - 1][j]);
                    }
                }
            }
        }
        return Math.min(dp[n][0], Math.min(dp[n][1], dp[n][2]));
    }

}