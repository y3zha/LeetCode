package leetcode.interviewbook;

public class 面试题16_17_连续数列 {

    //dp 对于当前位置上的数字，无非就是放和不放
    public int maxSubArray(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] dp = new int[n+1];
        int max = 0x80000000;
        for(int i = 1; i <= n; i++){
            dp[i] = Math.max(nums[i-1],dp[i-1]+nums[i-1]);
            max = Math.max(dp[i],max);
        }
        return max;
    }
}