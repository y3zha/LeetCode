package leetcode.interviewbook;

public class 面试题01_07_旋转矩阵 {

    /**
     * 先转秩，再列两两交换
     */

    public void rotate(int[][] mat) {
        //转秩
        int n = mat.length;
        int m = mat[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < m; j++) {   //矩阵转秩从对角线开始，不然就再换回来了
                int temp = mat[j][i];
                mat[j][i] = mat[i][j];
                mat[i][j] = temp;
            }
        }

        //列两两交换
        int s = 0, e = m - 1;
        while (s < e) {
            for (int i = 0; i < n; i++) {
                int temp = mat[i][s];
                mat[i][s] = mat[i][e];
                mat[i][e] = temp;
            }
            s++;
            e--;
        }
    }
}