package leetcode.interviewbook;

import java.util.LinkedList;
import java.util.Queue;

public class 面试题08_10_颜色填充 {

    //bfs写法
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int n = image.length;
        int m = image[0].length;
        int oldColor = image[sr][sc];
        if (oldColor == newColor) return image;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc});
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            image[poll[0]][poll[1]] = newColor;
            for (int i = 0; i < 4; i++) {
                int nx = poll[0] + dirs[i];
                int ny = poll[1] + dirs[i + 1];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m|| image[nx][ny] != oldColor) {
                    continue;
                }
                image[nx][ny] = newColor;
                queue.offer(new int[]{nx, ny});
            }
        }
        return image;
    }

    //dfs写法

    int[] dirs = {-1, 0, 1, 0, -1};
    public int[][] floodFill2(int[][] image, int sr, int sc, int newColor) {
        if (image[sr][sc] == newColor) {
            return image;
        }
        dfs(image, sr, sc, image[sr][sc], newColor);
        return image;
    }

    private void dfs(int[][] image, int x, int y, int oldColor, int newColor) {
        if (x < 0 || x >= image.length || y < 0 || y >= image[0].length || image[x][y] != oldColor) {
            return;
        }
        image[x][y] = newColor;
        for (int i = 0; i < 4; i++) {
            int nx = x + dirs[i];
            int ny = y + dirs[i + 1];
            dfs(image, nx, ny, oldColor, newColor);
        }
    }

}