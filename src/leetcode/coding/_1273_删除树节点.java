package leetcode.coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class _1273_删除树节点 {

    /*
    基于数据的思路
    1. 第一遍是从后往前遍历，把子节点的值累加到父节点
    2. 从前往后遍历，计算自身不为0且父节点不为0的节点的数量，遍历过程中要把父节点为0自身不为0的节点设置为0，以便能处理下一级节点。
     */
    public int deleteTreeNodes(int nodes, int[] parent, int[] value) {
        // 除去根
        for (int i = nodes - 1; i >= 1; i--) {
            value[parent[i]] += value[i];
        }
        // i=0,j=1
        int count = 0;
        if (value[0] != 0) {
            count++;
        }
        for (int i = 1; i < nodes; i++) {
            if (value[i] != 0) {
                if (value[parent[i]] == 0) {
                    value[i] = 0;
                } else {
                    count++;
                }
            }
        }
        return count;
    }

    /*
    dfs 的思路，先把子树的值算出来，再将所有节点累加，就可以得到以当前节点为根时节点数目和权值
    如果权值之和为 0，那么以这个节点为根节点的树就要被删除，将其节点数目置为0返回给上一层
    最后根节点对应的数目就是答案
     */
    public int deleteTreeNodes2(int n, int[] parent, int[] value) {
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node();
        }
        for (int i = 0; i < n; i++) {
            if (parent[i] != -1) {
                nodes[parent[i]].adj.add(i);
            }
        }
        int[] cnt = new int[n];
        Arrays.fill(cnt, 1);
        dfs(0, nodes, value, cnt);
        return cnt[0];
    }

    private void dfs(int cur, Node[] nodes, int[] value, int[] cnt) {
        for (Integer child : nodes[cur].adj) {
            dfs(child, nodes, value, cnt);
            value[cur] += value[child];
            cnt[cur] += cnt[child];
        }
        if (value[cur] == 0) {
            cnt[cur] = 0;
        }
    }

    class Node {
        int id;
        List<Integer> adj = new ArrayList<>();
    }
}