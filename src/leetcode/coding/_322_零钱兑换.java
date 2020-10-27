package leetcode.coding;

import java.util.Arrays;
import java.util.Collections;

/**
 * 编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 动态规划
 * dp[i]代表凑成总金额为i所需的最少的金币数
 */
public class _322_零钱兑换 {
    /**
     * 完全背包问题写法
     * 直接套模版
     */
    public int coinChange2(int[] coins, int amount) {
        int n = coins.length;
        int[][] f = new int[n + 1][amount + 1];
        for (int j = 1; j <= amount; j++) {
            f[0][j] = Integer.MAX_VALUE;
        }
        f[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= amount; j++) {
                // 不选择
                f[i][j] = f[i - 1][j];
                // 选择
                if (j >= coins[i] && f[i][j - coins[i]] != Integer.MAX_VALUE) {
                    f[i][j] = Math.min(f[i][j], f[i][j - coins[i]] + 1);
                }
            }
        }
        return f[n][amount] == Integer.MAX_VALUE ? -1 : f[n][amount];
    }

    /**
     * 压缩成一维
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        int n = coins.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= amount; j++) {
                if (j >= coins[i] && dp[j - coins[i]] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

}