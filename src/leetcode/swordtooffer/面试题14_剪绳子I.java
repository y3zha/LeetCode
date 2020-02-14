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
     * 写在剪绳子2了。
     *
     * 为什么有这个结论呢？
     * 1 结果为 0
     * 2 结果为 1 * 1
     * 3 结果为 1 * 2
     * 4 结果为 2 * 2
     * 5 结果为 2 * 3
     * 6 结果为 3 * 2
     * 7 结果为 3 * 2 * 2
     *
     * 其实就是 (长度/段数)^段数求极值 算出来极值点n为 长度/e
     *
     * 为啥呢 剪绳子剪下来 你剪成 6 也就是两个三 相当于 2*3 或者3个2
     * 超过3就可以剪短了
     */

}