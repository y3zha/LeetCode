package leetcode.swordtooffer;

public class 面试题42_连续子数组的最大和 {

    /**
     * 贪心  dp 都可以 ，dp易懂一点
     */

    public int maxSubArray(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int[] f = new int[n];
        f[0] = nums[0];
        int res = f[0];
        for (int i = 1; i < n; i++) {
            f[i] = Math.max(f[i - 1] + nums[i], nums[i]);   //放和不放
            res = Math.max(res, f[i]);
        }
        return res;
    }

}