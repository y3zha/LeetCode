package leetcode.coding;

import java.util.Arrays;
import java.util.HashMap;

public class _1371_每个元音包含偶数次的最长子字符串 {

    // 看到奇偶，要想到异或，偶数次直接抵消！a、e、i、o、u 只出现偶数次，就相当于在这个子串里异或和为 0；
    // 由于要记录最长的符合要求的子串的长度，于是只需要记录第一次出现的“前缀异或和”，以后再次出现的“异或和”的时候
    // 这两个值一样，说明在这之间的字符串就是我们想要的，可以更新答案
    // 因为如果子串[0，i]与字串[0,j]状态相同，那么字串[i+1,j]的状态一定是0
    // 因此可以记录每个状态第一次出现的位置，此后再出现该状态时相减即可。
    // 注意 状态0首次出现的位置应该设定为-1。

    public int findTheLongestSubstring(String s) {
        char[] chs = {'a', 'e', 'i', 'o', 'u' };
        // state- > first idx
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        // 00000
        int state = 0;
        int res = 0;

        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < 5; j++) {
                if (s.charAt(i) == chs[j]) {
                    state ^= (1 << j);
                    // 找到就break
                    break;
                }
            }
            // 如果之前有这个状态了
            if (map.containsKey(state)) {
                // 比如第一个是元音（i=0），map中0为-1，那么就是 0 - (-1) = 1
                res = Math.max(res, i - map.get(state));
            }
            map.putIfAbsent(state, i);
        }
        return res;
    }


    /**
     * 状态压缩 下面这个题解就非常的好
     * https://leetcode-cn.com/problems/find-the-longest-substring-containing-vowels-in-even-counts/solution/zhuang-tai-ya-suo-ji-lu-yuan-yin-zi-mu-chu-xian-qi/
     */

    // 换一种写法
    public int findTheLongestSubstring2(String s) {
        char[] chs = {'a', 'e', 'i', 'o', 'u' };
        int[] dp = new int[1 << 5];
        Arrays.fill(dp, 0x3f3f3f3f);
        dp[0] = -1;
        int state = 0;
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < 5; j++) {
                if (s.charAt(i) == chs[j]) {
                    state ^= (1 << j);
                    break;
                }
            }
            dp[state] = Math.min(dp[state], i);
            res = Math.max(res, i - dp[state]);
        }
        return res;
    }
    // 当然可以从第一个位置开始 dp[0] = 0
    public int findTheLongestSubstring3(String s) {
        char[] chs = {'a', 'e', 'i', 'o', 'u' };
        int[] dp = new int[1 << 5];
        Arrays.fill(dp, 0x3f3f3f3f);
        // 这里改动
        dp[0] = 0;
        int state = 0;
        int res = 0;
        //这里改动
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < 5; j++) {
                if (s.charAt(i) == chs[j]) {
                    state ^= (1 << j);
                    break;
                }
            }
            dp[state] = Math.min(dp[state], i);
            res = Math.max(res, i - dp[state]);
        }
        return res;
    }
}