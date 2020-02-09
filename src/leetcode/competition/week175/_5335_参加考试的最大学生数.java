package leetcode.competition.week175;

import java.util.Arrays;

/**
 * 也就是学生不能坐在左右相邻位置，它左上右上不能有人
 *
 * 这个题是状态压缩dp，可以参考 https://www.cnblogs.com/ibilllee/p/7651971.html
 *
 * 从题意分析，当前行的状态只和上一行有关，假设有m列，那么状态最多有2^m种（每个位置坐和不坐），即1<<m
 * 怎么表示状态是个关键问题，这就要用到状态压缩了，用二进制来表示某个状态比如 0101，就代表1、3列不坐，2、4列坐
 * 这样省代码也省空间
 *
 * 每一行只与上一行有关，同时每一行最多2^82
 * 8
 *  种状态，我们自然想到进行状态压缩DP。
 *
 * 状态转移方程为：
 *
 * dp[row][state] = max(dp[row-1][last] + state.count())
 * 注意我们需要检查合法性，这里包括：
 *
 * 1、本行的合法性：不能把学生安排在坏座位上；不能有相邻的学生
 * 2、两行之间的合法性：如果第一行某个位置安排了学生，则下一行斜向的两个位置不能安排学生
 *
 * 最后的结果就是max(dp[m][state])
 *
 *
 */
public class _5335_参加考试的最大学生数 {

    public int maxStudents(char[][] seats) {
        int n = seats.length;
        int m = seats[0].length;
        //dp[i][bits] 代表前i行，作为情况为bits的最大答案
        //bits就是个01串，这里题目中最多为8位，比如00100101，1表示有人坐，0表示没人坐
        int[][] dp = new int[10][1 << 8];
        //初始化
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        dp[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            //当前状态是什么
            for (int cur = 0; cur < 1 << m; cur++) {
                //前一行状态是什么
                for (int pre = 0; pre < 1 << m; pre++) {
                    //如果前一行状态为-1，不看
                    if (dp[i - 1][pre] == -1) {
                        continue;
                    }
                    boolean flag = true;
                    //判断能不能坐
                    for (int j = 0; j < m; j++) {
                        //如果当前这位是0，是符合的，继续
                        if (((cur >> j) & 1) == 0) {
                            continue;
                        }
                        //如果当前行这个位置是坏位置，那肯定不能坐
                        if (seats[i - 1][j] == '#') {
                            flag = false;
                            break;
                        }
                        //如果当前行、当前位置的左右有了，那不能坐
                        if (j + 1 < m && ((cur >> (j + 1)) & 1) == 1) {
                            flag = false;
                            break;
                        }
                        if (j - 1 >= 0 && ((cur >> (j - 1)) & 1) == 1) {
                            flag = false;
                            break;
                        }
                        //当前行的左上右上有人做了，也不能坐
                        if (j >= 1 && ((pre >> (j - 1)) & 1) == 1) {
                            flag = false;
                            break;
                        }
                        if (j + 1 < m && ((pre >> (j + 1)) & 1) == 1) {
                            flag = false;
                            break;
                        }
                    }
                    //不能坐
                    if (!flag) {
                        continue;
                    }
                    //能坐
                    dp[i][cur] = Math.max(dp[i][cur], dp[i - 1][pre] + bitCount(cur));
                }
            }
        }
        int res = 0;
        for (int i = 0; i < 1 << m; i++) {
            res = Math.max(res, dp[n][i]);
        }
        return res;
    }

    //计算当前这一行坐了多少人
    private int bitCount(int x) {
        int res = 0;
        while (x > 0) {
            res++;
            x -= lowbit(x);
        }
        return res;
    }

    private int lowbit(int x) {
        return x & (-x);
    }
}