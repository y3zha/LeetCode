package leetcode.coding;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 岛屿数量 BFS
 *
 * 思路一：遍历每一个位置，如果是岛屿，则bfs，把岛屿上的每个1变为0
 *
 * 思路二：并查集，首先统计有多少个1，假设一开始都是独立的岛屿，然后进行union即可
 */
public class _200_岛屿数量 {
    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    //bfs把这个岛屿上所有1变为0
                    bfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void bfs(char[][] grid, int x, int y) {
        //四个方向
        int[] xAxis = {0, 0, -1, 1};
        int[] yAxis = {1, -1, 0, 0};

        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(x, y));

        while (!queue.isEmpty()) {
            Point poll = queue.poll();
            //遍历四个方向
            for (int i = 0; i < 4; i++) {
                int adjX = poll.x + xAxis[i];
                int adjY = poll.y + yAxis[i];

                if (isValid(grid, adjX, adjY) && grid[adjX][adjY] == '1') {
                    grid[adjX][adjY] = '0';
                    queue.offer(new Point(adjX, adjY));
                }
            }
        }
    }

    private boolean isValid(char[][] grid, int x, int y) {
        int rows = grid.length;
        int clos = grid[0].length;
        if (x >= 0 && x < rows && y >= 0 && y < clos) {
            return true;
        } else {
            return false;
        }
    }

    //写法二：并查集
    class UnionFind{
        private int[] father;
        private int count;

        //init
        public UnionFind(int n) {
            father = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                father[i] = i;
            }
        }

        //find操作，采用压缩路径的写法
        public int find(int x) {
            if (father[x] == x) {
                return x;
            }
            return father[x] = find(father[x]);
        }

        //union操作
        public void union(int a, int b) {
            int root_a = find(a);
            int root_b = find(b);
            if (root_a != root_b) {
                father[root_a] = root_b;    //a->b
                count--;    //能够合并，岛屿数量-1
            }
        }

        //查询岛屿数量
        public int query() {
            return count;
        }

        //设置初始岛屿数量
        public void setCount(int count) {
            this.count = count;
        }
    }

    public int numIslands2(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int n = grid.length;
        int m = grid[0].length;
        if (n == 0 || m == 0) {
            return 0;
        }
        int islands = 0;
        //统计有多少个1，然后后面再union
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    islands++;
                }
            }
        }
        UnionFind unionFind = new UnionFind(n * m);
        unionFind.setCount(islands);

        int[] dirs = {-1, 0, 1, 0, -1};
        //遍历所有点
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //如果是岛屿，用1表示的，题目中用true表示岛屿
                if (grid[i][j] == '1') {
                    //查看它四个方向
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dirs[k];
                        int ny = j + dirs[k + 1];
                        //如果坐标合法且也是岛屿，连接一下
                        if (nx < 0 || nx >= n || ny < 0 || ny >= m || grid[nx][ny] == '0') {
                            continue;
                        }
                        // if (isVaild(n,m, nx, ny) && grid[nx][ny] == '1') {
                            //它们在father数组中的坐标，index从1开始
                            unionFind.union(i * m + j, nx * m + ny);
                        // }
                    }
                }
            }
        }
        return unionFind.query();
    }
}