package leetcode.coding;

public class _685_冗余连接II {


    /*
    只需要去除一条边，就能变成一个根树（有向），反过来说一定存在（可以存在很多）某个根树加了一条边变成了现在的图
    1. 这条非法边从任意一个节点出发，指向树根。 这种情况下，树没了根，所有点的入度是 1 -> 环上的任意一条边都可以删除，删除之后就可以得到一个合法的根树。
    2. 这条边从任意节点出发，指向祖先链上任意一个非根节点。树不合法的原因是出现了一个有两个入度的节点，并且其中一个入度来自环中的一条边,只能删除来自环里的那条边
    3. 这条边从任意节点出发，指向非祖先链上任意一个节点,树不合法的原因也是出现了一个有两个入度的节点，但两个入度都在环上。可以观察到，这两个入度都能删掉，所以按照题目要求删掉排序在后面的那条。

    并查集合并每一条边，对于情况2、3，我们会在这个过程中碰到一条边导致某个点的入度从1变为2，也就是那个有两个入度的点的排在后面的那个入度。
    我们先把这两条边edge1，edge2记下来，**如果我们跳过并查集合并edge2**，那么对于情况2、3会出现不同的结果。

    ～如果合并的过程中压根没碰到过一条边会导致双入度点，那么就是情况1，这种情况下，合并过程中会碰到一条边导致环的出现，（一个环，在最后一条边出现之前都不是个环）因此的这条边就是情况1的答案。
    ～如果跳过的这条edge2是在环里的那条边，则也会一路畅通，那答案就是 edge2, 如果跳过的这条edge2是不在环里的那条边，那么接下来并查集合并的时候一定会碰到环！那么答案就是 edge1。
    ～情况3会发现合并到最后一路畅通，什么事情都不会发生，因此跳过这条edge2就是答案。

     */

    private int[] fa;   // dsu
    private int[] pa;   // 节点与他的父亲

    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        fa = new int[n + 1];
        pa = new int[n + 1];

        int[] e1 = null, e2 = null, lastCir = null;

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];

            // 初始化 dsu
            if (fa[u] == 0) fa[u] = u;
            if (fa[v] == 0) fa[v] = v;

            // 节点 v 有父亲的情况，放弃合并，检查是否有环，同时记录下 e1 和 e2
            if (pa[v] != 0) {
                e1 = new int[]{pa[v], v};
                e2 = edge;
            } else {
                pa[v] = u;
                int ru = find(u);
                int rv = find(v);
                if (ru != rv) {
                    fa[rv] = ru;
                } else {
                    // 碰到环
                    lastCir = edge;
                }
            }
        }
        // 如果是情况2、3，则根据有没有碰到环返回 edge1 或 edge2
        if (e1 != null && e2 != null) {
            return lastCir == null ? e2 : e1;
        } else {
            // 否则就是情况 1
            return lastCir;
        }
    }

    private int find(int x){
        if (x == fa[x]) {
            return x;
        }
        return fa[x] = find(fa[x]);
    }
}