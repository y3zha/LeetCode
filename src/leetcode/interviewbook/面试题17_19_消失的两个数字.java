package leetcode.interviewbook;

import java.util.Arrays;

public class 面试题17_19_消失的两个数字 {

    /**
     * 缺失数字
     * 方法一： 加法
     * 因为缺失的2个数字不相等，所以一个小于等于threshold,一个大于threshold。
     * 只对小于等于threshold的元素求和，得到第一个缺失的数字
     */
    public int[] missingTwo1(int[] nums) {
        int n = nums.length + 2;
        int sum = Arrays.stream(nums).sum();
        // 两数之和
        int twoSum = n * (n + 1) / 2 - sum;
        int threshold = twoSum / 2;
        int sum2 = Arrays.stream(nums).filter(x -> x <= threshold).sum();
        int disappear1 = threshold * (threshold + 1) / 2 - sum2;
        return new int[]{disappear1, twoSum - disappear1};
    }

    /**
     * 方法二：异或后再拆分
     */
    public int[] missingTwo2(int[] nums) {
        int n = nums.length + 2;
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        for (int i = 1; i <= n; i++) {
            xor ^= i;
        }
        int mask = xor & -xor;
        int a = 0;
        // 再筛选两次
        for (int i = 1; i <= n; i++) {
            if ((mask & i) != 0) {
                a ^= i;
            }
        }
        for (int num : nums) {
            if ((mask & num) != 0) {
                a ^= num;
            }
        }
        return new int[]{a, a ^ xor};
    }
}