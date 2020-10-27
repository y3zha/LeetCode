package leetcode.coding;

import java.util.Stack;

/**
 * 就是求出每一层的 heights[] 然后传给84题的函数就可以了。
 * 计算每一层的最大值，更新结果
 */
public class _085_最大矩形 {

    public int maximalRectangle(char[][] mat) {
        if (mat == null || mat.length == 0) return 0;
        int res = 0;
        int n = mat.length, m = mat[0].length;
        int[][] f = new int[n][m];
        //初始化第一行
        for (int i = 0; i < m; i++) {
            f[0][i] = mat[0][i] - '0';
        }
        //剩余行

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == '1') {
                    f[i][j] = f[i - 1][j] + 1;
                }
            }
        }

        // for(int i = 0; i < n; i++){
        //     for(int j = 0; j < m; j++){
        //         System.out.print(f[i][j] +" ");
        //     }
        //     System.out.println();
        // }

        for (int[] a : f) {
            res = Math.max(res, getArea(a));
        }
        return res;

    }

    private int getArea(int[] hs) {
        int res = 0, n = hs.length;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i <= n; i++) {
            int cur = i == n ? -1 : hs[i];
            while (!stack.isEmpty() && cur <= hs[stack.peek()]) {
                int h = hs[stack.pop()];
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                res = Math.max(res, h * w);
            }
            stack.push(i);
        }
        return res;
    }


}