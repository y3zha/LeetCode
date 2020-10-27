package leetcode.coding;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 模拟类题目
 * 【问题】
 * 给出一组 n 个不同的非空字符串，您需要按以下规则为每个单词生成 最小的缩写。 like->l2e
 * 从第一个字符开始，然后加上中间缩写掉的字符的长度，后跟最后一个字符。
 * 如果有冲突，就是多个单词共享相同的缩写，使用较长的前缀，而不是仅使用第一个字符，直到使单词的缩写的映射变为唯一。 换句话说，最终得到的缩写不能映射到多个原始单词。
 *      比如linxxe和likxxe-> lin2e lik2e
 * 如果缩写不会使单词更短，则不进行缩写，保持原样。 比如me
 *
 * 【开始模拟】
 * ["like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"]
 * 第一步 变为[l2e,god,i6l,me,i6t,i6l,i7n,f2e,i7n]
 * 第二步 看重复的i6l和i7n，针对重复的再模拟，in5l，in5l，in6n，in6n
 *          还是重复，再模拟int4l，int4l，int5n，int5n
 *          还是重复，再模拟inte3l，inte3l，inte4n，intr4n，后面两个ok了，再搞前面的
 *          ....
 * 最终结果
 * ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]
 *
 * 如何判断重复？ hash
 */
public class _527_单词缩写 {

    public List<String> wordsAbbreviation(List<String> dict) {
        if (dict.isEmpty()) return new ArrayList<>();

        // abbr -> cnt
        int n = dict.size();
        String[] ans = new String[n];
        Map<String, Integer> map = new HashMap<>();
        // 记录当前是第几轮，前缀长度是多少
        int round = 1;

        // 获取初始的缩写
        for (int i = 0; i < n; i++) {
            ans[i] = getAbbr(dict.get(i), round);
            map.put(ans[i], map.getOrDefault(ans[i], 0) + 1);
        }

        while (true) {
            boolean unique = true;
            round++;
            for (int i = 0; i < n; i++) {
                if (map.get(ans[i]) > 1) {
                    unique = false;
                    ans[i] = getAbbr(dict.get(i), round);
                    map.put(ans[i], map.getOrDefault(ans[i], 0) + 1);
                }
            }
            if (unique) {
                break;
            }
        }
        return Arrays.stream(ans).collect(Collectors.toList());
    }

    /**
     * @param s
     * @param len 前缀长度
     * @return
     */
    private String getAbbr(String s, int len) {
        int n = s.length();
        // 如果前缀长度加一个后缀、加一个数字大于等于本身长度，直接返回
        if (len + 2 >= n) {
            return s;
        }
        return s.substring(0, len) + (n - len - 1) + s.charAt(n - 1);
    }
}