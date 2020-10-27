package leetcode.coding;

/**
 * 并查集
 *
 * 冗余连接是说，给定一张图（无环、无向图）
 * 这张图是由树构成的，一共有N个节点和一条附加的边，这条附加的边是不属于树中已存在的边的问这条可以删除的边是哪条
 *
 * 思路：正常来说，图既是树，节点 = 边+1，现在是多了一条冗余边，也就是节点=边，必定构成环。
 *      用并查集的思路就是：如果这条边的两个节点一开始就在同一个集合中，那么说明它们连接后将成环，这就是我们要找的
 */
public class _684_冗余连接 {

    public int[] findRedundantConnection(int[][] edges) {
        int[] res = new int[2];
        int n = edges.length;
        UnionFind uf = new UnionFind(n);
        //遍历每一条边
        for (int i = 1; i <= n; i++) {
            int root1 = uf.find(edges[i - 1][0]);
            int root2 = uf.find(edges[i - 1][1]);
            //如果不在同一个集合中，合并
            if (root1 != root2) {
                uf.union(edges[i - 1][0], edges[i - 1][1]);
            } else {
                res[0] = edges[i - 1][0];
                res[1] = edges[i - 1][1];
                //不着急返回，有多个答案返回最后出现的
            }
        }
        return res;
    }

    class UnionFind{
        private int[] father = null;

        public UnionFind(int n) {
            father = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                father[i] = i;
            }
        }

        //采用路径压缩写法
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
            }
        }
    }

}