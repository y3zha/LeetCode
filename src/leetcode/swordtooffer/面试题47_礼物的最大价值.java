package leetcode.swordtooffer;

public class 面试题47_礼物的最大价值 {

    public int maxValue(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] f = new int[n][m];
        f[0][0] = grid[0][0];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                f[i][j] = 0xffffffff;
                if (i > 0) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j] + grid[i][j]);
                }
                if (j > 0) {
                    f[i][j] = Math.max(f[i][j], f[i][j - 1] + grid[i][j]);
                }
            }
        }
        return f[n - 1][m - 1];
    }
}