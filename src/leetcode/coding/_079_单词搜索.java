package leetcode.coding;

/**
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 */
public class _079_单词搜索 {

    char[][] board;
    String word;
    int[] dirs = {-1, 0, 1, 0, -1}; // 方向数组
    boolean[][] visited;    //需要保证每个位置不被重复访问 比如[["a","a"]] "aaa"这种用例

    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.word = word;
        int n = board.length;
        int m = board[0].length;
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //dfs每一个起点,0为word的起点,只爱有一处存在，就返回true
                if (dfs(i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 题中有个测试用例
     * [["a","a"]]
     *  "aaa"
     *  我的输出结果是true，这是不对的，我们需要添加每个位置的visited数组，保证每个位置不被重复访问。
     */
    private boolean dfs(int x, int y, int start) {
        if (start == word.length() - 1) {
            return board[x][y] == word.charAt(start);
        }
        if (board[x][y] == word.charAt(start)) {
            visited[x][y] = true;
            for (int i = 0; i < 4; i++) {
                int nx = x + dirs[i];
                int ny = y + dirs[i + 1];
                if (isVaild(nx, ny, board) && !visited[nx][ny]) {
                    //return dfs(nx, ny, start + 1);  //这是错误的写法，题目的意思是：只要你的搜索返回 True 才返回，如果全部的格子都搜索完了以后，都返回 False ，才返回 False。
                    if (dfs(nx, ny, start + 1)) {
                        return true;
                    }
                }
            }
            visited[x][y] = false;
        }
        return false;
    }

    private boolean isVaild(int x, int y, char[][] board) {
        int n = board.length;
        int m = board[0].length;
        if (x >= 0 && x < n && y >= 0 && y < m) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        _079_单词搜索 test = new _079_单词搜索();
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";

        char[][] board2 = {{'a', 'b'}};
        String word2 = "ba";
        test.exist(board2, word2);

    }
}