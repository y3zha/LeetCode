package leetcode.coding;

import java.util.PriorityQueue;

public class _378_有序矩阵中第K小的元素 {

    public int kthSmallest(int[][] matrix, int k) {

        int n = matrix.length;
        int m = matrix[0].length;

        PriorityQueue<int[]> pq = new PriorityQueue<>(k, (o1, o2) -> o1[2] - o2[2]);
        boolean[][] vis = new boolean[n][m];

        // 第一行放进群
        for (int j = 0; j < m; j++) {
            pq.offer(new int[]{0, j, matrix[0][j]});
            vis[0][j] = true;
        }
        // 第一列放进去
        for (int i = 1; i < n; i++) {
            pq.offer(new int[]{i, 0, matrix[i][0]});
            vis[i][0] = true;
        }

        int[] dx = {1, 0};
        int[] dy = {0, 1};
        // poll
        for (int i = 0; i < k - 1; i++) {
            int[] poll = pq.poll();
            for (int j = 0; j < 2; j++) {
                int nx = poll[0] + dx[j];
                int ny = poll[1] + dy[j];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !vis[nx][ny]) {
                    vis[nx][ny] = true;
                    pq.offer(new int[]{nx, ny, matrix[nx][ny]});
                }
            }
        }
        return pq.peek() != null ? pq.peek()[2] : -1;
    }
}