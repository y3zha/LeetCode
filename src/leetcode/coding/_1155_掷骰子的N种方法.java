package leetcode.coding;

public class _1155_掷骰子的N种方法 {

    public int numRollsToTarget(int d, int f, int target) {
        int mod = (int) (1e9 + 7);
        int[][] dp = new int[35][1050];
        dp[0][0] = 1;
        for (int i = 1; i <= d; i++) {
            // 当前这个的值
            for (int j = 1; j <= f; j++) {
                // 枚举前一个的值
                for (int k = 0; k + j <= target; k++) {
                    dp[i][k + j] = (dp[i][k + j] + dp[i - 1][k]) % mod;
                }
            }
        }
        return dp[d][target];
    }
}