package leetcode.swordtooffer;

import java.util.PriorityQueue;

public class 面试题40_最小的k个数 {

    /**
     * 给定一个数字找出其中最小的k个数
     * 方法一：排序取前k个
     * 方法二：quick select算法
     * 方法三：优先队列
     */
    //方法三
    public int[] getLeastNumbers(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i : arr) {
            pq.offer(i);
        }
        int[] res = new int[k];
        int index = 0;
        while (k >= 0) {
            res[index++] = pq.poll();
            k--;
        }
        return res;
    }
}