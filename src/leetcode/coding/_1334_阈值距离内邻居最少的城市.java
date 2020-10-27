package leetcode.coding;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.util.*;

/**
 * 周赛题
 * dijkstra、floyd、spfa 写一下
 */
public class _1334_阈值距离内邻居最少的城市 {

    // floyd 处理多源最短路比较舒服
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = i == j ? 0 : 0x3f3f3f3f;
            }
        }
        for (int[] edge : edges) {
            map[edge[0]][edge[1]] = map[edge[1]][edge[0]] = edge[2];
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i != k && j != k && i != j && map[i][k] + map[k][j] < map[i][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }

        int min = Integer.MAX_VALUE;
        int cnt = 0;
        int res = -1;
        for (int i = 0; i < n; i++) {
            cnt = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && map[i][j] <= distanceThreshold) {
                    cnt++;
                }
            }
            if (cnt <= min) {
                min = cnt;
                res = i;
            }
        }
        return res;
    }


    // dijkstra
    // public int findTheCity(int n, int[][] edges, int distanceThreshold) {
    //     Map<Integer, List<int[]>> map = new HashMap<>();
    //     for (int[] edge : edges) {
    //         List<int[]> list = map.getOrDefault(edge[0], new ArrayList<>());
    //         list.add(new int[]{edge[1], edge[2]});
    //
    //     }
    // }

    // spfa 没过。。
    int[][] map;
    int[] dist;
    boolean[] vis;
    Queue<Integer> queue;
    int INF = 0x3f3f3f3f;

    public int findTheCity3(int n, int[][] edges, int distanceThreshold) {
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = i == j ? 0 : INF;
            }
        }
        for (int[] edge : edges) {
            map[edge[0]][edge[1]] = map[edge[1]][edge[0]] = edge[2];
        }
        int min = Integer.MAX_VALUE, cnt = 0, res = -1;
        for (int i = 0; i < n; i++) {
            int temp = spfa(i, distanceThreshold, n);
            if (temp <= min) {
                min = temp;
                res = i;
            }
        }
        return res;
    }

    private int spfa(int start, int distanceThreshold, int n) {
        dist = new int[n];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        vis = new boolean[n];
        vis[start] = true;

        queue = new LinkedList<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            Integer v1 = queue.poll();
            for (int v2 = 0; v2 < n; v2++) {
                if (map[v1][v2] != INF) {
                    int w = map[v1][v2];
                    if (dist[v2] == INF || dist[v2] > dist[v1] + w) {
                        dist[v2] = dist[v1] + w;
                        if (!vis[v2]) {
                            vis[v2] = true;
                            queue.offer(v2);
                        }
                    }
                }

            }
            vis[v1] = true;
        }
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (dist[i] <= distanceThreshold) {
                cnt++;
            }
        }
        return cnt;
    }
}