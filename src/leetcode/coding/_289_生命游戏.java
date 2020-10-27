package leetcode.coding;

public class _289_生命游戏 {

    // 1 代表细胞活的， 0 代表细胞死的
    // 那么这个位置就四种状态呀
    // 状态1： 00，死的，下一轮还是死的
    // 状态2： 01，死的，下一轮活了
    // 状态3： 10，活的，下一轮死了
    // 状态4： 11，活的，下一轮继续活着
    //
    // 下一轮活的可能有两种，也就是要把单元格变为1
    // 1、这个活细胞周围八个位置有两个或三个活细胞，下一轮继续活，属于 11
    // 2、这个细胞本来死的，周围有三个活着的，下一轮复活了，属于 01
    // 那遍历下每个格子看他周围细胞有多少个活细胞就行了，然后更改为状态
    // 那么对于第一种可能，把board[i][j]设置为3，对于第二种可能状态设置为2
    // 这样设置后，我去遍历后面的格子，就能拿到与他相邻的格子的前一个状态是活着的还是死了的 与一下即可
    // 这样最后我们只要把所有格子 除以2，留下来的1都是活着的，其他就都是死的

    // 定义八个方向
    int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
    int[][] board;
    int m, n;

    public void gameOfLife(int[][] board) {
        this.board = board;
        // 特判
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) return;
        this.m = board.length;
        this.n = board[0].length;
        // 遍历
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 拿到当前位置周围活细胞数量
                int cnt = countAlive(i, j);
                // 1. 活细胞周围八个位置有两个或三个活细胞，下一轮继续活
                if (board[i][j] == 1 && (cnt == 2 || cnt == 3)) board[i][j] = 3;
                // 2. 细胞本来死的，周围有三个活着的，下一轮复活了
                if (board[i][j] == 0 && cnt == 3) board[i][j] = 2;
            }
        }

        // 更新结果
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 右移 1 就是除以 2
                board[i][j] >>= 1;
            }
        }
    }

    private int countAlive(int x, int y) {
        int cnt = 0;
        for (int k = 0; k < 8; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
            // 如果这个位置为 0，代表是死的，之前死或者之后死都不会算进去
            // 如果这个位置为 1，代表是是活得，并且是还没遍历到，需要算进去
            // 如果这个位置为 2，代表是是活得，修改过了，并且之前是死的，不需要算进去
            // 如果这个位置为 3，代表是是活得，修改过了，并且之前是活的，需要算进去
            cnt += (board[nx][ny] & 1);
        }
        return cnt;
    }


    public static void main(String[] args) {
        _289_生命游戏 test = new _289_生命游戏();
        int[][] board = {{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
        test.gameOfLife(board);
    }
}