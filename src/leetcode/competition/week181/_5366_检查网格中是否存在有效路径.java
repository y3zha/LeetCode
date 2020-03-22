package leetcode.competition.week181;

public class _5366_检查网格中是否存在有效路径 {

    //比赛时候写了很多if 把自己都写晕了，学一下别人的代码，这个pipe的思路很好，还是一个问题转化做得不好
    int n;
    int m;
    int[][] grid;
    //下右上左四个方向，0代表向下，1代表向右，2代表向上，3代表向左
    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};
    //定义管道pipe，pipe[3][2]=3表示第3块拼图通过向上走，它的出口是向左走这个方向。如果是-1代表不能走
    int[][] pipe = {
            {-1, -1, -1, -1},   //0号拼图
            {-1, 1, -1, 3},     //1号拼图
            {0, -1, 2, -1},     //2号拼图
            {-1, 0, 3, -1},     //依此类推
            {-1, -1, 1, 0},
            {3, 2, -1, -1},
            {1, -1, -1, 2},
    };


    public boolean hasValidPath(int[][] grid) {
        this.grid = grid;
        this.n = grid.length;
        this.m = grid[0].length;
        //从起点开始，看四个方向
        int s = grid[0][0];
        for (int i = 0; i < 4; i++) {
            if (pipe[s][i] != -1) {
                if (dfs(0, 0, pipe[s][i])) {
                    return true;
                }
            }
        }
        return false;
    }

    //dir是出口的方向
    private boolean dfs(int x, int y, int dir) {
        if (x == n - 1 && y == m - 1) {
            return true;
        }
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
            return false;
        }
        //获得下一块拼图的编号
        int id = grid[nx][ny];
        //看看走进去有没有出口，不能走返回false，否则就尝试走
        if (pipe[id][dir] == -1) {
            return false;
        } else {
            return dfs(nx, ny, pipe[id][dir]);
        }
    }


}