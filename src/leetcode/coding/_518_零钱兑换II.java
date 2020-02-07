package leetcode.coding;

/**
 * 写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
 * 完全背包问题
 *
 * 0-1 背包问题：当前考虑的物品用或者不用；
 * 完全背包问题：当前考虑的物品用或者不用，如果用，用几个。
 *
 * dp[i][j]代表前i种硬币有多少种方案 凑成金额 j
 *
 * https://blog.csdn.net/weixin_44424668/article/details/104017370 第五个
 *
 * 初始化：0种硬币，凑成amount为0时是1种方案，其他都是0种方案
 *      1种以上硬币，凑成amount为0都是0种方案
 *
 * https://leetcode-cn.com/problems/coin-change-2/solution/dong-tai-gui-hua-wan-quan-bei-bao-wen-ti-by-liweiw/
 */
public class _518_零钱兑换II {

    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        //初始化，0种硬币，凑成amount为0时是1种方案，其他都是0种方案，1种以上硬币，凑成amount为0都是0种方案
        dp[0][0] = 1;
        for (int j = 1; j <= amount; j++) {
            dp[0][j] = 0;
        }
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 0;
        }

        //前i种硬币
        for (int i = 1; i <= n; i++) {
            //凑成多少amount
            for (int j = 0; j <= amount; j++) {
                //不用
                dp[i][j] = dp[i - 1][j];
                //用
                if (j - coins[i - 1] >= 0) {
                    dp[i][j] += dp[i][j - coins[i - 1]];    //这个是推导出来的，每一行单元的值的填写我们只要看它的左边就好了（肯定比i-1层新），如果没有左边，它至少是上一行单元格的值。
                }
            }
        }
        return dp[n][amount];
    }
}