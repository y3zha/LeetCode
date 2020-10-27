package leetcode.coding;

import java.util.LinkedList;
import java.util.Queue;

// 不仅仅要能够到达目的地，还要在目的地停下来
// 和其他bfs不一样的是要到墙才停下来
// 因为可能这个位置会被反复访问，这样就会陷入死循环，所以要用个vis数组来记录
public class _490_迷宫 {

    public boolean hasPath(int[][] maze, int[] start, int[] dest) {
        int n = maze.length;
        int m = maze[0].length;
        // 队列中放的是停下来的点，而不是在中间半路上的
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        boolean[][] vis = new boolean[n][m];
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            // 如果这个停下来的点是终点，那就直接返回true
            if (poll[0] == dest[0] && poll[1] == dest[1]) {
                return true;
            }
            // 否则就判断四个方向
            for (int k = 0; k < 4; k++) {
                int x = poll[0], y = poll[1];
                // 找一个方向一直滚动，直到碰到墙
                while (x >= 0 && x < n && y >= 0 && y < m && maze[x][y] == 0) {
                    x += dirs[k];
                    y += dirs[k = 1];
                }
                // 此时不符合条件退出，要往回推一格，到正确位置
                x -= dirs[k];
                y -= dirs[k + 1];
                if (!vis[x][y]) {
                    vis[x][y] = true;
                    queue.offer(new int[]{x, y});
                }
            }
        }
        return false;
    }
}