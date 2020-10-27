package leetcode.coding;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _1162_地图分析 {

    // 暴力写一下,太慢了
    public static int maxDistance(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        List<int[]> lands = new ArrayList<>();
        List<int[]> seas = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    lands.add(new int[]{i, j});
                } else {
                    seas.add(new int[]{i, j});
                }
            }
        }
        if (lands.size() == 0 || seas.size() == 0) {
            return -1;
        }
        int res = 0;
        // 距离陆地区域最远的海洋区域是,枚举所有海洋，看他们的到陆地的最短距离，在这些最短距离中找一个最大的

        for (int i = 0; i < seas.size(); i++) {
            int min = Integer.MAX_VALUE;
            int[] sea = seas.get(i);
            for (int j = 0; j < lands.size(); j++) {
                int[] land = lands.get(j);
                int dis = Math.abs(land[0] - sea[0]) + Math.abs(land[1] - sea[1]);
                min = Math.min(min, dis);
            }
            res = Math.max(res, min);
        }
        return res;
    }

    /**
     * 改进版
     */
    public static int maxDistance2(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 把陆地都放进去
                if (grid[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        if (queue.size() == 0 || queue.size() == n * n) {
            return -1;
        }
        int step = 0;

        int[] dirs = {-1, 0, 1, 0, -1};
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                for (int k = 0; k < 4; k++) {
                    int nx = poll[0] + dirs[k];
                    int ny = poll[1] + dirs[k + 1];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                        continue;
                    }
                    if (grid[nx][ny] == 0) {
                        queue.offer(new int[]{nx, ny});
                        grid[nx][ny] = 1;
                    }
                }
            }
            step++;
        }
        return step - 1;
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 0, 1}, {0, 0, 0}, {1, 0, 1}};
        maxDistance(grid);
    }
}