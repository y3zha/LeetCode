package leetcode.coding;

import java.util.LinkedList;
import java.util.Queue;

public class _695_岛屿的最大面积 {

    //bfs模版题。。没意思的
    public int maxAreaOfIsland(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    res = Math.max(res, bfs(grid, i, j));
                }
            }
        }
        return res;
    }

    int[] dirs = {-1, 0, 1, 0, -1};

    private int bfs(int[][] grid, int i, int j) {
        grid[i][j] = 0;
        int n = grid.length;
        int m = grid[0].length;
        int cnt = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            for (int k = 0; k < 4; k++) {
                int nx = poll[0] + dirs[k];
                int ny = poll[1] + dirs[k + 1];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                if (grid[nx][ny] == 1) {
                    cnt++;
                    grid[nx][ny] = 0;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
        return cnt;
    }

    //再写个dfs版本,递归效率更快
    public int maxAreaOfIsland2(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    res = Math.max(res, dfs(grid, i, j));
                }
            }
        }
        return res;
    }

    private int dfs(int[][] grid, int i, int j) {
        //出口
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return 0;
        }
        int cnt = 1;
        grid[i][j] = 0;
        for (int k = 0; k < 4; k++) {
            int nx = i + dirs[k];
            int ny = j + dirs[k + 1];
            cnt += dfs(grid, nx, ny);
        }
        return cnt;
    }
}