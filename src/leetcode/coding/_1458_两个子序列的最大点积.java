package leetcode.coding;

public class _1458_两个子序列的最大点积 {

    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = -100 * 1000;
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int a = nums1[i - 1];
                int b = nums2[j - 1];
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1]);
                dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + a * b);
                dp[i][j] = Math.max(dp[i][j], a * b);
            }
        }
        return dp[n][m];
    }
}