package leetcode.coding;

import java.util.LinkedList;
import java.util.Queue;

public class _542_01矩阵 {

    //bfs
    public int[][] updateMatrix(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int[][] res = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 0) {
                    continue;
                }
                int dis = bfs(mat, i, j);
                res[i][j] = dis;
            }
        }
        return res;
    }

    int[] dirs = {-1, 0, 1, 0, -1};

    private int bfs(int[][] mat, int i, int j) {
        int steps = 0;
        int n = mat.length;
        int m = mat[0].length;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        while (!queue.isEmpty()) {
            steps++;
            int size = queue.size();
            for (int _i = 0; _i < size; _i++) {
                int[] poll = queue.poll();
                for (int k = 0; k < 4; k++) {
                    int nx = poll[0] + dirs[k];
                    int ny = poll[1] + dirs[k + 1];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                        continue;
                    }
                    if (mat[nx][ny] != 0) {
                        queue.offer(new int[]{nx, ny});
                    } else {
                        return steps;
                    }
                }
            }
        }
        return steps;
    }
}