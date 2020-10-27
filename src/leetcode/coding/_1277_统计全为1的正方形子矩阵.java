package leetcode.coding;

public class _1277_统计全为1的正方形子矩阵 {


    // https://leetcode-cn.com/problems/count-square-submatrices-with-all-ones/solution/tong-ji-quan-wei-1-de-zheng-fang-xing-zi-ju-zhen-f/
    // 这个题解不错
    // dp, f[i][j][k] 代表以i,j为右下角，边长为k的正方形区域是否全为1
    public int countSquares(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        int a = Math.min(n, m);
        boolean f[][][] = new boolean[n][m][a + 1];

        int ans = 0;
        // 统计边长为1的
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 1) {
                    f[i][j][1] = true;
                    ans++;
                }
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                for (int k = 2; k <= a; k++) {
                    f[i][j][k] = mat[i][j] == 1 && f[i - 1][j - 1][k - 1] && f[i - 1][j][k - 1] && f[i][j - 1][k - 1];
                    if (f[i][j][k]) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }


    // 继续优化，题目其实并不关心变长为 1，2... k的有多少个，如果我们直到以i,j为右下角，边长为k是正方形的话
    // 那么这个正方形区域全为1，比如边长为3的正方形区域，全为1，一定是包含了全为2和全为1的区域的
    // 所以只需要记录以 i，j 为右下角区域最大的正方形边长即可
    // 可以将原始的dp降一维度，设f[i][j]表示以(i, j)为右下角的最大全1正方形区域的边长
    public int countSquares2(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        int[][] f = new int[n + 1][m + 1];
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (mat[i - 1][j - 1] == 1) {
                    // 拿到最小的正方形边长+1
                    f[i][j] = Math.min(Math.min(f[i - 1][j], f[i][j - 1]), f[i - 1][j - 1]) + 1;
                    ans += f[i][j];
                }
            }
        }
        return ans;

    }


}