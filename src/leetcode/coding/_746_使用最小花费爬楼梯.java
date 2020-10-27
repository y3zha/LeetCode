package leetcode.coding;

/**
 * 假设 f(i) 代表爬到第 i 个台阶的最低消耗
 *
 * f(k) = min(f(k-1), f(k-2)) + cost[k]
 * 问题问的是到楼顶的最低消耗，所以到楼顶前的最后一步可能是 f(cost.size()-1) 或者 f(cost.size()-2)
 * 所以最后应该 return min(f(cost.size()-1), f(cost.size()-2));
 */
public class _746_使用最小花费爬楼梯 {

    public static int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        if (n <= 1) return 0;
        if (n == 2) return Math.min(cost[0], cost[1]);
        int[] dp = new int[n];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < n; i++) {
            dp[i] = cost[i] + Math.min(dp[i - 2], dp[i - 1]);
        }
        return Math.min(dp[n - 2], dp[n - 1]);
    }

    public static void main(String[] args) {
        int[] a = {10, 15, 20};
        minCostClimbingStairs(a);
    }
}