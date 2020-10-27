package leetcode.coding;

import java.util.Arrays;

public class _287_寻找重复数 {

    /**
     * 二分法, 可以利用二分法来确定一个范围内的数，如果测试数据不在这个范围内，二分法就失效了
     * 二分思路：先猜一个数字统计原数组中 【小于等于】 这个数的个数 cnt，如果 cnt 严格大于 mid（抽屉原理），重复的元素就在这个区间内
     * 否则就在另一个区间内
     *
     * 二分写法一，left + 1 则取 mid 不需要 + 1
     */
    public int findDuplicate(int[] nums) {
        int left = 1, right = nums.length - 1;
        while (left < right) {
            // 猜一个数字
            int val = left + right >> 1;
            // 如果在重复的区间
            if (check(val, nums)) {
                right = val;
            } else {
                left = val + 1;
            }
        }
        return left;
    }

    // 如果大于等于 val 的有大于 val 个,filter 内是的保留的
    private boolean check(int val, int[] nums) {
        long count = Arrays.stream(nums).filter(x -> x <= val).count();
        return count > val;
    }

    /**
     * 方法二：快慢指针
     *
     * 对 nums 数组建图，每个下标 i 都指向 nums[i]，因为存在重复的数字，也就是存在相同的 nums[i]，因此至少有两个下标指向它，
     * 其实就是数组中有环，这里说的 数组 有环，即 数组中的一个元素值 被不同的 index 指向（映射），所以可以考虑快慢指针的做法
     * 我们要找的答案就是环的入口，这个题就等价于 环形链表 II
     *
     * 「Floyd 判圈算法」两个指针在有环的情况下一定会相遇，此时我们再将 slow 放置起点 0，两个指针每次同时移动一步，相遇的点就是答案。
     */

    public int findDuplicate2(int[] nums) {
        int slow = 0, fast = 0;
        // 先做一次
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        // 相等了， slow 从头开始
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

}