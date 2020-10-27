package leetcode.coding;


import java.util.ArrayList;
import java.util.List;

public class _834_树中距离之和 {

    /*
    两次dfs
    dfs1：求出每个节点为根时的子节点个数，求出每个节点为根时，到它的子节点们的路径和
    dfs2：每个节点利用其父节点的信息，求出通过其父节点才能到达其他节点的路径和
     */

    int[] ans;
    int[] sumOfPath;    // sumOfPath[i] 表示以 i 为 root 时，它到它的儿子们的路径和
    int[] childCnt;     // childCnt[i] 表示以 i 为 root 时，它有几个儿子
    List<List<Integer>> g;
    int nodes;

    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        nodes = N;
        ans = new int[N];
        sumOfPath = new int[N];
        childCnt = new int[N];
        g = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            g.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            g.get(edge[0]).add(edge[1]);
            g.get(edge[1]).add(edge[0]);
        }
        dfs1(0, -1);
        dfs2(0, -1);
        return ans;
    }

    private void dfs2(int cur, int fa) {
        if (fa == -1) {
            ans[cur] = sumOfPath[cur];
        } else {
            ans[cur] = ans[fa] - childCnt[cur] + nodes - childCnt[cur];
        }
        for (Integer child : g.get(cur)) {
            if (child == fa) continue;
            dfs2(child, cur);
        }
    }

    private void dfs1(int cur, int fa) {
        // 遍历儿子
        for (Integer child : g.get(cur)) {
            if (child == fa) continue;
            dfs1(child, cur);
            childCnt[cur] += childCnt[child];
            // 路径和除了要加上子节点的路径和，还要加上当前 root 到儿子的路径和，是这两部分构成的
            sumOfPath[cur] += sumOfPath[child];
            sumOfPath[cur] += childCnt[child];
        }
        // nodeNum[root] = sum(nodeNum[child]) + 1
        childCnt[cur]++;
    }
}