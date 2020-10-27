package leetcode.coding;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class _632_最小区间 {


    /*
    相当于合并k和有序数组，用堆，pop出一个把下一个元素加到堆里
     */
    public int[] smallestRange(List<List<Integer>> nums) {
        int n = nums.size();
        int inf = 0x3f3f3f;
        int max = -inf; // 当前最大值
        int st = -inf;  // 起点
        int ed = inf;   // 终点

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.val, o2.val));

        // 相当于合并k个有序链表，把 head 放进去
        for (int i = 0; i < n; i++) {
            int val = nums.get(i).get(0);
            pq.offer(new Node(i, 0, val));
            max = Math.max(max, val);
        }

        // 必须包含 k 个元素
        while (pq.size() == n) {
            Node node = pq.poll();
            int i = node.i;
            int j = node.j;
            int val = node.val;

            // 更新区间长度
            if (max - val < ed - st) {
                st = val;
                ed = max;
            }

            // 为堆中填充元素
            if (j + 1 < nums.get(i).size()) {
                int nVal = nums.get(i).get(j + 1);
                pq.offer(new Node(i, j + 1, nVal));
                max = Math.max(max, nVal);
            }
        }
        return new int[]{st, ed};

    }

    class Node{
        int i, j, val;

        public Node(int i, int j, int val) {
            this.i = i;
            this.j = j;
            this.val = val;
        }
    }

}