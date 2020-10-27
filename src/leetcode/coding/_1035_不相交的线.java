package leetcode.coding;

public class _1035_不相交的线 {

    // 就是求最长公共子序列
    public int maxUncrossedLines(int[] a, int[] b) {
        int n = a.length;
        int m = b.length;

        int[][] f = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
                if (a[i - 1] == b[j - 1]) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - 1] + 1);
                }
            }
        }
        return f[n][m];
    }
}