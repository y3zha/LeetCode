package leetcode.coding;

/**
 * 要求在旋转数组中找到给定的数，时间复杂度O(logN)
 * 这就只能二分了
 */

/**
 * 【问题】
 * 假设有一个排序的按未知的旋转轴旋转的数组(比如，0 1 2 4 5 6 7 可能成为4 5 6 7 0 1 2)
 * 给定一个目标值进行搜索，如果在数组中找到目标值返回数组中的索引位置，否则返回-1。你可以假设数组中不存在重复的元素。
 *
 * 【思路】
 * 思路一：先排序，再二分搜索...，排序时间复杂度O(n)啊，暴力了
 * 思路二：两次二分。可以利用logn，找到最小的那个，最小的这个，比如0把这个数组分为左右两部分，判断这个数字在哪一部分，再去二分
 * 思路三：如果面试官要求不能两次二分呢？取mid只有两种情况
 *          情况一、取在前半部分升序的数组中
 *          情况二、取在后半部分升序的数组中
 *          这两种情况分开处理。
 *          如何判断mid是切在前半部分还是后半部分呢？只要和rotate后数组的第一个元素比较，如果大于第一个元素，那就是前半部分，如果小于第一个元素，那就是在后半部分
 *      然后需要去判断target在mid的左半部分还是右半部分
 *          如果 A[start] <= target <= A[mid],那就在左半部分
 *          如果 A[mid] <= target <= A[end],那就在右半部分
 *
 *  做完这个做81题 follow up，假如有重复元素又将如何？
 */
public class _033_搜索旋转排序数组 {

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        int mid = 0;

        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] < nums[start]) {      //如果切在了后半部分,这边在纸上画一下就知道了
                if (nums[mid] <= target && target <= nums[end]) {
                    start = mid;
                } else {
                    end = mid;
                }
            } else {        //如果切在了前半部分
                if (nums[start] <= target && target <= nums[mid]) {
                    end = mid;
                } else {
                    start = mid;
                }
            }
        }

        //double check
        if (nums[start] == target) {
            return start;
        }
        if (nums[end] == target) {
            return end;
        }
        return -1;
    }
}