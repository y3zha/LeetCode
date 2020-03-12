package leetcode.coding;

/**
 *
 */
public class _1025_除数博弈 {

    /**
     * 归纳法
     * 最终结果应该是占到 2 的赢，占到 1 的输；
     * 若当前为奇数，奇数的约数只能是奇数或者 1，因此下一个一定是偶数；
     * 若当前为偶数， 偶数的约数可以是奇数可以是偶数也可以是 1，因此直接减 1，则下一个是奇数；
     * 因此，奇则输，偶则赢。直接:
     */
    public boolean divisorGame(int N) {
        return N % 2 == 0;
    }


    /**
     * dp思路
     * dp[i]代表当数字为i时，先手赢还是输 dp[i-x]代表先手的人选了x后，后手人是输还是赢
     * 所以我们要枚举，如果x满足 i % x == 0  且 dp[i-x]为false 那么此时先手就能赢
     */
    public boolean divisorGame2(int N) {
        if (N ==1) return false;
        if (N == 2) return true;
        boolean[] dp = new boolean[N + 1];
        dp[1] = false;
        dp[2] = true;

        for (int i = 3; i <= N; i++) {
            dp[i] = false;
            for (int j = 1; j < i; j++) {
                if (i % j == 0 && !dp[i - j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[N];
    }
}