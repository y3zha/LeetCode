package leetcode.coding;

public class _1230_抛掷硬币 {

    // prob[i] 表示第 i 枚硬币正面朝上的概率。
    // prob 数组的长度就是有多少枚硬币
    // 1. 如果 target >  prob.len，这个概率必定为0
    // 2. 如果 target <=  prob.len 那就是个组合数问题了
    // 组合问题 -> 立马想到了组合数 -> dfs,但是这个概率不等有点难处理
    // 再一看，和背包dp有点像的
    // dp[i][j]表示 前i个硬币中，有j个正面朝上的概率
    // 每个硬币有放和不放两种可能
    public double probabilityOfHeads(double[] prob, int target) {
        int n = prob.length;
        if (target > n) return 0;
        double[][] dp = new double[n + 1][n + 1];
        //初始化第一个
        dp[1][0] = 1 - prob[0];
        dp[1][1] = prob[0];
        // 前i个硬币
        for (int i = 2; i <= n; i++) {
            // 有多少个正面朝上
            for (int j = 0; j <= i; j++) {
                //放
                if (j >= 1) {
                    dp[i][j] += dp[i - 1][j - 1] * prob[i - 1];
                }
                //不放
                dp[i][j] += dp[i - 1][j] * (1 - prob[i - 1]);
            }
        }
        return dp[n][target];
    }

    //https://coordinate.wang/index.php/archives/2693/
}