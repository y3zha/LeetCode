package leetcode.interviewbook;

import java.util.*;

public class 面试题04_01_节点间通路 {

    /**
     * BFS+邻接表
     */
    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        ArrayList<Integer>[] edges = new ArrayList[n];
        //init
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int[] ints : graph) {
            int s = ints[0];
            int e = ints[1];
            edges[s].add(e);
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        Set<Integer> set = new HashSet<>();
        set.add(start);
        while (!queue.isEmpty()) {
            Integer p = queue.poll();
            if (edges[p].contains(target)) {
                return true;
            } else {
                for (Integer i : edges[p]) {
                    if (!set.contains(i)) {
                        set.add(i);
                        queue.offer(i);
                    }
                }
            }
        }
        return false;
    }
}