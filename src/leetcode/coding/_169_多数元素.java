package leetcode.coding;

/**
 * 做个笔记
 * 本质上这个题就是求众数，我们都会用hash表
 * 但是如何做到空间O（1）呢
 * 这里就得用 摩尔投票算法了
 *
 * 我们把众数记为 +1+1 ，把其他数记为 -1−1 ，将它们全部加起来，显然和大于 0 ，从结果本身我们可以看出众数比其他数多。
 * 为啥这样是对的呢
 *
 * 我们维护一个计数器，如果遇到一个我们目前的候选众数，就将计数器加一，否则减一。
 * 只要计数器等于 0 ，我们就将 nums 中之前访问的数字全部 忘记 ，并把下一个数字当做候选的众数。
 *
 * 因为 【通过忽略掉前面的数字，我们忽略掉了同样多数目的众数和非众数，因为如果遗忘更多的非众数，会导致计数器变成负数】
 *
 * 做完这个题做229！
 */
public class _169_多数元素 {

    public int majorityElement(int[] nums) {
        int n = nums.length;
        int cnt = 0;
        Integer candidate = null;
        for (int i : nums) {
            if (cnt == 0) {
                candidate = i;
            }
            cnt += candidate == i ? 1 : -1;
        }
        return candidate;
    }


}