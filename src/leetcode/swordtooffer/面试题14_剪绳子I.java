package leetcode.swordtooffer;

public class 面试题14_剪绳子I {

    /**
     * 动态规划思路
     */
    public int cuttingRope(int n) {
        if (n <= 2) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = dp[2] = 1;
        //当前长度
        for (int i = 3; i <= n; i++) {
            //前面切一刀的位置
            for (int j = 1; j <= i - 1; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * dp[i - j], j * (i - j)));
            }
        }
        return dp[n];
    }

    /**
     * 贪心思路 ，其实就是统计有多少个3，如果最后剩下4，要拆成2+2
     * 写在剪绳子2了
     */

}