package leetcode.swordtooffer;

import java.util.LinkedList;
import java.util.Queue;

public class 面试题13_机器人的运动范围 {

    //1 <= n,m <= 100
    //0 <= k <= 20
    public int movingCount(int m, int n, int k) {
        int[] dirs = {-1, 0, 1, 0, -1};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;
        int count = 1;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = poll[0] + dirs[i];
                int ny = poll[1] + dirs[i + 1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && getSumOfDigits(nx,ny) <= k && !visited[nx][ny]) {
                    queue.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                    count++;
                }
            }
        }
        return count;
    }

    private int getSumOfDigits(int a, int b) {
        int sum = 0;
        while (a != 0) {
            sum += a % 10;
            a /= 10;
        }
        while (b != 0) {
            sum += b % 10;
            b /= 10;
        }
        return sum;
    }


}