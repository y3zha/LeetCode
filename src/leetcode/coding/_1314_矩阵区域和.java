package leetcode.coding;

public class _1314_矩阵区域和 {

    /*
    类似于一个矩形,(这个矩形大小会变)，在数据矩阵上向右和向下滑动，每走一步，就会笼罩一个区域，计算这个区域里面值的和，类似于深度学习中的卷积操作
     */

    // 暴力
    public int[][] matrixBlockSum(int[][] mat, int K) {
        int n = mat.length;
        int m = mat[0].length;
        int[][] ans = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = Math.max(0, i - K); k <= Math.min(n - 1, i + K); k++) {
                    for (int l = Math.max(0, j - K); l <= Math.min(m - 1, j + K); l++) {
                        ans[i][j] += mat[k][l];
                    }
                }
            }
        }
        return ans;
    }

    // 前缀和优化
    public int[][] matrixBlockSum2(int[][] mat, int K) {
        int n = mat.length;
        int m = mat[0].length;
        int[][] ans = new int[n][m];
        int[][] ps = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                ps[i][j] = ps[i - 1][j] + ps[i][j - 1] - ps[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // 左上角与右下角
                int x1 = Math.max(0, i - K - 1), y1 = Math.max(0, j - K - 1);
                int x2 = Math.min(n, i + K), y2 = Math.min(m, j + K);
                ans[i - 1][j - 1] = ps[x2][y2] - ps[x2][y1] - ps[x1][y2] + ps[x1][y1];
            }
        }
        return ans;
    }

}