package leetcode.coding;

import java.util.Arrays;
import java.util.List;

/**
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * 可以重复使用字典中的单词。可以假设字典中没有重复的单词。
 *
 * 感觉是划分型dp
 *
 *
 */
public class _139_单词拆分 {

    /**
     * 方法一：暴力搜索————超时
     * 时间复杂度：O(n^n)，每一个前缀都在字典中，此时回溯树的复杂度会达到 n^n
     *      每次遍历都会无限递归直到 s == length, 遍历为 N，递归 了N次
     * 空间复杂度：O(n) ，回溯树的深度最深达到 n 。
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        return dfs(s, wordDict, 0);
    }

    private boolean dfs(String s, List<String> wordDict, int starIndex) {
        if (starIndex == s.length()) {
            return true;
        }
        for (int endIndex = starIndex + 1; endIndex <= s.length(); endIndex++) {
            if (wordDict.contains(s.substring(starIndex, endIndex)) && dfs(s, wordDict, endIndex)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 方法二：记忆化搜索
     * 对暴力剪枝，我们的暴力是这样的，当这个startIndex不行后，后移，后面还会再去搜前面的
     * 时间复杂度：O(n^2)回溯树的大小最多达到 n^2
     *      把递归的时间复杂度n*(n - 1)*(n - 2)...*2*1，即O（n^n）变为了n + (n - 1) + (n - 2) +... + 2 + 1、？
     * 空间复杂度：O(n)。回溯树的深度可以达到 n 级别。
     */

    public boolean wordBreak_memo(String s, List<String> wordDict) {
        Boolean[] memo = new Boolean[s.length()];
        return dfs2(s, wordDict, 0, memo);
    }

    private boolean dfs2(String s, List<String> wordDict, int start, Boolean[] memo) {
        if (start == s.length()) {
            return true;
        }
        if (memo[start] != null) {
            return memo[start];
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end)) && dfs2(s, wordDict, end, memo)) {
                return memo[start] = true;
            }
        }
        return memo[start] = false;
    }

    /**
     * 区间型dp，dp[i][j]代表从第i哥字符到第j哥字符能否由wordDict中的 单词构成
     * 这个题比较特殊，它的i是固定的，是0，所以我们只需要考虑右端点即可
     * dp[i] 代表区间[0,i）内的字符串 能否由 wordDict 中的单词构成
     *
     * 状态转移方程:dp[i] = dp[j] && wordDict.contains(s.sub(j,i))， dp[j]为前面的结果，j到i为新的单词
     * 初始值，dp[0] = true,空串可以
     */

    public boolean wordBreak_dp(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        //枚举当前位置i
        for (int i = 1; i <= n; i++) {
            //枚举i前面的位置j
            for (int j = 0; j < i; j++) {
                dp[i] = dp[j] && wordDict.contains(s.substring(j, i));
                //只要为true，就能break了
                if (dp[i]) {
                    break;
                }
            }
        }
        return dp[n];
    }

}