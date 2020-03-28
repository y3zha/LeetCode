package leetcode.coding;

/**
 * 给出一个非负整数数组，你最初定位在数组的第一个位置。数组中的每个元素代表你在那个位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 *
 * dp[i]代表从上一个位置调过来所需花的最小次数,感觉和最长上升子序列差不多的意思
 */
public class _045_跳跃游戏II {

    //dp 这样写可以，但是会超时
    public static int jump(int[] A) {
        int n = A.length;
        int[] dp = new int[n];
        dp[0] = 0;

        for (int i = 1; i < n; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (j+ A[j] >= i) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[n - 1];
    }

    //贪心，每次在可跳范围内选择可以使得跳的更远的位置。
    public static int jump2(int[] A) {
        int start = 0, end = 0, jumps = 0;
        while (end < A.length - 1) {
            jumps++;
            int farthest = end; //记录此时能跳的最远的位置
            for (int i = start; i <= end; i++) {
                if (A[i] + i > farthest) {
                    farthest = A[i] + i;
                }
            }
            start++;
            end = farthest;
        }
        return jumps;
    }


    public static void main(String[] args) {
        int[] A = {2, 1, 1, 1, 4};
        jump(A);
    }

}