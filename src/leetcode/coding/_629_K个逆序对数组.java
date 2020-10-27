package leetcode.coding;

public class _629_K个逆序对数组 {


    /**
     * 动态规划
     * f[i][j] 表示i个数，j个逆序对的组合方式种类,i个数的j个逆序对的组合，可以在i-1个数的基础上得到
     * 把第i个数插入到前面i-1的i个空位上，相应的逆序数会变化
     * 把第i个数字插在f(i - 1, j)最后，逆序增加0，插在倒数第1个前面，逆序数为1，为保持总逆序数j，那i-1个数的逆序数为j-1，依次类推
     * 可以得到递推式,一共有i-1个，最后一个位置是插在倒数第i-1个的前面，也就是 j - (i-1)，那么递推式就是
     * f[i][j] = f[i-1][j] + f[i-1][j-1] +...+f[i-1][j - i + 1]
     */
    // 超时，但是 cpp过了
    public int kInversePairs(int n, int k) {
        int mod = (int) (1e9 + 7);
        int[][] f = new int[n + 1][k + 1];
        // i个数字，0个逆序对，有一种，就是升序
        for (int i = 1; i <= n; i++) {
            f[i][0] = 1;
        }
        // 枚举i个数字
        for (int i = 2; i <= n; i++) {
            // 枚举逆序对个数
            for (int j = 1; j <= k; j++) {
                for (int p = Math.max(j - i + 1, 0); p <= j; p++) {
                    f[i][j] = (f[i][j] + f[i - 1][p]) % mod;
                }
            }
        }
        return f[n][k];
    }

    // 优化
    // f[i][j] = f[i-1][j] + f[i-1][j-1] +...+f[i-1][j - i + 1]
    // f[i][j-1] = f[i-1][j-1] + f[i-1][j-2] +...+f[i-1][j - i]
    // 做差
    // f[i][j] - f[i][j-1] = f[i-1][j] - f[i-1][j - i]
    // 所以
    // f[i][j] = f[i][j-1] + f[i-1][j] - f[i-1][j-1]
    public int kInversePairs2(int n, int k) {
        int mod = (int) (1e9 + 7);
        int[][] f = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++){
            f[i][0] = 1;
        }
        for (int i = 2; i <= n; i++) {
            // 最大逆序数
            int cnt = i * (i - 1) >> 1;
            for (int j = 1; j <= Math.min(cnt, k); j++) {
                // dp[i-1][j] 不一定比 dp[i-1][j-i] 大（正态分布），+mod防止负数
                // j >= i ? f[i - 1][j - i] : 0，这个是因为 f[i - 1][j] 已经囊括了j以前的了，不需要重复计算，只要计算后面的
                f[i][j] = (f[i][j - 1] % mod + (f[i - 1][j] - (j >= i ? f[i - 1][j - i] : 0) + mod) % mod) % mod;
            }
        }
        return f[n][k];
    }
}