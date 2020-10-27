package leetcode.interviewbook;

import java.util.PriorityQueue;

public class 面试题17_14_最小K个数 {

    public int[] smallestK(int[] arr, int k) {
        int[] res = new int[k];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i : arr) {
            pq.offer(i);
        }
        int index = 0;
        while (k > 0) {
            res[index++] = pq.poll();
            k--;
        }
        return res;
    }
}