package leetcode.swordtooffer;

/**
 * 就是 79 题 单词搜索
 * 从每个位置dfs即可
 */
public class 面试题12_矩阵中的路径 {

    char[][] board;
    String word;
    boolean[][] visited;    //需要保证每个位置不被重复访问 比如[["a","a"]] "aaa"这种用例

    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.word = word;
        int n = board.length;
        int m = board[0].length;
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dfs(i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    int[] dirs = new int[]{-1, 0, 1, 0, -1};

    private boolean dfs(int x, int y, int s) {
        if (s == word.length() - 1) {
            return board[x][y] == word.charAt(s);
        }
        //如果当前位置和word的当前字符一致再去搜索
        if (board[x][y] == word.charAt(s)) {
            visited[x][y] = true;
            //搜索四个方向
            for (int i = 0; i < 4; i++) {
                int nx = x + dirs[i];
                int ny = y + dirs[i + 1];
                if (nx >= 0 && nx < board.length && ny >= 0 && ny < board[0].length && !visited[nx][ny]) {
                    //return dfs(nx, ny, start + 1);  //这是错误的写法，题目的意思是：只要你的搜索返回 True 才返回，如果全部的格子都搜索完了以后，都返回 False ，才返回 False。
                    if (dfs(nx, ny, s + 1)) {
                        return true;
                    }
                }
            }
            //四个方向搜索不到则状态重置
            visited[x][y] = false;
        }
        return false;
    }
}