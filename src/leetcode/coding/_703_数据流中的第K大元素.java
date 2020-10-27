package leetcode.coding;

import java.util.Collections;
import java.util.PriorityQueue;

public class _703_数据流中的第K大元素 {

    /**
     * 思路一：小顶堆，第k大的数，只维护k个元素，对顶就是第k大的
     * 思路二：二叉搜索树
     */
    class KthLargest {

        PriorityQueue<Integer> pq;
        int k;


        public KthLargest(int k, int[] nums) {
            this.k = k;
            pq = new PriorityQueue<>(k);
            for (int num : nums) {
                add(num);
            }
        }

        // 每次添加元素，能添加到队列的有两种情况，一种未到k个，另一种比堆顶大
        public int add(int val) {
            if (pq.size() < k) {
                pq.offer(val);
            } else if (pq.peek() < val) {
                pq.poll();
                pq.offer(val);
            }
            return pq.peek();
        }
    }

}