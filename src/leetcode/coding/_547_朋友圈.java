package leetcode.coding;

/**
 * 并查集类问题，求朋友圈个数
 */
public class _547_朋友圈 {

    public int findCircleNum(int[][] M) {
        int n = M.length;   //正方形的边长
        UnionFind uf = new UnionFind(n);
        //只要遍历右上角即可
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                //如果他们是朋友
                if (M[i-1][j-1] == 1) {
                    //看看它们的father一不一样，不一样则合并
                    if (uf.find(i) != uf.find(j)) {
                        uf.union(i, j);
                    }
                }
            }
        }
        return uf.count;
    }

    class UnionFind{
        private int[] father = null;
        private int count;

        public UnionFind(int n) {
            father = new int[n + 1];
            count = n;
            for (int i = 1; i <= n; i++) {
                father[i] = i;
            }
        }

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


}