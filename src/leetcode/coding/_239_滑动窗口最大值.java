package leetcode.coding;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

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
 */
public class _239_滑动窗口最大值 {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }
        int[] res = new int[nums.length - k + 1];
        int index = 0;  //res下标
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            //如果队中有数比当前元素小，依次弹出，直到满足要求,必须维护一个单调递减的双端队列
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }
            //满足要求了，添加当前元素的下标到队尾
            deque.offerLast(i);
            //看看队头元素要不要踢出
            if (deque.getFirst() <= i - k) {
                deque.pollFirst();
            }
            //更新当前窗口最大值
            if (i + 1 >= k) {
                res[index++] = nums[deque.getFirst()];
            }
        }
        return res;
    }

}