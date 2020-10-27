package leetcode.coding;

import java.util.Arrays;

/**
 *
 * 1、回溯
 * 2、状压dp
 */
public class _526_优美的排列 {

    //回溯比较直接点吧1
    int res = 0;

    public int countArrangement(int N) {
        int[] arr = new int[N];
        Arrays.fill(arr, -1);
        dfs(arr, N);
        return res;
    }

    //数组以及放置数字的个数
    private void dfs(int[] arr, int n) {
        if (n == 0) {
            res++;
            return;
        }
        //枚举每个位置，如果这个位置没有被放置过，且符合要求，就可以尝试放置
        //我这里直接从n开始，当然你可以设置从1开始枚举每个pos到N
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == -1 && (n % (i + 1) == 0 || (i + 1) % n == 0)) {
                arr[i] = i;
                dfs(arr, n - 1);
                arr[i] = -1;
            }
        }
    }

    //状压dp
    // N 是一个正整数，并且不会超过15。
    //思路是这样的，从1到N，一个个枚举数量为x的数字集合的方案数有多少个
    //每个位置都有放了了没放两种状态，我们要枚举，所以是2^n次方
    //所以时间复杂度是 n* 2^n
    //假设现在枚举到了状态 x，看一下它这个状态是否满足题目中的条件，如果满足，那就可以加进去
    //dp[i]表示该状态下，有cnt个元素，这样的优美排列有几种可能
    public int countArrangement2(int N) {
        //开到 1<<N就行
        int[] dp = new int[1 << N];
        //发现得要初始化一下，
        for (int i = 0; i < N; i++) {
            dp[1 << i] = 1;
        }
        //枚举每一种状态
        for (int i = 1; i < 1 << N; i++) {
            //得先保证dp[i]>0，不然是没有意义的
            if (dp[i] > 0) {
                //先统计下放了多少个了
                int pos = 0;
                for (int j = 0; j < N; j++) {
                    if ((i & (1 << j)) != 0) {
                        pos++;
                    }
                }
                //看看下一个位置可不可以放哟
                for (int j = 0; j < N; j++) {
                    //如果这个位置没被放过，并且满足题中两个条件，那就可以放咯
                    if (((i & (1 << j)) == 0) && ((pos + 1) % (j + 1) == 0 || (j + 1) % (pos + 1) == 0)) {
                        dp[i | 1 << j] += dp[i];
                    }
                }
            }
        }
        //注意：减法优先级更高
        return dp[(1 << N) - 1];
    }
}