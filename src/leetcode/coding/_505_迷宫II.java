package leetcode.coding;
// https://www.cnblogs.com/grandyang/p/6725380.html#4511214
// 在上一个题的基础上要找出最短距离
// 可以使用 dijkstra 来解决单源最短路，迷宫里每个格子都是一个节点，边权为 1，没有负权边，可以用dij，在这里用堆优化写
// 用一个二维数组 dist[i][j]表示从起点到这个位置的最短距离（步数），在后面遍历过程中不断的去更新

// BFS和dijkstra，比较重要的是
// BFS 算法是将未访问的邻居压入队列，然后再将未访问邻居的未访问过的邻居入队列再依次访问
// Dijkstra 算法是在剩余的未访问过的结点中找出权重最小的并访问，这就是为什么要用一个优先队列（最小堆）来代替普通的 queue
// 这样就能尽量先更新离起点近的位置的 dp 值，优先队列里同时也存了该点到起点的距离，这个距离不一定是最短距离，可能还能松弛

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class _505_迷宫II {

    // bfs 但感觉不是dij
    public int shortestDistance(int[][] maze, int[] start, int[] dest) {
        int n = maze.length;
        int m = maze[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        //Queue<int[]> queue = new LinkedList<>();
        // 用优先队列反而比用队列慢
        pq.offer(new int[]{start[0], start[1], 0});
        int[][] dist = new int[n][m];
        for (int[] arr : dist) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!pq.isEmpty()) {
            int[] poll = pq.poll();
            // // 小于等于
            // if (dist[poll[0]][poll[1]] <= poll[2]) {
            //     continue;
            // }
            // // 否则可以更新
            // dist[poll[0]][poll[1]] = poll[2];
            for (int k = 0; k < 4; k++) {
                int x = poll[0], y = poll[1], distance = poll[2];
                while (x >= 0 && x < n && y >= 0 && y < m && maze[x][y] == 0) {
                    x += dirs[k];
                    y += dirs[k + 1];
                    distance++;
                }
                x -= dirs[k];
                y -= dirs[k + 1];
                distance--;
                if (dist[x][y] > distance) {
                    dist[x][y] = distance;
                    pq.offer(new int[]{x, y, distance});
                }
            }
        }
        return dist[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : dist[dest[0]][dest[1]];
    }

    // dfs
    public int shortestDistance2(int[][] maze, int[] start, int[] dest) {
        int n = maze.length;
        int m = maze[0].length;
        int[][] dist = new int[n][m];
        for (int[] arr : dist) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }
        dist[start[0]][start[1]] = 0;
        helper(maze, start[0], start[1], dist, dest);
        return dist[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : dist[dest[0]][dest[1]];
    }

    int[] dirs = {-1, 0, 1, 0, -1};

    private void helper(int[][] maze, int x, int y, int[][] dist, int[] dest) {
        if (x == dest[0] && y == dest[1]) {
            return;
        }
        int n = maze.length;
        int m = maze[0].length;
        for (int k = 0; k < 4; k++) {
            int nx = x, ny = y;
            int distance = dist[nx][ny];
            while (nx >= 0 && nx < n && ny >= 0 && ny < m && maze[nx][ny] == 0) {
                nx += dirs[k];
                ny += dirs[k + 1];
                distance++;
            }
            nx -= dirs[k];
            ny -= dirs[k + 1];
            distance--;
            if (distance < dist[nx][ny]) {
                dist[nx][ny] = distance;
                helper(maze, nx, ny, dist, dest);
            }
        }
    }


}