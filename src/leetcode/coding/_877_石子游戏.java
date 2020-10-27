package leetcode.coding;

public class _877_石子游戏 {


    // f[i][j] 代表取第i堆到第j堆石子的时候，如果先手，alice比lee多的石子数，他们都是尽可能多拿，不让自己亏
    // 这就转化为区间 dp
    // f[i][j] = max(piles[i] - f[i+1][j],piles[j] - f[i][j-1])
    public boolean stoneGame(int[] piles) {

        int n = piles.length;
        int[][] f = new int[n][n];

        // init
        for (int i = 0; i < n; i++) {
            f[i][i] = piles[i];
        }

        // dp
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                f[i][j] = Math.max(piles[i] - f[i + 1][j], piles[j] - f[i][j - 1]);
            }
        }
        return f[0][n - 1] > 0;
    }


    // 压缩空间
    // dp[0][1]dp[0][1] 的值之后，就不再需要 dp[0][0]dp[0][0] 得值。同理得到 dp[1][2]dp[1][2] 的值之后
    // 就不再需要 dp[1][1]dp[1][1]
    // TODO


    // 贾凡解法3  return true


}