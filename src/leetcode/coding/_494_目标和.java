package leetcode.coding;

/**
 * DFS 和 01背包
 */
public class _494_目标和 {

    public int findTargetSumWays(int[] nums, int S) {
        return dfs(nums, S, 0);
    }

    private int dfs(int[] nums, int s, int i) {
        if (s == 0 && i == nums.length) {
            return 1;
        }
        if (i == nums.length) {
            return 0;
        }
        int res = 0;
        res += dfs(nums, s + nums[i], i + 1);
        res += dfs(nums, s - nums[i], i + 1);   //看一下437题的dfs
        return res;
    }
}