package leetcode.coding;

public class _453_最小移动次数使数组元素相等 {


    // 给n-1个数+1实际上就是给剩下那个数-1，使所有数的差值不变，直到所有的数都变成最小值
    // 只需找出每次让1个元素-1，多少次后所有元素相等
    public int minMoves(int[] nums) {
        int min = nums[0], ans = 0, n = nums.length;
        for (int i = 0; i < n; i++) min = Math.min(min, nums[i]);
        for (int i = 0; i < n; i++) ans += nums[i] - min;
        return ans;
    }
}