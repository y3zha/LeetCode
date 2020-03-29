package leetcode.coding;

/**
 * dp:  从最后一步出发，如果倒数第二哥位置能跳到最后一个位置，我们只要考虑倒数第二个位置即可，依次往前推
 *      推到最前面就是第一块能否跳到第二块石头
 *
 * 贪心:  如果能到达某个位置，那一定能到达它前面的所有位置。要尽可能到达最远位置（贪心）
 *        初始化最远位置为0，然后遍历数组，如果当前位置能到达，并且当前位置+跳数>最远位置，就更新最远位置。
 *        最后比较最远位置和数组长度。
 */
public class _055_跳跃游戏 {

    //贪心
    public boolean canJump(int[] nums) {
        int max = 0;    //当前能到达的最远位置
        for (int i = 0; i < nums.length; i++) {
            if (max >= i && i + nums[i] > max) {    //如果当前位置可达 && 需要更新
                max = i + nums[i];
            }
        }
        return max >= nums.length - 1;
    }

    //dp
    public boolean canJump2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return true;
        }
        int n = nums.length;
        boolean[] dp = new boolean[n];
        dp[0] = true;

        //当前位置i
        for (int i = 1; i < n; i++) {
            //枚举i前面的位置j
            for (int j = 0; j < i; j++) {
                //如果j位置可达，并且可以跳到i
                if (dp[j] && nums[j] + j >= i) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n - 1];
    }
    
    
    
}