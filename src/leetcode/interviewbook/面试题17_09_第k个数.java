package leetcode.interviewbook;

public class 面试题17_09_第k个数 {

    /**
     * 这个题和丑数是一样的呀
     * 两种方法，优先队列和动态规划思想
     * 这里写个动态规划思想的吧
     */

    public int getKthMagicNumber(int k) {
        int[] dp = new int[k];
        dp[0] = 1;
        int p3 = 0, p5 = 0, p7 = 0;
        for (int i = 1; i < k; i++) {
            dp[i] = Math.min(dp[p3] * 3, Math.min(dp[p5] * 5, dp[p7] * 7));
            //不能写if else if 这种，要同步增加的
            if (dp[i] == dp[p3] * 3) p3++;
            if (dp[i] == dp[p5] * 5) p5++;
            if (dp[i] == dp[p7] * 7) p7++;
        }
        return dp[k - 1];
    }
}