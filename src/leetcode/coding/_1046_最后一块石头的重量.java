package leetcode.coding;

import java.util.PriorityQueue;

public class _1046_最后一块石头的重量 {

    // 由于需要动态维护数据的有序性（支持增加、删除），这里使用优先队列是最合适的数据结构。知道了这一点以后，根据题目意思模拟就好了。
    public int lastStoneWeight(int[] stones) {
        int n = stones.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>(n, (o1, o2) -> o2.compareTo(o1));
        for (int stone : stones) {
            pq.offer(stone);
        }
        while (pq.size() >= 2) {
            Integer x = pq.poll();
            Integer y = pq.poll();
            if (!x.equals(y)) {
                pq.offer(x - y);
            }
        }
        return pq.isEmpty() ? 0 : pq.poll();
    }
}