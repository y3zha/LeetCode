package leetcode.coding;

import java.util.ArrayList;
import java.util.List;

public class _163_缺失的区间 {

    public static List<String> findMissingRanges(int[] nums, int lower, int upper) {
        String arrow = "->";
        List<String> ans = new ArrayList<>();
        int n = nums.length;
        if (n == 0) {
            if (lower == upper) {
                ans.add(String.valueOf(lower));
            } else {
                ans.add(lower + arrow + upper);
            }
        }
        for (int i = 0; i < n; i++) {
            if (i == 0 && nums[i] > lower) {
                long val = nums[i] - 1;
                ans.add(val == lower ? String.valueOf(lower) : lower + arrow + val);
            }
            if (i >= 1 && nums[i] > nums[i - 1]) {
                if (nums[i] - nums[i-1] == 2) ans.add(String.valueOf(nums[i] - 1));
                if ((long)(nums[i]) - (long)(nums[i-1]) > 2) ans.add((nums[i - 1] + 1) + arrow + (nums[i] - 1));

            }
            if (i == n - 1 && nums[i] < upper) {
                long val = nums[i] + 1;
                ans.add(val == upper ? String.valueOf(upper) : val + arrow + upper);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        findMissingRanges(new int[]{0x80000000,0x7fffffff}, -2147483648, 2147483647);

        System.out.println((long)(0x7fffffff) - (long)(0x80000000));

    }

}