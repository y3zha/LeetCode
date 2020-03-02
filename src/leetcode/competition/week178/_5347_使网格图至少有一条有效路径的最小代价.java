package leetcode.competition.week178;

import java.util.*;

/**
 * 讲道理，作为一个转行狗，板子不练就生，比赛时候看到也卡半天，菜是原罪，今天好好练下板子
 * 在箭头上走是没有代价的
 * 写法一：bfs
 * 写法一：dijkstra
 * https://zybuluo.com/juruo/note/1535384 非常好的学习的地方
 */
public class _5347_使网格图至少有一条有效路径的最小代价 {

    //bfs 模版题了
    public int minCost(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] f = new int[n][m];
        for (int[] ints : f) {
            Arrays.fill(ints, 0x3f3f3f3f);
        }
        f[0][0] = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0], y = poll[1];
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    int cost = f[x][y] + (grid[x][y] == i + 1? 0 : 1); //这里一定要加括号
                    if (f[nx][ny] > cost) {
                        f[nx][ny] = cost;
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
        }
        return f[n - 1][m - 1];
    }

    //dijkstra
    public int minCost2(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        int[][] dist = new int[n][m];
        for (int[] ints : dist) {
            Arrays.fill(ints, 0x3f3f3f3f);
        }
        dist[0][0] = 0;
        boolean[][] vis = new boolean[n][m];
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] a,int[] b){
                return a[2] - b[2]; //按照cost升序
            }
        });
        pq.offer(new int[]{0, 0, 0});
        while (!pq.isEmpty()) {
            int[] poll = pq.poll();
            int x = poll[0], y = poll[1];
            if (vis[x][y]) {
                continue;
            }
            vis[x][y] = true;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    int cost = poll[2] + (grid[x][y] == i + 1 ? 0 : 1);
                    if (cost < dist[nx][ny]) {
                        dist[nx][ny] = cost;
                        pq.offer(new int[]{nx, ny, cost});
                    }
                }
            }
        }
        return dist[n - 1][m - 1];
    }

    /**
     * 本题的最佳思路是 01-bfs
     * 什么是01-bfs呢？
     * 原来的bfs是，层次便利是按照一层层的升序的，一大堆的数 比如 x x x x，然后x+1 x+1...
     * 总体上来说就是 x x x x (x+1) (x+1) (x+1) (x+2) (x+2) (x+2) (x+2) 这样子的，它的权值不为0
     * 01bfs就是权值只有0或者1，那么就可能出现这样的情况了
     * 队列中的数字是这样的 x x x (x+1) (x+1) x (x+1) x (x+1)
     * 这样拿出来的时候，就不是我们预期的了，但实际上我们要拿的是最小的那个，而不是较大的。
     *
     * 解决思路就是用 Deque，如果新拓展出的节点的权值和之前节点的权值是一样的，那我们就要把它放在队头，否则放在队尾
     * 这样我们每次在队头取的时候，就能够保证拿到的是最小的
     *
     * 而且时间复杂度是最低的
     */
    public int minCost3(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] f = new int[n][m];
        for (int[] ints : f) {
            Arrays.fill(ints, 0x3f3f3f3f);
        }
        f[0][0] = 0;
        boolean[][] vis = new boolean[n][m];
        Deque<int[]> deque = new ArrayDeque<>();
        deque.offer(new int[]{0, 0});
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        while (!deque.isEmpty()) {
            int[] poll = deque.pollFirst();
            int x = poll[0], y = poll[1];
            if (vis[x][y]) {
                continue;
            }
            vis[x][y] = true;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    int cost = f[x][y] + (grid[x][y] == i + 1? 0 : 1); //这里一定要加括号
                    if (f[nx][ny] > cost) {
                        f[nx][ny] = cost;
                        if (f[nx][ny] == f[x][y]) {
                            deque.offerFirst(new int[]{nx, ny});
                        } else {
                            deque.offerLast(new int[]{nx, ny});
                        }
                    }
                }
            }
        }
        return f[n - 1][m - 1];
    }

}