package leetcode.coding;

public class _999_车的可用捕获量 {

    // 上下左右
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    //碰到B停下来，碰到P停下来，结果 + 1
    public int numRookCaptures(char[][] board) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 'R') {
                    int res = 0;
                    for (int k = 0; k < 4; k++) {
                        // 传入方向
                        if (check(board, i, j, k)) {
                            res++;
                        }
                    }
                    return res;
                }
            }
        }
        return 0;
    }

    //k 代表某一方向
    private boolean check(char[][] board, int x, int y, int k) {
        //只往某一方向前进
        while (true) {
            //越界就停下
            if (x < 0 || x >= 8 || y < 0 || y >= 8) {
                break;
            }
            //碰到'B'就停下
            if (board[x][y] == 'B') {
                return false;
            }
            // 碰到'P'，
            if (board[x][y] == 'p') {
                return true;
            }
            //如果是'.'就继续走
            x = x + dx[k];
            y = y + dy[k];
        }
        return false;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', 'p', '.', '.', '.', '.'},
                {'.', '.', '.', 'R', '.', '.', '.', 'p'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', 'p', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'}};
        _999_车的可用捕获量 solution = new _999_车的可用捕获量();
        int res = solution.numRookCaptures(board);
        System.out.println(res);
    }

}