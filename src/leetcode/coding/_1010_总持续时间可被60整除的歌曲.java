package leetcode.coding;

import com.sun.org.apache.regexp.internal.RE;

import java.util.Map;

public class _1010_总持续时间可被60整除的歌曲 {


    // 超时
    public int numPairsDivisibleBy60(int[] time) {
        int n = time.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((time[i] + time[j]) % 60 == 0) {
                    ans++;
                }
            }
        }
        return ans;
    }

    // 更好的方案是统计余数
    public static int numPairsDivisibleBy602(int[] time) {
        int cnt = 0;
        int[] remain = new int[60];
        for (int i = 0; i < time.length; i++) {
            int re = time[i] % 60;
            // 加自己的位置
            cnt += re == 0 ? remain[0] : remain[60 - re];
            remain[re]++;
        }
        return cnt;
    }


}