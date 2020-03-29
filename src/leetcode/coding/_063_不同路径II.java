package leetcode.coding;

public class _063_不同路径II {
    //坐标型dp
    //1代表有障碍
    public int uniquePathsWithObstacles(int[][] A) {
        int n = A.length;
        int m = A[0].length;
        //首先看下左上角和右下角，如果有障碍，就根本走不到
        if (A[0][0] == 1 || A[n - 1][m - 1] == 1) {
            return 0;
        }
        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                }
                //如果碰到障碍了
                if (A[i][j] == 1) {
                    dp[i][j] = 0;
                }else {
                    if (i > 0) {
                        dp[i][j] += dp[i - 1][j];
                    }
                    if (j > 0) {
                        dp[i][j] += dp[i][j - 1];
                    }
                }
            }
        }
        return dp[n - 1][m - 1];
    }
}