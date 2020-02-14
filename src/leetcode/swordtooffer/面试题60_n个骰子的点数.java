package leetcode.swordtooffer;

public class 面试题60_n个骰子的点数 {

    /**
     * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
     * 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
     *
     * 思路： f(n,s)代表n个骰子，和为s的情况
     *      1、考虑只有一个骰子的情况，f(1,1) = f(1,2) = ...= f(1,6)
     *      2、考虑n个骰子，和为s的情况
     *          f(n,s) = f(n-1,s-1) + f(n-1,s-2) + f(n-1,s-3) + f(n-1,s-4) + f(n-1,s-5) +f(n-1,s-6)
     */
    public double[] twoSum(int n) {
        int[][] dp = new int[n + 1][6 * n + 1];
        //初始化一个骰子的情况
        for (int i = 1; i <= 6; i++) {
            dp[1][i] = 1;
        }
        //从第二个骰子开始
        for (int i = 2; i <= n; i++) {
            //它的范围从哪开始到哪结束 2个骰子 2～12 3个骰子 3～18
            for (int j = i; j <= 6 * i; j++) {
                //加它前面的，如果越界就break
                for (int k = 1; k <= 6; k++) {
                    if (j - k < 0) {
                        break;
                    }
                    dp[i][j] += dp[i - 1][j - k];
                }
            }
        }
        double total = Math.pow(6, n);
        double[] res = new double[5 * n + 1];
        int index = 0;
        for (int i = n; i <= 6 * n; i++) {
            res[index++] = dp[n][i] / total;
        }
        return res;
    }
}