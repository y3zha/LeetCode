package leetcode.coding;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 和207题的课程表是一样的，见课程表即可
 */
public class _210_课程表II {

    /**
     * BFS
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer> list = new ArrayList<>();
        int[] indegrees = new int[numCourses];
        for (int[] p : prerequisites) {
            indegrees[p[0]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegrees.length; i++) {
            if (indegrees[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            //poll出来的是先修课程
            Integer pre = queue.poll();
            list.add(pre);
            //删除pre，它的邻居的入度都-1
            for (int[] p : prerequisites) {
                if (p[1] != pre) {
                    continue;
                }
                if (--indegrees[p[0]] == 0) {
                    queue.offer(p[0]);
                }
            }
        }
        if (list.size() != numCourses) {
            return new int[]{};
        } else {
            return list.stream().mapToInt(Integer::valueOf).toArray();
        }
    }

    /**
     * DFS
     * 我们使用vis数组来表示当前顶点的状态，和207题不一样的是，这里vis仅有访问和没访问过
     * 我们再利用help数组来表示再这一轮中这个节点有没有被再次访问，如果被再次访问了，那么就说明有环！
     */
    int[] res;
    boolean[] vis;  //顶点的状态
    ArrayList<Integer>[] edges;
    boolean[] help; //这一轮中节点的状态
    int idx;

    public int[] findOrder2(int n, int[][] prerequisites) {
        res = new int[n];
        vis = new boolean[n];
        edges = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int[] arr : prerequisites) {
            edges[arr[0]].add(arr[1]);
        }
        help = new boolean[n];
        boolean flag = true;
        //遍历每个节点
        for (int i = 0; i < n; i++) {
            if (!vis[i] && haveCycle(i)) {
                return new int[0];
            }
        }
        return res;
    }

    // 是否有环
    private boolean haveCycle(int v) {
        vis[v] = true;
        help[v] = true;
        // 遍历邻居
        for (Integer next : edges[v]) {
            if (vis[next] && help[next]) {
                return true;
            }
            if (vis[next]) {
                continue;
            }
            if (haveCycle(next)) {
                return true;
            }
        }
        res[idx++] = v;
        //状态重置
        help[v] = false;
        return false;
    }


}