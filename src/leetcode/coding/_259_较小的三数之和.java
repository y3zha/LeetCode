package leetcode.coding;

import java.util.Arrays;

public class _259_较小的三数之和 {

    // a + b + c < target ->  b + c < target - a
    // 固定小的
    public static int threeSumSmaller(int[] nums, int target) {
        if (nums == null || nums.length < 3) return 0;
        Arrays.sort(nums);
        int res = 0;
        int n = nums.length;
        // 需要考虑去重么？不需要吧
        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;
            int temp = target - nums[i];
            while (left < right) {
                if (nums[left] + nums[right] >= temp) {
                    right--;
                } else {
                    // 因为在right之前的到left之间的这些数，和left相加 都小于 left + right啊
                    res += right - left;
                    left++;
                }

            }
        }
        return res;
    }
}