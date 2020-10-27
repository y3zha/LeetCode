package leetcode.coding;

import java.util.Arrays;

/**
 * A.sort()
 *         ans = 0
 *         n = len(A)
 *         for i in range(1, n):
 *             if A[i] <= A[i-1]:
 *                 ans += A[i-1] - A[i] + 1
 *                 A[i] = A[i-1] + 1
 *         return ans
 */
public class _945_使数组唯一的最小增量 {

    //方法一，排序遍历
    public int minIncrementForUnique(int[] A) {
        Arrays.sort(A);
        int res = 0;
        int n = A.length;
        for (int i = 1; i < n; i++) {
            if (A[i] <= A[i - 1]) {
                res += A[i - 1] - A[i] + 1;
                A[i] = A[i - 1] + 1;
            }
        }
        return res;
    }
}