package leetcode.coding;

import java.util.Arrays;

public class _375_猜数字大小II {


    /*
    区间 dp
    https://leetcode-cn.com/problems/guess-number-higher-or-lower-ii/solution/dong-tai-gui-hua-c-you-tu-jie-by-zhang-xiao-tong-2/
     */
    // 一种我不太习惯的写法
    public int getMoneyAmount(int n) {
        int[][] f = new int[500][500];
        // 枚举末尾
        for (int j = 1; j <= n; j++) {
            // 枚举开头
            for (int i = j - 1; i >= 1; i--) {
                f[i][j] = Integer.MAX_VALUE;
                // 枚举中间的 k
                for (int k = i; k <= j; k++) {
                    f[i][j] = Math.min(f[i][j], k + Math.max(f[i][k - 1], f[k + 1][j]));
                }
            }
        }
        return f[1][n];
    }

    // my
    public int getMoneyAmount2(int n) {

        n = n + 1;
        int[][] f = new int[n][n];

        for (int i = 0; i < n; i++) {
            f[i][i] = 0;
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                f[i][j] = Integer.MAX_VALUE;
                for (int k = i + 1; k <= j - 1; k++) {
                    f[i][j] = Math.min(f[i][j], Math.max(f[i][k - 1], f[k + 1][j]) + k);
                }
                f[i][j] = Math.min(f[i][j], Math.min(i + f[i + 1][j], j + f[i][j - 1]));
            }
        }
        return f[1][n - 1];
    }


}