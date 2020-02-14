package leetcode.swordtooffer;

import java.util.Deque;
import java.util.LinkedList;

public class 面试题59_滑动窗口的最大值I {

    /**
     * 这个题要用双端队列做,时间复杂度才是最优的
     *
     * 思路
     *      1、最简单的思路，两个for循环，遍历所有窗口，一共N-K+1个窗口，每个窗口k个元素，时间复杂度O(NK)
     *      2、优化时间复杂度，看到集合中求最大最小值，用堆，堆的大小为k，每次add一个，时间复杂度logK，一共add n次，时间复杂度NlogK
     *      3、能不能优化到O（N）,看到这个问题，肯定是要把插入、删除（弹出）的时间复杂度变为O（1）,那只能用数据结构，比如队列、栈、双端队列
     *         在移动窗口的过程中，获得窗口最大值是我们想要的，这里用双端队列表示窗口，双端窗口的队头总是当前窗口中最大的那个数
     *         如果左端小于右端, 那么就肯定没用, 直接剔除掉。
     *         注意要保存的是下标而不是值，有了这个下标，我们可以很快地知道新的窗口是否已经不再包含原来那个最大的数
     *
     *         维护一个单调递减栈即可
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }
        int n = nums.length;
        int[] res = new int[n - k + 1];
        int index = 0;
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            //如果队列不为空，且里面的最大值小于当前元素
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.removeLast();
            }
            deque.offerLast(i);
            //如果对头元素不在窗口中的，需要踢出
            if (deque.getFirst() <= i - k) {
                deque.removeFirst();
            }
            if (i + 1 >= k) {
                res[index++] = nums[deque.getFirst()];
            }
        }
        return res;
    }
}