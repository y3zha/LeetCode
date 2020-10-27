package leetcode.interviewbook;

public class 面试题08_11_硬币 {

    // 给定数量不限的硬币，币值为25分、10分、5分和1分，编写代码计算n分有几种表示法。
    // 背包，四种物品无限量，背包容量为n，问有几种放法
    public int waysToChange(int n) {
        if (n == 0) return 0;
        int[] coins = {1, 5, 10, 25};
        int[][] dp = new int[4 + 1][n + 1];
        int MOD = (int) (1e9 + 7);

        // 初始化操作
        // 当只有一种硬币时，只有1种表示法
        for (int i = 0; i <= n; i++) {
            dp[1][i] = 1;
        }
        // i种物品，拼成target为0，只有一种表示法
        for (int i = 0; i < 5; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= 4; i++) {
            for (int j = 0; j <= n; j++) {
                //不放
                dp[i][j] = dp[i - 1][j] % MOD;
                //放
                if (j >= coins[i - 1]) {
                    dp[i][j] = (dp[i - 1][j] + dp[i][j - coins[i - 1]]) % MOD;
                }
            }
        }
        return dp[4][n];
    }

    // 简单写
    public int waysToChange2(int n) {
        int MOD = (int)(1e9+7);
        int[] coins = {1,5,10,25};
        // dp[i][j] 代表前i种硬币，组成金额为j的情况有多少种
        int[][] dp = new int[5][n+1];
        dp[0][0] = 1;
        // 前 i 种硬币，组成金额为 0 各有一种方案
        // for(int i = 0; i <= 4; i++){
        //     dp[i][0] = 1;
        // }
        // 只有一种硬币的时候，也就是只有1元的时候，组成金额为j，也只有一种方案
        // for(int j = 0;j <= n; j++){
        //     dp[1][j] = 1;
        // }
        for(int i = 1; i <= 4; i++){
            for(int j = 0; j <= n; j++){
                dp[i][j] = dp[i-1][j] % MOD;
                if(j >= coins[i-1]){
                    dp[i][j] = (dp[i-1][j] + dp[i][j-coins[i-1]]) % MOD;
                }
            }
        }
        return dp[4][n];
    }

    // 优化成两行
    public int waysToChange3(int n) {
        int MOD = (int)(1e9+7);
        int[] coins = {1,5,10,25};
        // dp[i][j] 代表前i种硬币，组成金额为j的情况有多少种
        int[][] dp = new int[2][n+1];
        dp[0][0] = 1;
        int old = 0, now = 0;

        for (int i = 1; i <= 4; i++) {
            old = now;
            now = 1 - now;
            for (int j = 0; j <= n; j++) {
                dp[now][j] = dp[old][j] % MOD;
                if (j >= coins[i - 1]) {
                    dp[now][j] = (dp[old][j] + dp[now][j - coins[i - 1]]) % MOD;
                }
            }
        }
        return dp[now][n];
    }


    // 优化成一行
    public int waysToChange4(int n) {
        if (n == 0) {
            return 0;
        }
        int[] coins = {1, 5, 10, 25};
        int[] dp = new int[n + 1];
        // 初始化 别忘了
        dp[0] = 1;
        int MOD = 1000000007;

        // dp[i][j]只与dp[i-1][j]和dp[i][j-coins[i]]有关，所以完全可以把第一个维度除掉，只用一个一维数组存储
        for (int i = 0; i < coins.length; i++) {
            for (int j = 1; j <= n; j++) {
                if (j >= coins[i]) {
                    dp[j] = (dp[j] + dp[j - coins[i]]) % MOD;
                }
            }
        }
        return dp[n];
    }
}