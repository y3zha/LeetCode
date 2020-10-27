package leetcode.coding;

import java.util.Arrays;

public class _1099_小于K的两数之和 {
    // 和小于k 但尽可能接近k
    // 枚举，或者排序二分都可以吧
    public static int twoSumLessThanK(int[] A, int K) {
        if (A == null || A.length == 0) return -1;
        Arrays.sort(A);
        int left = 0, right = A.length - 1;
        int res = Integer.MIN_VALUE;
        while (left < right) {
            if (A[left] + A[right] >= K) {
                right--;
            } else {
                // 小于k才更新
                res = Math.max(res, A[left] + A[right]);
                left++;
            }
        }
        return res == Integer.MIN_VALUE ? -1 : res;
    }

}