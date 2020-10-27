package leetcode.coding;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 动态规划思想：
 *      dp[i]表示nums中以nums[i]结尾的最大子序和
 *      那么dp[i]要么续上前面的字串，要吗不续，就是它本身
 *      我们需要个变量来记录最大值
 *
 */
public class _053_最大子序和 {

    //方法一：dp
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int res = dp[0];

        for (int i = 1; i < n; i++) {
            //续和不续
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    // 方法二：贪心

    // 方法三：前缀和，记录前缀和中最小的即可
    public int maxSubArray3(int[] nums) {
        int n = nums.length, sum = 0, min = 0x7fffffff, ans = nums[0];
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            ans = Math.max(ans, sum - min);
            min = Math.min(min, sum);
        }
        return ans;
    }


}