package leetcode.coding;

import java.util.Arrays;
import java.util.HashMap;

public class _924_尽量减少恶意软件的传播 {

    /*
    题意：
    在一些分散的图中，某些图中有某些节点是病原体，与这些节点在一个图中的所有节点都被感染了
    现在求去掉一个原始病原体，使得最终所有图中感染的节点最少
     */
    public int minMalwareSpread(int[][] graph, int[] initial) {
        int n = graph.length;
        int m = graph[0].length;
        DSU dsu = new DSU(n);

        // 首先给init排序，要求返回最小的那个
        Arrays.sort(initial);

        // 开始union
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < m; j++) {
                if (graph[i][j] == 1) {
                    dsu.union(i, j);
                }
            }
        }

        // 统计下每个联通块内包含感染节点的数量，如果多于1个就不用看了，肯定都会被感染的
        // 联通块编号 -> 数量
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : initial) {
            int tmp = dsu.find(i);
            map.put(tmp, map.getOrDefault(tmp, 0) + 1);
        }

        int res = -1;
        int max = -1;
        // 遍历所有的节点
        for (int i : initial) {
            // 如果联通块中只有一个感染节点
            if (map.get(dsu.find(i)) == 1) {
                // 获取此时联通块的大小
                int size = dsu.getSize(i);
                if (size > max) {
                    max = size;
                    res = i;
                }
            }
        }
        if (res == -1) {
            return initial[0];
        }
        return res;
    }

    class DSU{
        int[] f;
        int[] nodeSize;

        public DSU(int n) {
            f = new int[n];
            nodeSize = new int[n];
            for (int i = 0; i < n; i++) {
                f[i] = i;
            }
            // 起始联通块大小是 1
            Arrays.fill(nodeSize, 1);
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
                nodeSize[fb] += nodeSize[fa];
            }
        }

        public int getSize(int x) {
            return nodeSize[f[x]];
        }
    }
}