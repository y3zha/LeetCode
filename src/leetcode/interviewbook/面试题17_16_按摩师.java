package leetcode.interviewbook;

public class 面试题17_16_按摩师 {

    //总预约时间最长，这个和那个偷金币的差不多
    //序列+状态
    public int massage(int[] nums) {
        int n = nums.length;
        //0代表偷，1代表不偷
        int[][] dp = new int[n + 1][2];
        //初始化第0个
        dp[0][0] = dp[0][1] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i][0] = dp[i - 1][1] + nums[i - 1];
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1]);
        }
        return Math.max(dp[n][0], dp[n][1]);
    }

    //压缩成一维

    public int massage2(int[] nums) {
        int n = nums.length;
        if(n == 0) return 0;
        if (n == 1) return nums[0];
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        return dp[n];
    }

}