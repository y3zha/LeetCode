package leetcode.interviewbook;

public class 面试题08_01_三步问题 {

    public int waysToStep(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (n == 3) return 4;
        long[] dp = new long[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        int MOD = 1000000007;
        for (int i = 4; i <= n; i++) {
            //发现一件事情，每个除以mod加起来和加起来除以mod不一样的！
            dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % MOD;
        }
        return (int) dp[n];
    }
}