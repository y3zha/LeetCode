package leetcode.coding;

public class _980_不同路径III {

    /*
    在二维网格 grid 上，有 4 种类型的方格：
    1 表示起始方格。且只有一个起始方格。
    2 表示结束方格，且只有一个结束方格。
    0 表示我们可以走过的空方格。
    -1 表示我们无法跨越的障碍。
    返回在四个方向（上、下、左、右）上行走时，从起始方格到结束方格的不同路径的数目，每一个无障碍方格都要通过一次。

    每个无障碍都要通过一次，可以先统计无障碍方格的个数

     */

    int sr, sc; //start row， start col
    int er, ec; //end row，end col
    int res;
    int[][] grid;
    int n, m;

    public int uniquePathsIII(int[][] grid) {
        res = 0;
        this.grid = grid;
        this.n = grid.length;
        this.m = grid[0].length;
        int obstacle = 0; //障碍个数
        int noObstacle = 0; //无障碍个数
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == -1) obstacle++;
                if (grid[i][j] == 1) {
                    sr = i;
                    sc = j;
                }
                if (grid[i][j] == 2) {
                    er = i;
                    ec = j;
                }
            }
        }
        noObstacle = n * m - obstacle;
        dfs(sr, sc, noObstacle);
        return res;
    }

    int[] dirs = {-1, 0, 1, 0, -1};

    private void dfs(int x, int y, int noObstacle) {
        noObstacle--;
        if (noObstacle < 0) {
            return;
        }
        //看下是不是到了终点
        if (x == er && y == ec) {
            if (noObstacle == 0) {
                res++;
            }
            return;
        }

        //只能走一次，标记为访问过
        int temp = grid[x][y];
        grid[x][y] = 3;
        for (int k = 0; k < 4; k++) {
            int nx = x + dirs[k];
            int ny = y + dirs[k + 1];
            if (nx < 0 || nx >= n || ny < 0 || ny >= m || grid[nx][ny] == -1) {
                continue;
            }
            if (grid[nx][ny] == 0 || grid[nx][ny] == 2) {
                dfs(nx, ny, noObstacle);
            }
        }
        //状态重置
        grid[x][y] = temp;
    }
}