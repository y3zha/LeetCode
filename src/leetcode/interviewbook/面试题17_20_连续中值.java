package leetcode.interviewbook;

import java.util.Collections;
import java.util.PriorityQueue;

public class 面试题17_20_连续中值 {

    /**
     * 这个题就是数据流的中位数
     * 分成两部分，左边大顶堆，右边小顶堆
     */

    class MedianFinder {

        PriorityQueue<Integer> minHeap;
        PriorityQueue<Integer> maxHeap;

        public MedianFinder() {
            minHeap = new PriorityQueue<>();
            maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        }

        public void addNum(int num) {
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
            if (minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
        }

        public double findMedian() {
            if (maxHeap.size() == minHeap.size()) {
                return (maxHeap.peek() + minHeap.peek()) * 0.5;
            }
            return maxHeap.peek();
        }
    }
}