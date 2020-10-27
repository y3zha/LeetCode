package leetcode.coding;

public class _265_粉刷房子II {

    //这个题，如果单纯使用和256题一样思路的话，可能是过不去的吧，我试试
    public static int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        int n = costs.length;
        int k = costs[0].length;
        if (k == 1) {
            //如果只有一种颜色，那必定只有一栋房屋
            return costs[0][0];
        }
        int[][] dp = new int[n + 1][k];

        // 初始化，其实可以省略,第0栋房子粉刷的花费是0
        for (int i = 0; i < k; i++) {
            dp[0][i] = 0;
        }

        //枚举每一栋房子
        for (int i = 1; i <= n; i++) {
            //当前这栋房子刷成什么颜色
            for (int j = 0; j < k; j++) {
                dp[i][j] = 0x7fffffff;
                //枚举之前那栋房子刷成什么颜色
                for (int l = 0; l < k; l++) {
                    if (j != l) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][l] + costs[i - 1][j]);
                    }
                }
            }
        }
        int min = 0x7fffffff;
        for (int i = 0; i < k; i++) {
            min = Math.min(min, dp[n][i]);
        }
        return min;
    }


    /**
     * 可以使用最小值和次小值来记录下来即可，进行优化
     */
    public static int minCostII2(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        int n = costs.length;
        int k = costs[0].length;
        if (k == 1) {
            //如果只有一种颜色，那必定只有一栋房屋
            return costs[0][0];
        }
        int[][] dp = new int[n + 1][k];
        // 初始化，其实可以省略,第0栋房子粉刷的花费是0
        for (int i = 0; i < k; i++) {
            dp[0][i] = 0;
        }
        // 最小值和次小值，及其idx
        int min1, min2, idx1 = 0, idx2 = 0;

        for (int i = 1; i <= n; i++) {
            min1 = min2 = Integer.MAX_VALUE;
            // 首先计算下前i-1栋的最小值和次小值
            for (int j = 0; j < k; j++) {
                //如果比最小值还小，首先让次小值拿到当前最小值的值，再去更新最小值
                if (dp[i - 1][j] < min1) {
                    min2 = min1;
                    idx2 = idx1;
                    min1 = dp[i - 1][j];
                    idx1 = j;
                } else if (dp[i - 1][j] < min2) {
                    // 如果只是比次小值小，只要更新次小值即可
                    min2 = dp[i - 1][j];
                    idx2 = j;
                }
            }
            
            // 更新当前房屋
            for (int j = 0; j < k; j++) {
                // 如果和最小值的颜色不一样，就可以直接拿最小值的颜色来更新，如果一样就可以拿次小值来更新
                if (j != idx1) {
                    dp[i][j] = min1 + costs[i - 1][j];
                } else {
                    dp[i][j] = min2 + costs[i - 1][j];
                }
            }
        }
        
        // 查找结果
        int res = Integer.MAX_VALUE;
        for (int i = 0; i <k; i++) {
            res = Math.min(res, dp[n][i]);
        }
        return res;
    }





    public static void main(String[] args) {
        int[][] a = {{8}};
        minCostII(a);
    }

}