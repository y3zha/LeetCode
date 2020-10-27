package leetcode.coding;

/**
 * 二分法
 */
public class _153_寻找旋转排序数组中的最小值 {

    public int findMin(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        int last = nums[end];   //最后一个数，因为发生旋转，后半部分最后一个数一定比前半部分第一个数小，不发生旋转那就暂且指定它为最小的 然后更新

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] <= last) {
                end = mid;
                last = nums[mid];
            } else {
                start = mid;
            }
        }

        //如果头还比尾巴小
        if (nums[start] <= nums[end]) {
            return nums[start];
        } else {
            return nums[end];
        }
    }
}