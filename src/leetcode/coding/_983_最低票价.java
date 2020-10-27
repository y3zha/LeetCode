package leetcode.coding;

public class _983_最低票价 {

    /*
    总体思路是：花最少的钱，完成旅行计划
    dp[i] 表示从开始到第i天的旅行计划的最小花费，因此 i 最大为days中的最后一个值，

    假设今天是需要出行的第i天，那么需要考虑三种情况：

    一天之前买票，是否最便宜？ dp[i-1] + cost[0]
    一周之前买票，是否最便宜？ dp[i-7] + cost[1]
    一月之前买票，是否最便宜？ dp[i-30]+ cost[2]
    状态转移方程为：dp[i] = min(dp[i-1]+cost[0], dp[i-7]+cost[1], dp[i-30]+cost[2])

     */
    public int mincostTickets(int[] days, int[] costs) {
        int n = days.length;
        int[] dp = new int[days[n - 1] + 1];
        // 对旅行的那几天打上标记位
        for (int i = 0; i < n; i++) {
            dp[days[i]] = -1;
        }
        for (int i = 1; i < dp.length; i++) {
            if (dp[i] != -1) {
                // 不旅行
                dp[i] = dp[i - 1];
                continue;
            }
            // 旅行的情况
            dp[i] = dp[i - 1] + costs[0];
            int cost7 = i >= 7 ? dp[i - 7] + costs[1] : costs[1];
            int cost30 = i >= 30 ? dp[i - 30] + costs[2] : costs[2];
            dp[i] = Math.min(dp[i], Math.min(cost7, cost30));
        }
        return dp[dp.length - 1];
    }
}