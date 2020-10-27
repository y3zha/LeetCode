package leetcode.coding;

import java.util.Arrays;

public class _1000_合并石头的最低成本 {

    /**
     * 区间 dp
     * 我之前一个错误是直接枚举了K堆，这是不对的，正切做法是从两堆开始枚举到K堆
     *https://www.acwing.com/file_system/file/content/whole/index/content/492220/
     * 最后一步 : 把k-1堆 和 最后1堆合并成k堆 然后把k堆合并成1堆
     * 首先 ：合并成k堆
     * 枚举分界点p，把区间[i,j]分为[i, p] 和 [ p+1, j]左右两端，把左边合并成k-1堆，右边合并成1堆即可
     * 从2到K枚举合并的堆数k ，f(i,j,k) = min (f(i,p,k-1)+(p+1,j,1))
     * 其次 ：再把k堆合并成1堆
     * f(i,j,1) = min(f(i,j,1)，f(i,j,K) + sum(i,j))
     * 最终所求为 f(1, n, K)
     */
    int[][][] dp;   // dp[i][j][k]表示把第i堆到第j堆合并为第k堆所需要的最小代价
    int[] s;  //前缀和数组，求代价
    int INF = (int) 1e9;

    public int mergeStones(int[] stones, int K) {
        int n = stones.length;
        // 特判
        if ((n - 1) % (K - 1) != 0) return -1;
        if (n < 2) return 0;
        dp = new int[31][31][31];
        s = new int[31];
        // 初始化 dp
        for (int[][] a : dp) {
            for (int[] b : a) {
                Arrays.fill(b, INF);
            }
        }
        // 初始化前缀和以及第一堆的情况
        for (int i = 1; i <= n; i++) {
            s[i] = s[i - 1] + stones[i - 1];
            dp[i][i][1] = 0;
        }
        // K堆石子进行合并
        for (int len = 2; len <= n; len++) {
            //枚举起点
            for (int i = 1; i <= n - len + 1; i++) {
                int j = i + len - 1;
                //枚举连续堆数
                for (int k = 2; k <= K; k++) {
                    // 枚举划分的分界点,记录把i～j堆合并为k堆所需要的最小代价
                    for (int l = i; l < j; l++) {
                        dp[i][j][k] = Math.min(dp[i][j][k], dp[i][l][k - 1] + dp[l + 1][j][1]);
                    }
                }
                // 把k堆合并为一堆所需要的最小代价
                dp[i][j][1] = Math.min(dp[i][j][1], dp[i][j][K] + s[j] - s[i - 1]);
            }
        }
        return dp[1][n][1];
    }

}