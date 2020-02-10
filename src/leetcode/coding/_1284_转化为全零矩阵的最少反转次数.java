package leetcode.coding;

import java.util.*;

/**
 * 给你一个 m x n 的二进制矩阵 mat。
 * 每一步，你可以选择一个单元格并将它反转（反转表示 0 变 1 ，1 变 0 ）。如果存在和它相邻的单元格，那么这些相邻的单元格也会被反转。（注：相邻的两个单元格共享同一条边。）
 * 请你返回将矩阵 mat 转化为全零矩阵的最少反转次数，如果无法转化为全零矩阵，请返回 -1 。
 * 二进制矩阵的每一个格子要么是 0 要么是 1 。全零矩阵是所有格子都为 0 的矩阵。
 *
 * 思路
 *      最少反转次数，就是最短路径。。BFS，但是怎么做呢？
 *
 *      位运算知识：
 *          1、判断x的最低位是否为1 x&1 == 1
 *          2、判断x的第k位是否为1 x&(1<<k) == 1
 *
 *      BFS思路：
 *          1、首先就是把起始状态加入队列，然后搜索其相邻的状态，如果这个状态没访问过，那就加入队列中，如果状态为全0，那就可以返回了
 *          2、怎么存储状态呢？这边如果用二维矩阵存储就非常的耗费空间，一种巧妙的办法是：
 *              通过将二维矩阵按行优先的顺序展开，看成一个十进制整数的二进制表示即可。
 *              比如：
 *                   [0,0,1]
 *                   [1,0,0]
 *                   [0,1,1]       看成二进制就是 001100011，转为十进制就是99，这就是一种状态映射
 *
 *          3、加入队列是加入映射后的数字，所以我们要编写一个 encode(int[][] mat)的方法，将其映射为数字
 *             出队操作得到的数字，我们也需要编写一个 decode(int x)的方法，得到矩阵，翻转修改状态。
 */
public class _1284_转化为全零矩阵的最少反转次数 {

    //BFS
    public int minFlips(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int x = encode(mat, n, m);
        if (x == 0) {
            return 0;
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(x);
        Set<Integer> set = new HashSet<>();
        set.add(x);
        int steps = 0;

        while (!queue.isEmpty()) {
            steps++;
            int size = queue.size();
            for (int _i = 0; _i < size; _i++) {
                Integer poll = queue.poll();
                int[][] arr = decode(poll, n, m);
                //翻转这个矩阵的每一位
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        //翻转
                        flip(arr, n, m, i, j);
                        int temp = encode(arr, n, m);
                        if (temp == 0) {
                            return steps;
                        }
                        if (!set.contains(temp)) {
                            set.add(temp);
                            queue.offer(temp);
                        }
                        //翻转回来
                        flip(arr, n, m, i, j);
                    }
                }
            }
        }
        return -1;
    }

    private int encode(int[][] mat, int n, int m) {
        int x = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                x = x * 2 + mat[i][j];
            }
        }
        return x;
    }

    private int[][] decode(int x, int n, int m) {
        int[][] mat = new int[n][m];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                mat[i][j] = x & 1;
                x >>= 1;
            }
        }
        return mat;
    }

    int[] dx = {0, 0, -1, 1, 0};
    int[] dy = {1, -1, 0, 0, 0};

    private void flip(int[][] mat, int n, int m, int i, int j) {
        //当前单元格及它相邻的单元格也会被翻转
        for (int k = 0; k < 5; k++) {
            int ni = i + dx[k];
            int nj = j + dy[k];
            if (ni < 0 || ni >= n || nj < 0 || nj >= m) {
                continue;
            }
            //异或取反
            mat[ni][nj] ^= 1;
        }
    }
}