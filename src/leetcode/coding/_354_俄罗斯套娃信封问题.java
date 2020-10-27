package leetcode.coding;

import java.util.Arrays;

public class _354_俄罗斯套娃信封问题 {

    // 暴力 dp
    public int maxEnvelopes(int[][] env) {
        if (env == null || env.length == 0 || env[0] == null || env[0].length == 0) {
            return 0;
        }
        // 首先对信封按长度进行升序排序，如果长度一样则按照宽度进行升序排序
        Arrays.sort(env, (o1, o2) -> o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1]);
        int n = env.length;
        int[] dp = new int[n];
        int res = 0x80000000;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (env[j][0] < env[i][0] && env[j][1] < env[i][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /**
     * 二分搜索的优化
     */
    public int maxEnvelopes2(int[][] env) {
        if (env == null || env.length == 0 || env[0] == null || env[0].length == 0) {
            return 0;
        }
        // 先按照 w 升序排序，宽度一样，再按照 h 降序排序
        // 这样子，因为 w 已经升序排好，并且 h 大的在前，所以相同 w 下的不同 h ，我们就先选择了较大的 h
        // 我们只要看这个 w 和 h下的最长上升子序列即可，不需要去考虑小于这个 h 的情况
        // 将问题转换为 h 的 lis Longest Increasing subSequence
        Arrays.sort(env, (o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0]);
        int n = env.length;
        int[] dp = new int[n];
        int len = 0;
        for (int i = 0; i < n; i++) {
            int idx = Arrays.binarySearch(dp, 0, len, env[i][1]);
            if (idx < 0) {
                idx = -idx - 1;
            }
            dp[idx] = env[i][1];
            if (idx == len) {
                len++;
            }
        }
        return len;
    }
}