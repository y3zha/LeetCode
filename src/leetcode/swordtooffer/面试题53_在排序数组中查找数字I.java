package leetcode.swordtooffer;

public class 面试题53_在排序数组中查找数字I {

    /**
     * 两次二分 找到元素在数组中的第一次出现位置和最后一次出现位置
     */
    public int search(int[] nums, int target) {
        if (nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int first = getFirstIndex(nums, 0, n - 1, target);
        int last = getLastIndex(nums, 0, n - 1, target);
        // System.out.println(first + " " + last);
        if (first == last && first == -1) {
            return 0;
        }
        return last - first + 1;
    }

    private int getFirstIndex(int[] nums, int start, int end, int target) {
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                end = mid;
            } else if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums[start] == target) {
            return start;
        } else if (nums[end] == target) {
            return end;
        }
        return -1;
    }

    private int getLastIndex(int[] nums, int start, int end, int target) {
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                start = mid;
            } else if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums[end] == target) {
            return end;
        } else if (nums[start] == target) {
            return start;
        }
        return -1;
    }

}