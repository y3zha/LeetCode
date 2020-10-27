package leetcode.interviewbook;

import java.util.Arrays;
import java.util.Collections;

public class 面试题08_13_堆箱子 {

    // 暴力 dp
    // 和354俄罗斯套娃不一样的是，这边要返回最大的高度而不是堆的箱子个数
    public int pileBox(int[][] box) {
        if (box == null || box.length == 0 || box[0] == null || box[0].length == 0) {
            return 0;
        }
        // [wi, di, hi]表示每个箱子。都升序排列
        Arrays.sort(box, (o1, o2) -> o1[0] == o2[0] ? o1[1] == o2[1] ? o1[2] - o2[2] : o1[1] - o2[1] : o1[0] - o2[0]);
        int n = box.length;
        int[] dp = new int[n];
        int res = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = box[i][2];
            for (int j = 0; j < i; j++) {
                if (box[i][0] > box[j][0] && box[i][1] > box[j][1] && box[i][2] > box[j][2]) {
                    dp[i] = Math.max(dp[i], dp[j] + box[i][2]);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}