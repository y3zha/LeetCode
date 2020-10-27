package leetcode.coding;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class _1042_不邻接植花 {

    /*
    存储邻接点信息
    遍历所有节点，对于每个节点
    查看其邻接点颜色，使用不同的颜色染色即可
     */
    public int[] gardenNoAdj(int N, int[][] paths) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            graph.put(i, new HashSet<>());
        }
        /* 初始化路径信息 */
        for (int[] path: paths) {
            int a = path[0];
            int b = path[1];
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        int[] res = new int[N];
        for (int i = 1; i <= N; i++) {
            boolean[] used = new boolean[5];
            /* 查看当前节点的所有邻接点的色彩 */
            for (int adj: graph.get(i)) {
                used[res[adj - 1]] = true;
            }
            /* 为当前节点染色 */
            for (int j = 1; j <= 4; j++) {
                if (!used[j]) {
                    res[i] = j;
                }
            }
        }
        return res;
    }
}