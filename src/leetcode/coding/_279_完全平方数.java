package leetcode.coding;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *
 * 思路：
 * 1、dp,dp[i]代表数字i时最少个数 dp[i] = min(dp[i],dp[i-j*j]+1)
 * 2、bfs,最少，最短路径，都要考虑bfs
 *      比如11，他能拆解成10、7、2，就把这三个offer进队列，什么时候能offer 0了，那就是最短路径
 */
public class _279_完全平方数 {

    //dp
    public int numSquares2(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = i;  //最坏情况 1+1+1...+1
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

    //bfs
    public int numSquares(int n) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(n);
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            step++;
            for (int i = 0; i < size; i++) {
                Integer num = queue.poll();
                for (int j = 1; j < Math.sqrt(num) + 1; j++) {
                    if (num - j * j == 0) {
                        return step;
                    }
                    queue.offer(num - j * j);
                }
            }
        }
        return step;
    }
}