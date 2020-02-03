package leetcode.coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 根据题意，就是每一行不能有数字出现两次，每一列不能有数字出现两次，每个小方块中数字不能出现两次
 *
 * 难点在于，如何检查9个格子？box[i][j]表示第i个小格子，然后格子里一共有9个数，我们要找第j位，如果这个位子上是空的，那么就是ok的
 */
public class _036_有效的数独 {

    public boolean isValidSudoku(char[][] board) {
        boolean[][] arr1 = new boolean[9][9];
        boolean[][] arr2 = new boolean[9][9];
        boolean[][] arr3 = new boolean[9][9];

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int index = row / 3 * 3 + col / 3;  //第几个格子
                if (board[row][col] != '.') {
                    int temp = board[row][col] - '1';
                    //枚举的每一列
                    if (arr1[col][temp]) {
                        return false;
                    } else {
                        arr1[col][temp] = true;
                    }
                    //枚举每一行
                    if (arr2[row][temp]) {
                        return false;
                    } else {
                        arr2[row][temp] = true;
                    }
                    //枚举每个小box
                    if (arr3[index][temp]) {
                        return false;
                    } else {
                        arr3[index][temp] = true;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 使用set，两个for循环搞定
     */
    public boolean isValidSudoku2(char[][] board) {
        Set<String> set = new HashSet();
        for (int i=0; i<9; ++i) {
            for (int j=0; j<9; ++j) {
                char number = board[i][j];
                if (number != '.')
                    if (!set.add(number + " in row " + i) ||
                            !set.add(number + " in column " + j) ||
                            !set.add(number + " in block " + i/3 + "-" + j/3))
                        return false;
            }
        }
        return true;
    }
}