package leetcode.coding;

/**
 * 判断斜杠能划分多少个区域
 *
 * 一个巧妙的思路：https://leetcode-cn.com/problems/regions-cut-by-slashes/solution/mei-ge-xiao-ge-fen-jie-wei-3-3-fang-ge-qiu-lian-to/
 *
 * 首先将原来n*n方格转为3n * 3n 方格。然后求0的连通量个数。
 *
 * 并查集
 */
public class _959_由斜杠划分区域 {

    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        int[][] mat = new int[3 * n][3 * n];

        //统计有多少个1，0的数量就是所有数量-1的数量
        int ones = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i].charAt(j) == '/') {
                    mat[i * 3][j * 3 + 2] = 1;
                    mat[i * 3 + 1][j * 3 + 1] = 1;
                    mat[i * 3 + 2][j * 3] = 1;
                    ones += 3;
                } else if (grid[i].charAt(j) == '\\') {
                    mat[i * 3][j * 3] = 1;
                    mat[i * 3 + 1][j * 3 + 1] = 1;
                    mat[i * 3 + 2][j * 3 + 2] = 1;
                    ones += 3;
                }
            }
        }

        // for (int i = 0; i < mat.length; i++) {
        //     for (int j = 0; j < mat[0].length; j++) {
        //         System.out.print(mat[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        UnionFind uf = new UnionFind(3 * n * 3 * n + 1);
        //这就是一开始0的数量，可以理解为是独立岛屿的数量
        uf.count = 3 * n * 3 * n - ones;
        //遍历这个图
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int i = 0; i < 3 * n; i++) {
            for (int j = 0; j < 3 * n; j++) {
                //如果是0，遍历它的四个方向
                if (mat[i][j] == 0) {
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dirs[k];
                        int ny = j + dirs[k + 1];
                        if (nx < 0 || nx >= 3 * n || ny < 0 || ny >= 3 * n) {
                            continue;
                        }
                        //必须是0才能合并
                        if (mat[nx][ny] == 0 && uf.find(i * 3 * n + j) != uf.find(nx * 3 * n + ny)) {
                            uf.union(i * 3 * n + j, nx * 3 * n + ny);
                        }
                    }
                }
            }
        }
        return uf.count;
    }

    class UnionFind{
        private int[] father;
        private int count;

        public UnionFind(int n) {
            father = new int[n + 1];
            count = n;
            //初始化
            for (int i = 1; i <= n; i++) {
                father[i] = i;
            }
        }

        //压缩路径写法
        public int find(int x) {
            if (father[x] == x) {
                return x;
            }
            return father[x] = find(father[x]);
        }

        public void union(int a, int b) {
            int root_a = find(a);
            int root_b = find(b);
            if (root_a != root_b) {
                father[root_a] = root_b;
                count--;
            }
        }
    }

    public static void main(String[] args) {
        String[] strs = {" /","/ "};
        _959_由斜杠划分区域 test = new _959_由斜杠划分区域();
        test.regionsBySlashes(strs);
    }
}