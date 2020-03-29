package leetcode.coding;

/**
 * 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
 *
 * 方法一：使用和螺旋数组I中相同的方法，记录四个边界
 */
public class _059_螺旋矩阵II {

    //方法一
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int up = 0, down = n - 1, left = 0, right = n - 1;
        int val = 1;
        while (up <= down && left <= right) {
            for (int i = left; i <= right; i++) {
                res[up][i] = val;
                val++;
            }
            up++;
            for (int i = up; i <= down; i++) {
                res[i][right] = val;
                val++;
            }
            right--;
            if (up <= down && left <= right) {
                for (int i = right; i >= left; i--) {
                    res[down][i] = val;
                    val++;
                }
                down--;
                for (int i = down; i >= up; i--) {
                    res[i][left] = val;
                    val++;
                }
                left++;
            }
        }
        return res;
    }
}