package leetcode.coding;

import java.util.PriorityQueue;

/**
 * 给定一个 m x n 的矩阵，其中的值均为正整数，代表二维高度图每个单元的高度，请计算图中形状最多能接多少体积的雨水。
 *
 * 堆的作用：维护外围最小灌水高度
 *
 * 思路：接雨水I中，我们维护了左右两个最高的墙，把这个思路同样用到二维，那就是维护周围一个圈，用堆来维护周围这一圈中的最小元素。
 *      为什么是维护最小的元素不是最大的元素呢，因为木桶原理呀。这个最小的元素从堆里弹出来，和它四个方向的元素去比较大小，看能不能往里灌水
 *      怎么灌水呢，如果用方向就比较复杂了，我们可以用visited数组来表示哪些遍历过，哪些没遍历过。
 *
 *      如果当前弹出来的高度比它周围的大，那就是能灌水，把下一个柱子放进去的时候，放的高度要取两者较大的（看下面例子）
 *      如果不能灌水，继续走
 *
 *      例子：
 *      [[12,13,8,12],
 *       [13,4,13,12],
 *       [13,8,10,12],
 *       [12,13,12,12]]
 *      这个例子，最外一圈遍历完的最小值是8（遍历时候标记为访问过），8从堆中弹出，它下方的13没有被访问过，13入堆，现在堆中最小的是12，弹出
 *      比如我们现在走到了(2,3)这个位置的12，它能向左边灌水，这时候我们要往堆里插入这个元素，这个插的时候，位置还是nx，ny
 *      但是高度已经是你灌水后的高度了！
 *
 */
public class _407_接雨水II {

    public int trapRainWater(int[][] heights) {
        if (heights == null || heights.length == 0) return 0;
        int n = heights.length;
        int m = heights[0].length;
        boolean[][] vis = new boolean[n][m];
        //优先队列中存放三元组 [x,y,h] 坐标和高度
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);

        //先把最外一圈放进去
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
                    pq.offer(new int[]{i, j, heights[i][j]});
                    vis[i][j] = true;
                }
            }
        }
        int res = 0;
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!pq.isEmpty()) {
            int[] poll = pq.poll();
            for (int k = 0; k < 4; k++) {
                int nx = poll[0] + dirs[k];
                int ny = poll[1] + dirs[k + 1];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !vis[nx][ny]) {
                    //如果外围这一圈中最小的比当前这个还高，那就说明能往里面灌水啊
                    if (poll[2] > heights[nx][ny]) {
                        res += poll[2] - heights[nx][ny];
                    }
                    //高度得是你灌水后的高度了
                    pq.offer(new int[]{nx, ny, Math.max(heights[nx][ny], poll[2])});
                    vis[nx][ny] = true;
                }
            }
        }
        return res;

    }


}