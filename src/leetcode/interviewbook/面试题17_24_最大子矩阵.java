package leetcode.interviewbook;

import java.util.PriorityQueue;

public class 面试题17_24_最大子矩阵 {

    // 给定一个正整数和负整数组成的 N × M 矩阵，编写代码找出元素总和最大的子矩阵。
    // 我尝试写一下暴力二维前缀和
    // 返回一个数组 [r1, c1, r2, c2]，其中 r1, c1 分别代表子矩阵左上角的行号和列号，r2, c2 分别代表右下角的行号和列号。
    // 超时
    public int[] getMaxMatrix(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int[][] sum = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                sum[i][j] = sum[i][j - 1] + sum[i - 1][j] - sum[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }
        // for (int i = 0; i <= n; i++) {
        //     for (int j = 0; j <= m; j++) {
        //         System.out.print(sum[i][j] +" ");
        //     }
        //     System.out.println();
        // }
        int max = 0x80000000;
        int[] res = new int[4];
        //枚举左上角
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                //枚举右下角
                for (int k = i; k <= n; k++) {
                    for (int l = j; l <= m; l++) {
                        int temp = sum[k][l] - sum[k][j - 1] - sum[i - 1][l] + sum[i - 1][j - 1];
                        if (temp > max) {
                            max = temp;
                            res[0] = i - 1;
                            res[1] = j - 1;
                            res[2] = k - 1;
                            res[3] = l - 1;
                        }
                    }
                }
            }
        }
        // System.out.println(max);
        return res;
    }

    /**
     * 压缩一下，和1074题思路一样
     */
    // public int[] getMaxMatrix(int[][] mat) {
    //     int n = mat.length;
    //     int m = mat[0].length;
    //     for (int i = 0; i < n; i++) {
    //         for (int j = 1; j < m; j++) {
    //             mat[i][j] += mat[i][j - 1];
    //         }
    //     }
    //     int max = 0x80000000;
    //     int[] res = new int[4];
    //     PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
    //     for (int j1 = 0; j1 < m; j1++) {
    //         for (int j2 = 0; j2 < m; j2++) {
    //             pq.clear();
    //             pq.offer(new int[]{0, 0});
    //             int cur = 0;
    //             for (int i = 0; i < n; i++) {
    //                 cur += mat[i][j2] - (j1 > 0 ? mat[i][j1 - 1] : 0);
    //                 if (cur - pq.peek()[0] > max) {
    //                     max = cur - pq.peek()[0];
    //                     res[0] = pq.peek()[1];
    //                     res[1] = j1;
    //                     res[2] = i;
    //                     res[3] = j2;
    //                 }
    //                 pq.offer(new int[]{cur, i});
    //             }
    //         }
    //     }
    //     return res;
    // }

}