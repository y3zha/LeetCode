package leetcode.coding;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _1140_石子游戏II {

    /*
    本题难点在于理解两者都发挥“最佳水平”，“最佳水平”在于，每当轮到自己拿石子的时候，要在后继的所有状态中，选择对自己最有利的
    那么也就是要遍历后继的所有状态，并选择一个最优解。我们设 dfs(i, M) 表示
    当从第 i 堆石子开始拿，允许拿 M <= x <= 2 * M 时，在剩余石子中所能拿到的最大值，那么我们最终要返回的结果就是 dfs(0, 1)
    https://leetcode-cn.com/problems/stone-game-ii/solution/javadong-tai-gui-hua-by-ethan97/

    使用后缀和数组避免重复计算
     */

    // 后缀和数组
    int[] ss;
    int n;
    // memo
    int[][] f;

    public int stoneGameII(int[] piles) {
        n = piles.length;
        // 求后缀和数组
        ss = new int[n];
        ss[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            ss[i] = ss[i + 1] + piles[i];
        }
        f = new int[n][n];
        return dfs(0, 1);
    }

    /**
     * @param i 当前第几堆
     * @param M
     * @return
     */
    private int dfs(int i, int M) {
        if (f[i][M] != 0) {
            return f[i][M];
        }
        // 如果全都拿完了
        if (i == n) {
            return 0;
        }
        // 如果可以拿走剩下的
        if (i + 2 * M >= n) {
            return ss[i];
        }
        // 尝试拿x堆，x属于 1～2M，先手要在这些可能中拿到最多的，那么就是后手要拿的最少，我们要求出后手拿的最少的石头有几个
        int minStone = Integer.MAX_VALUE;
        for (int x = 1; x <= 2 * M; x++) {
            minStone = Math.min(minStone, dfs(i + x, Math.max(x, M)));
        }
        return f[i][M] = ss[i] - minStone;
    }

    // 写成dp递推式子,需要从后往前推， f[i][j]代表从第i堆开始拿M为j堆时候的情况
    public int stoneGameII2(int[] piles) {
        int n = piles.length;
        int[][] f = new int[n + 1][n + 1];

        int sum = 0;
        // 从第i堆开始拿
        for (int i = n - 1; i >= 0; i--) {
            sum += piles[i];

            for (int M = 1; M <= n; M++) {
                if (i + 2 * M >= n) {
                    f[i][M] = sum;
                    continue;
                }
                // 最多拿 n - i堆
                for (int x = 1; x <= 2 * M; x++) {
                    f[i][M] = Math.max(f[i][M], sum - f[i + x][Math.max(x, M)]);
                }
            }
        }
        return f[0][1];
    }

}