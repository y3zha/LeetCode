package leetcode.coding;

import jdk.nashorn.internal.ir.annotations.Ignore;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 陆地：0
 * 水域：1
 *
 * 岛屿：从一块陆地出发，每次可以往上下左右 4 个方向相邻区域走，能走到的所有陆地区域，我们将其称为一座「岛屿」。
 * 【也就是说，不能在矩形的边界上，只要有一块在边界上都不行】
 * 如果一座岛屿 完全 由水域包围，即陆地边缘上下左右所有相邻区域都是水域，那么我们将其称为 「封闭岛屿」。
 * 请返回封闭岛屿的数目。
 *
 * 思路：从陆地出发，检查它是否被水域包围
 *
 * 从当前陆地开始出发，如果能走出边界就说明该陆地所在岛屿不是封闭岛屿，返回封闭岛屿个数0
 * 如果碰到水域(值为1的点)就返回封闭岛屿个数1，表示该岛屿可能就是一个封闭岛屿
 * 如果碰到陆地(值为0的点)就继续向该陆地的四个方向遍历，同时将该陆地标记为1，表示这个位置已经遍历过了。
 *
 * DFS/BFS
 * Flood Fill着色
 */
public class _1254_统计封闭岛屿的数目 {

    //dfs和bfs两种方法
    public static int closedIsland(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    res += dfs(grid, i, j); //dfs
                    // res += bfs(grid, i, j); //bfs
                }
            }
        }
        return res;
    }

    static int[] dirs = {-1, 0, 1, 0, -1};
    private static int dfs(int[][] grid, int x, int y) {
        //从当前陆地开始出发，如果能走出边界就说明该陆地所在岛屿不是封闭岛屿，返回封闭岛屿个数0
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
            return 0;
        }
        //如果碰到水域(值为1的点)就返回封闭岛屿个数1，表示该岛屿可能就是一个封闭岛屿
        if (grid[x][y] == 1) {
            return 1;
        }
        grid[x][y] = 1; //标记为访问过
        int res = 1;
        for (int i = 0; i < 4; i++) {
            int nx = x + dirs[i];
            int ny = y + dirs[i + 1];
            //只要有一个返回0，就说明有一个方向走出了边界，那该位置所在岛屿就不是封闭岛屿，即使其它三个方向都返回1
            res = Math.min(res, dfs(grid, nx, ny));
        }
        return res;
    }

    /**
     * bfs思路：先将当前陆地放入队列中，bfs四个方向，碰到陆地也放入队列中，一直循环直到队列为空
     * 在循环过程中判断有没有出界，如果出界了，那就说明该岛屿不是封闭岛屿
     */
    private static int bfs(int[][] grid, int i, int j) {
        int res = 0;
        int n = grid.length;
        int m = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            //标记为访问过
            grid[poll[0]][poll[1]] = 1;
            //遍历四个方向
            for (int k = 0; k < 4; k++) {
                int nx = poll[0] + dirs[i];
                int ny = poll[1] + dirs[i + 1];
                //如果出界了，res = 0
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    res = 0;
                    continue;
                }
                //判断下是不是陆地，是的话放入
                if (grid[nx][ny] == 0) {
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
        return res;
    }

}