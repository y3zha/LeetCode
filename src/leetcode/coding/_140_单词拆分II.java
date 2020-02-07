package leetcode.coding;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 在139题的基础上要求找出路所有的这个句子划分。。
 * 139是判断s能否由wordDict中的单词构成，那么我们可以利用139题，调用那个完成这个题
 *
 * X X X X X X
 * ^     ^   ^
 * 0     j   i
 * 先判断 j 到 i 的字符串在没在 wordDict 中
 * 然后把 0 到 j 的字符串由 wordDict 构成所有情况后边加空格再加上 j 到 i 的字符串即可
 *
 */
public class _140_单词拆分II {

    //dp 发现超时。那我先统计下wordDict中单词的最大长度，超过这个长度我就不看了,,改了之后还是超时的
    //【超时】
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        int n = s.length();
        List<String>[] dp = new List[n + 1];
        //要有空串
        List<String> init = new ArrayList<>();
        init.add("");
        dp[0] = init;

        //统计下wordDict中单词的最大长度
        int maxLen = 0;
        for (String word : wordDict) {
            maxLen = Math.max(maxLen, word.length());
        }
        for (int i = 1; i <= n; i++) {
            List<String> list = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                if (i - j > maxLen) {
                    continue;
                }
                if (set.contains(s.substring(j, i))) {
                    //对dp[j]中所有可能都添加这个串
                    for (String ss : dp[j]) {
                        String temp = ss + " " + s.substring(j, i);
                        list.add(temp.trim());
                    }
                }
            }
            dp[i] = list;
        }
        return dp[n];
    }


    //换个回溯的写法，需要用到map来记忆化搜索
    public List<String> wordBreak2(String s, List<String> wordDict) {
        int maxLen = 0;
        return helper(s, 0, new HashMap<Integer, List<String>>(), wordDict);
    }

    /**
     * 在哈希表中，key 是当前考虑字符串的开始下标， value 包含了所有从头开始的所有可行句子。
     * 下次我们遇到相同位置开始的调用时，我们可以直接从哈希表里返回结果，而不需要重新计算结果。
     */
    private List<String> helper(String s, int start, HashMap<Integer, List<String>> map, List<String> wordDict) {
        if (map.containsKey(start)) {
            return map.get(start);
        }
        //不包含这个start，new一个
        List<String> res = new LinkedList<>();
        if (start == s.length()) {
            res.add("");
        }
        //自顶向下
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end))) {
                for (String ss : helper(s, end, map, wordDict)) {
                    res.add(s.substring(start, end) + (ss.equals("") ? "" : " ") + ss);
                }
            }
        }
        map.put(start, res);
        return res;
    }


    public static void main(String[] args) {
        String s = "catsanddog";
        String[] strs = {"cat", "cats", "and", "sand", "dog"};
        ArrayList<String> list = new ArrayList<>(Arrays.asList(strs));

    }
}