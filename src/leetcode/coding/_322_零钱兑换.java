package leetcode.coding;

/**
 * 编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 动态规划
 * dp[i]代表凑成总金额为i所需的最少的金币数
 */
public class _322_零钱兑换 {

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 0;

        //对于金额为i的
        for (int i = 1; i <= amount; i++) {
            //首先初始化，要无穷多个才能凑成
            dp[i] = Integer.MAX_VALUE;
            //对于每一种硬币的情况
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j] && dp[i - coins[j]] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}