package leetcode.coding;


import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 数据流的中位数：数字是不断进入数组的，在每次添加一个新的数进入数组的同时返回当前新数组的中位数。
 * 中位数是排序后数组的中间值，如果有数组中有n个数，则中位数为A[(n-1)/2]
 * 时间复杂度为O(nlogn)
 *
 * 思路：用大小堆
 *      如果数组长度为奇数，中位数是最中间的那个，如果长度为偶数是中间偏左的那个元素
 *      使用最大堆来存储等于或小于中位数的值，只需poll一次就可弹出当前的中位数，使用最小堆来存储大于中位数的值。
 *      同时需要保持两个堆平衡，因为我们要获得中位数，所以最大堆的大小将始终等于或比最小堆的大小大1,保持平衡就好
 *
 *      做完这个做480题
 */
public class _295_数据流的中位数 {

    private PriorityQueue<Integer> maxHeap, minHeap;

    /** initialize your data structure here. */
/*    public MedianFinder() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }*/

    public void addNum(int num) {
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());
        //如果不平衡则调整
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