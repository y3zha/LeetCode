package leetcode.interviewbook;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 面试题16_19_水域大小 {

    //bfs 由垂直、水平或对角连接的水域为池塘。那应该是八个方向

    int[][] land;
    int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    int n;
    int m;

    public int[] pondSizes(int[][] land) {
        this.land = land;
        this.n = land.length;
        this.m = land[0].length;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (land[i][j] == 0) {
                    bfs(i, j, list);
                }
            }
        }
        return list.stream().sorted().mapToInt(Integer::valueOf).toArray();
    }


    private void bfs(int i, int j, List<Integer> list) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        land[i][j] = 1;
        int count = 1;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            for (int k = 0; k < 8; k++) {
                int ni = poll[0] + dx[k];
                int nj = poll[1] + dy[k];
                if (ni >= 0 && ni < n && nj >= 0 && nj < m && land[ni][nj] == 0) {
                    land[ni][nj] = 1;
                    count++;
                    queue.offer(new int[]{ni, nj});
                }
            }
        }
        list.add(count);
    }

    public static void main(String[] args) {
        面试题16_19_水域大小 test = new 面试题16_19_水域大小();
        int[][] land = {{0, 2, 1, 0}, {0, 1, 0, 1}, {1, 1, 0, 1}, {0, 1, 0, 1}};
        test.pondSizes(land);
    }
}