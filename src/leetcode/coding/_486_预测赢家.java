package leetcode.coding;

import java.lang.reflect.Array;
import java.util.Arrays;

public class _486_预测赢家 {

    /**
     * 零和博弈问题的关键都是要找出如何让后手陷入更差的局面的方法
     * optimal(L, R) 为在 nums[L:R] 这种局面上，先手的最优得分。
     * 这里的先手抛开了自己或者对手的概念，先手就是指在这种局面上第一个出手的那个人
     * 当 L == R 时，因为只有一个数字，显然不需要做抉择了
     * 当 L < R 时，先手有两种选择
     *      先手选择 nums[L], 让该回合的后手进入 optimal(L+1, R) 局面。
     *      先手选择 nums[R], 让该回合的后手进入 optimal(L, R-1) 局面。 optimal(L, R) = sum(L, R) - min(optimal(L+1, R), optimal(L, R-1))。
     */
    int[][] dp;

    public boolean PredictTheWinner(int[] nums) {
        int n = nums.length;
        dp = new int[n][n];
        for (int[] a : dp) {
            Arrays.fill(a, -1);
        }
        return dfs(nums, 0, n - 1) * 2 >= sum(nums, 0, n - 1);

    }

    private int dfs(int[] nums, int l, int r) {
        if (dp[l][r] != -1) {
            return dp[l][r];
        }
        if (l == r) {
            return dp[l][r] = nums[l];
        }
        return dp[l][r] = sum(nums, l, r) - Math.min(dfs(nums, l + 1, r), dfs(nums, l, r - 1));
    }

    private int sum(int[] nums, int l, int r) {
        int sum = 0;
        for (int i = l; i <= r; i++) {
            sum += nums[i];
        }
        return sum;
    }


    /**
     * 记忆化搜索改递推
     * dp[i][j] 表示两个玩家在数组 i 到 j 区间内游戏能赢对方的差值（i <= j）。
     * 假如玩家1先取左端 nums[i]，那么玩家2能赢对方的差值是dp[i+1][j] ，如果玩家1先取取右端 nums[j]，玩家2能赢对方的差值就是dp[i][j-1]
     *
     * dp[i][j] = max((nums[i] - dp[i + 1][j]), (nums[j] - dp[i][j - 1]));
     * 初始化，首先当i == j的时候，nums[i]就是dp[i][j]的值
     * 最终求是dp[0][nums.size() - 1]是否大于等于0
     */
    public boolean PredictTheWinner2(int[] nums) {
        int n = nums.length;
        int[][] f = new int[n][n];
        for (int i = 0; i < n; i++) {
            f[i][i] = nums[i];
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                f[i][j] = Math.max(nums[i] - f[i + 1][j], nums[j] - f[i][j - 1]);
            }
        }
        return f[0][n - 1] >= 0;
    }

}