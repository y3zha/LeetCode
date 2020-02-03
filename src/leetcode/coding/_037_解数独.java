package leetcode.coding;

/**
 * 编写一个程序，通过已填充的空格来解决数独问题。
 *
 * 用bool数组来表示行/列中某个位置是否被占用了，被占用就回溯，直到数组被填充完成
 */
public class _037_解数独 {

    public void solveSudoku(char[][] board) {
        boolean[][] rowVisited = new boolean[9][10]; //rowVisited[i][j]表示第i行第j个元素是否在了
        boolean[][] colVisited = new boolean[9][10]; //colVisited[i][j]表示第i列第j个元素是否在了
        boolean[][] boxVisited = new boolean[9][10]; //boxVisited[i][j]表示第i个小个子的第j个元素是否在了

        //首先初始化
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                int num = board[i][j] - '0';
                rowVisited[i][num] = true;
                colVisited[j][num] = true;
                int k = i / 3 * 3 + j / 3;  //第几个格子 0～8
                boxVisited[k][num] = true;
            }
        }
        backtrack(board, rowVisited, colVisited, boxVisited, 0, 0);
    }

    private boolean backtrack(char[][] board, boolean[][] rowVisited, boolean[][] colVisited, boolean[][] boxVisited, int row, int col) {
        //结束情况，如果这一行填充结束了，也就是col走到底了，需要重置col,row也要++
        if (col == board[0].length) {
            col = 0;
            row++;
            if (row == board.length) {
                return true;
            }
        }
        //是空的位置才需要填充,否则直接看下一个位置
        if (board[row][col] == '.') {
            for (int num = 1; num <= 9; num++) {
                int k = row / 3 * 3 + col / 3;  //格子下标
                if (!rowVisited[row][num] && !colVisited[col][num] && !boxVisited[k][num]) {
                    rowVisited[row][num] = colVisited[col][num] = boxVisited[k][num] = true;
                    board[row][col] = (char) (num + '0');
                    // backtrack(board, rowVisited, colVisited, boxVisited, row, col + 1);
                    //如果回溯成功了，直接返回true即可！，所以不能直接写上面那句！回溯成功，下面的就不用执行了
                    if (backtrack(board, rowVisited, colVisited, boxVisited, row, col + 1)) {
                        return true;
                    }
                    rowVisited[row][num] = colVisited[col][num] = boxVisited[k][num] = false;
                    board[row][col] = '.';
                }
            }
        } else {
            return backtrack(board, rowVisited, colVisited, boxVisited, row, col + 1);
        }
        return false;
    }
}