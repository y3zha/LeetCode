package leetcode.coding;

import java.util.LinkedList;
import java.util.Queue;

public class _994_腐烂的橘子 {
    class Point{
        int x;
        int y;
        int val;

        public Point(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }

    public int orangesRotting(int[][] grid) {
        int fresh = 0;  //首先统计下有多少的新鲜橘子
        int n = grid.length;
        int m = grid[0].length;
        Queue<Point> queue = new LinkedList<>();
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new Point(i, j, 2));
                }
                if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }
        int steps = 0;
        //如果0分钟没有新鲜橘子，直接返回0
        if (fresh == 0) {
            return steps;
        }
        while (!queue.isEmpty()) {
            steps++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point p = queue.poll();
                for (int k = 0; k < 4; k++) {
                    int nx = p.x + dirs[k];
                    int ny = p.y + dirs[k + 1];
                    if (nx >= 0 && nx < n && ny >= 0 && ny < m && grid[nx][ny] == 1) {
                        grid[nx][ny] = 2;
                        queue.offer(new Point(nx, ny, 2));
                        fresh--;
                        if (fresh == 0) {
                            return steps;
                        }
                    }
                }
            }
        }
        return -1;
    }
}