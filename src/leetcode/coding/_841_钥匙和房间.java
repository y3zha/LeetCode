package leetcode.coding;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _841_钥匙和房间 {

    /**
     * dfs，看走过的房间是否为房间总数即可
     */
    int count;
    boolean[] vis;

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n= rooms.size();
        this.vis = new boolean[n];
        dfs(rooms, 0);
        return count == n;
    }

    private void dfs(List<List<Integer>> rooms, int start) {
        vis[start] = true;
        count++;
        List<Integer> list = rooms.get(start);
        for (Integer ne : list) {
            if (!vis[ne]) {
                dfs(rooms, ne);
            }
        }
    }

    /**
     * bfs
     */
    public boolean canVisitAllRooms2(List<List<Integer>> rooms) {
        int n = rooms.size();
        Queue<Integer> queue = new LinkedList<>();
        int[] vis = new int[n];
        queue.offer(0);
        vis[0] = 1;
        while (!queue.isEmpty()) {
            Integer cur = queue.poll();
            for (Integer ne : rooms.get(cur)) {
                if (vis[ne] == 0) {
                    queue.offer(ne);
                    vis[ne] = 1;
                }
            }
        }
        return Arrays.stream(vis).filter(x -> x == 1).count() == n;
    }



}