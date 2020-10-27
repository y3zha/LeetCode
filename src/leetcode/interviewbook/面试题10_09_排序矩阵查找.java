package leetcode.interviewbook;

public class 面试题10_09_排序矩阵查找 {

    public boolean searchMatrix(int[][] mat, int t) {
        if (mat == null || mat.length == 0) {
            return false;
        }
        int n = mat.length;
        int m = mat[0].length;
        int i = 0;
        int j = m - 1;
        while (i < n && j >= 0) {
            if (mat[i][j] == t) {
                return true;
            } else if (mat[i][j] > t) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }
}