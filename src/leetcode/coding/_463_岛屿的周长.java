package leetcode.coding;

public class _463_岛屿的周长 {

    //一个巧妙的办法，岛屿内没有湖,所以只需要求出 上面(或下面) + 左面(或右面)的长度再乘2即可,小学老师教算周长的那种算法，
    public int islandPerimeter(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int half = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //如果是岛屿
                if (grid[i][j] == 1) {
                    //上面
                    if (i == 0 || grid[i - 1][j] == 0) {
                        half += 1;
                    }
                    //左面
                    if (j == 0 || grid[i][j - 1] == 0) {
                        half += 1;
                    }
                }
            }
        }
        return half * 2;
    }
}