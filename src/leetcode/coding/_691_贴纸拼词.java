package leetcode.coding;

import java.util.Arrays;
import java.util.HashMap;

public class _691_贴纸拼词 {
    // 和这个题相似的是1125

    // 每个贴片都有无数个，最少用几个贴片能拼出目标值target

    /*
    参考题解：https://www.cnblogs.com/grandyang/p/8468045.html


    大致理一下思路：
    target 给的数据范围是[1,15]，长度为 n，对于前i个字符，它能够构成的子几个有2^i个
    比如 "abcdefg",前三个字符，"abc"，它能构成的额子集合有8个，""、a、b、c、"ab"、"ab"、"bc"、"abc"
    每一种子集合都对应一种状态
       000     ""
       100     a
       010     b
       001     c
       110     ab
       101     ac
       011     bc
       111     abc
   只要把每个子集合的dp值算出来,dp[i]表示该状态下（子集合）所需要最少的贴片的个数，结果就是dp[1 << n - 1]

   初始化： 除了dp[0] = 0，表示空字符串的时候，不需要贴片，其余初始化为INF


    ps: 我们想看哪个字符，就提取出该字符对应的二进制位，提取方法就是 cur >> k，表示cur向右移动k位
        提出出来的值与上1就知道该位是0还是1了，如果是0，表示缺失，那么把该位变为1，通过 cur |= 1 << k来实现

     */
    public int minStickers(String[] stickers, String target) {
        int n = target.length();
        int INF = 0x3f3f3f3f;
        int[] dp = new int[1 << n];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int i = 0; i < 1 << n; i++) {
            // 如果该子集合不能够被拼出来，那么我们也无法在它的基础上去拼
            if (dp[i] == INF) {
                continue;
            }
            // 能够拼出来，遍历所有的贴片
            for (String sticker : stickers) {
                // 初始化当前状态 cur，我们尝试去改变状态
                int cur = i;
                // 遍历这个贴片的每个字符
                for (int j = 0; j < sticker.length(); j++) {
                    // 遍历 target 的每个字符
                    for (int k = 0; k < n; k++) {
                        // 如果这两个字符一致，并且当前状态下该位置还没被放上，还是缺失的，那可以放
                        if (sticker.charAt(j) == target.charAt(k) && ((cur >> k) & 1) == 0) {
                            cur |= 1 << k;
                            // 这个位置搞定可以直接 break 掉，继续下一个位置
                            break;
                        }
                    }
                }
                // 对于cur这个状态，就可以更新所需的最少贴片数了
                dp[cur] = Math.min(dp[cur], dp[i] + 1);
            }
        }
        // ➖优先级更高
        return dp[(1 << n) - 1] == INF ? -1 : dp[(1 << n) - 1];
    }
    // 类似完全背包
    // https://leetcode-cn.com/problems/stickers-to-spell-word/solution/bei-bao-wen-ti-zhuang-tai-ya-suo-dpjava-by-henryle/






    /**
     * 记忆化搜索
     */
    HashMap<String, Integer> map;
    public int minStickers2(String[] stickers, String target) {
        int m = stickers.length;
        int[][] mat = new int[m][26];
        map = new HashMap<>();
        map.put("", 0);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < stickers[i].length(); j++) {
                mat[i][stickers[i].charAt(j) - 'a']++;
            }
        }
        return dfs(mat, target);
    }

    private int dfs(int[][] mat, String target) {
        // 如果有这个target了，返回其值，否则就开始就算
        if (map.containsKey(target)) {
            return map.get(target);
        }
        // 首先统计出此时的target字符串的各个字母出现次数
        int[] cnt = new int[26];
        System.out.println(target);
        for (int i = 0; i < target.length(); i++) {
            cnt[target.charAt(i) - 'a']++;
        }
        int res = Integer.MAX_VALUE;
        // 然后我们遍历统计所有sticker中各个字母出现次数的数组
        for (int i = 0; i < mat.length; i++) {
            // 如果target字符串的第一个字母不在当前sticker中，我们直接跳过
            if (mat[i][target.charAt(0) - 'a'] == 0) {
                continue;
            }
            StringBuilder temp = new StringBuilder();
            for (int j = 0; j < 26; j++) {
                int a = cnt[j] - mat[i][j];
                if (a > 0) {
                    for (int k = 0; k < a; k++) {
                        // 这里注意写法
                        temp.append((char) (j + 'a'));
                    }
                }
            }
            int ans = dfs(mat, temp.toString());
            if (ans != -1) {
                res = Math.min(res, ans + 1);
            }
        }
        if (res == Integer.MAX_VALUE) {
            map.put(target, -1);
        } else {
            map.put(target, res);
        }
        return map.get(target);
    }

}