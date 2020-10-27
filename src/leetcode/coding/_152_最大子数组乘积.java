package leetcode.coding;

public class _152_最大子数组乘积 {

    public int maxProduct(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[0];
        }
        //需要使用两个dp数组保存当前最大值和最小值
        int[] f = new int[n];   //保存到i之前的最大乘积
        int[] g = new int[n];   //保存到j之前的最大乘积
        f[0] = g[0] = nums[0];
        int res = nums[0];  //注意 如果初始化，res的值不是MIN_VAULE,如果初始化在for循环中，则不用额外考虑

        for (int i = 1; i < n; i++) {
            f[i] = Math.max(nums[i], Math.max(nums[i] * f[i - 1], nums[i] * g[i - 1]));
            g[i] = Math.min(nums[i], Math.min(nums[i] * f[i - 1], nums[i] * g[i - 1]));
            res = Math.max(res, Math.max(f[i], g[i]));
        }
        return res;
    }
}