package leetcode.coding;

import java.util.LinkedList;
import java.util.Queue;

public class _733_图像渲染 {

    //模版题 bfs 效率不高 相同的还有695
    public int[][] floodFill2(int[][] image, int i, int j, int newColor) {
        if (image[i][j] == newColor) return image;  //要加个判断的
        int n = image.length;
        int m = image[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        int oldColor = image[i][j];
        image[i][j] = newColor;
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int _i = 0; _i < size; _i++) {
                int[] poll = queue.poll();
                for (int k = 0; k < 4; k++) {
                    int nx = poll[0] + dirs[k];
                    int ny = poll[1] + dirs[k + 1];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                        continue;
                    }
                    if (image[nx][ny] == oldColor) {
                        image[nx][ny] = newColor;
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
        }
        return image;
    }

    //dfs也能写，效率高一点
    int[] dirs = {-1, 0, 1, 0, -1};
    public int[][] floodFill(int[][] image, int i, int j, int newColor) {
        if (image[i][j] == newColor) {
            return image;
        }
        dfs(image, i, j, image[i][j], newColor);
        return image;
    }

    private void dfs(int[][] image, int i, int j, int oldColor, int newColor) {
        if (i < 0 || i >= image.length || j < 0 || j >= image[0].length || image[i][j] != oldColor) {
            return;
        }
        image[i][j] = newColor;
        for (int k = 0; k < 4; k++) {
            int nx = i + dirs[k];
            int ny = j + dirs[k + 1];
            dfs(image, nx, ny, oldColor, newColor);
        }
    }

}