package leetcode.swordtooffer;

public class 面试题10_斐波那契数列I {

    public int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        int MOD = 1_000_000_007;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
        }
        return dp[n];
    }

}