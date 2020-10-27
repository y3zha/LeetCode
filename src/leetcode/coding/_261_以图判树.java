package leetcode.coding;

import java.util.*;

public class _261_以图判树 {

    // 图是树的话
    // 首先：节点个数 = 边数-1
    // 其次：所有节点都是联通的
    // bfs 写一下

    /**
     *
     * @param n 节点个数
     * @param edges 边集合
     * @return
     */
    public boolean validTree(int n, int[][] edges) {
        // 节点个数 如果不等于 边数+1，return false
        if (n != edges.length + 1) {
            return false;
        }
        // 对于每个节点都搞一个邻接表
        ArrayList<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        // 添加关系
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        queue.offer(0);
        set.add(0);

        while (!queue.isEmpty()) {
            Integer v = queue.poll();
            for (Integer next : adj[v]) {
                if (!set.contains(next)) {
                    set.add(next);
                    queue.offer(next);
                }
            }
        }
        return set.size() == n;
    }


    // 并查集写一下
    // 如果图是树，最终不就一个集合吗
    // 每次取一条边，如果这条边的两端不在同一个集合中，就将其所在的集合并成一个，如果在一个集合中，那么就说明存在环,直接返回false

    public boolean validTree2(int n, int[][] edges) {
        DSU dsu = new DSU(n);
        for (int[] e : edges) {
            if (dsu.find(e[0]) != dsu.find(e[1])) {
                dsu.union(e[0], e[1]);
            } else {
                return false;
            }
        }
        System.out.println(dsu.count);
        return dsu.count == 1;
    }
    class DSU{
        private int[] f;
        private int count;

        public DSU(int n) {
            f = new int[n];
            count = n;
            for (int i = 0; i < n; i++) {
                f[i] = i;
            }
        }

        public int find(int x) {
            if (x == f[x]) {
                return x;
            }
            return f[x] = find(f[x]);
        }

        public void union(int a, int b) {
            int fa = find(a);
            int fb = find(b);
            if (fa != fb) {
                // a -> b
                f[fa] = fb;
            }
            count--;
        }
    }


}