package leetcode.coding;

import java.util.Arrays;

/**
 * 二分法,这个就是两次二分
 * 利用lintcode 014题和lintcode 458题，做两次二分，一次找到头，一次找到尾巴
 */
public class _034_在排序数组中查找元素的第一个和最后一个位置 {


    public static int[] searchRange(int[] nums, int target) {
        //leetcode限制也不在题目中说说清楚
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        if (nums.length == 1) {
            if (nums[0] == target) {
                return new int[]{0, 0};
            } else {
                return new int[]{-1, -1};
            }
        }
        Arrays.sort(nums);
        int firstIndex = findFirstIndex(nums, target);
        int lastIndex = findLastIndex(nums, target);
        return new int[]{firstIndex, lastIndex};
    }

    //找到target第一次出现的位置。。lintcode 014题
    public static int findFirstIndex(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                end = mid;      //end去接
            } else if (nums[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        //double check 找第一次出现的位置,首先判断头,再判断尾巴
        if (nums[start] == target) {
            return start;
        } else if (nums[end] == target) {
            return end;
        } else {
            return -1;
        }
    }
    //找到target最后一次出现的位置。。lintcode 458题
    public static int findLastIndex(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                start = mid;      //start去接
            } else if (nums[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        //double check 找最后一次出现的位置,首先判断尾巴,再判断头
        if (nums[end] == target) {
            return end;
        } else if (nums[start] == target) {
            return start;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 2};
        searchRange(nums, 3);
    }
}