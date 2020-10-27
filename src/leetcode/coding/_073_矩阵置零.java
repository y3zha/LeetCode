package leetcode.coding;

import java.util.HashSet;
import java.util.Set;

/**
 * 思路一: 用 O(m+n)额外空间
 * 两遍扫matrix,第一遍用集合记录哪些行,哪些列有0;第二遍置0
 * <p>
 * 思路二: 用O(1)空间
 * 关键思想: 用matrix第一行和第一列记录该行该列是否有0,作为标志位
 * 但是对于第一行,和第一列要设置一个标志位,为了防止自己这一行(一列)也有0的情况.注释写在代码里,直接看代码很好理解!
 */
public class _073_矩阵置零 {

    public void setZeroes(int[][] matrix) {
        Set<Integer> rowsSet = new HashSet<>();
        Set<Integer> colsSet = new HashSet<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    rowsSet.add(i);
                    colsSet.add(j);
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (rowsSet.contains(i) || colsSet.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

}