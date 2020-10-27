package leetcode.coding;

import java.util.ArrayList;
import java.util.List;

public class _886_可能的二分法 {

    public boolean possibleBipartition(int N, int[][] dislikes) {
        Node[] nodes = new Node[N + 1];
        // init
        for (int i = 0; i < N + 1; i++) {
            nodes[i] = new Node();
            nodes[i].val = i;
            nodes[i].dislike = new ArrayList<>();
        }
        for (int[] a : dislikes) {
            Node n1 = nodes[a[0]];
            Node n2 = nodes[a[1]];
            n1.dislike.add(n2);
            n2.dislike.add(n1);
        }
        // 分组
        int[] group = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            // 分组 0 代表未分组， 1 代表分组1， -1 代表分组2，用-1代表分组2是为了方便
            // 在不能分配某一组的时候尝试分另外一组，这个时候有其中一组转变为另外一组就可以直接乘以-1
            // 常数分组1
            if (group[i] == 0 && !dfs(nodes, group, i, 1, N)) {
                return false;
            }
        }
        return true;
    }

    // 具体算法
    // 遍历每一个人，默认分配分组1
    // 遍历这个人的讨厌的人，尝试给他们分另一组，分组失败则返回false
    private boolean dfs(Node[] nodes, int[] group, int i, int color, int n) {
        group[i] = color;
        for (Node node : nodes[i].dislike) {
            int other = node.val;
            // 对于一个人如果分配了一个组，并且他讨厌的人也被分组了，那么就不能再分配另外一组，返回false
            if (group[other] == color) {
                return false;
            }
            // 如果他讨厌的人没分组，就尝试把他讨厌的人分到另一组
            if (group[other] == 0 && !dfs(nodes, group, other, color * -1, n)) {
                return false;
            }
        }
        return true;
    }

    static class Node {
        int val;
        List<Node> dislike;
    }
}