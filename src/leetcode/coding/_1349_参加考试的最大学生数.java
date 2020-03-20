package leetcode.coding;

import java.util.Arrays;

public class _1349_参加考试的最大学生数 {

    public int maxStudents(char[][] seats) {
        int n = seats.length;
        int m = seats[0].length;
        int[][] dp = new int[n + 1][1 << m];
        // 初始化全部位置为-1表示没访问过
        for (int[] ints : dp) {
            Arrays.fill(ints, -1);
        }
        dp[0][0] = 0;   //初始条件，第0个位置 第0个状态能做的人数肯定为0啊

        //遍历n个位置
        for (int i = 1; i <= n; i++) {
            //第i个位置的状态
            for (int cur = 0; cur < 1 << m; cur++) {
                //第i-1个位置的状态
                for (int pre = 0; pre < 1 << m; pre++) {
                    if (dp[i - 1][pre] == -1) {
                        continue;
                    }
                    //判断当前状态cur能不能坐
                    boolean flag = true;
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
                    if (flag) {
                        dp[i][cur] = Math.max(dp[i][cur], dp[i - 1][pre] + count(cur));
                    }
                }
            }
        }

        int res = 0;
        for (int i = 0; i < 1 << m; i++) {
            res = Math.max(res, dp[n][i]);
        }
        return res;
    }

    private int count(int x) {
        int res = 0;
        while (x > 0) {
            x -= (x & (-x));
            res++;
        }
        return res;
    }

}