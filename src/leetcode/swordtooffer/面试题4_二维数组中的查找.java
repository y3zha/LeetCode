package leetcode.swordtooffer;

public class 面试题4_二维数组中的查找 {

    /**
     * 每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
     * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     * 思路：双指针，从左上角开始
     */
    public boolean findNumberIn2DArray(int[][] mat, int target) {
        if (mat.length == 0) {
            return false;
        }
        int n = mat.length;
        int m = mat[0].length;
        int i = 0, j = m - 1;
        while (i < n && j >= 0) {
            if (mat[i][j] == target) {
                return true;
            }
            if (mat[i][j] > target) {
                j--;
                continue;
            }
            if (mat[i][j] < target) {
                i++;
                continue;
            }
        }
        return false;
    }
}