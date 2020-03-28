package leetcode.coding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 要由words中所有单词串联形成子串，顺序可以不一样，然后找出这个子串在s中的起始位置
 * 这里来要注意的是，words里的词是可以重复的
 *
 * 思路
 *      其实就是把words中所有单词拼起来，这就是窗口的长度，把窗口内所有的情况都列出来，找有没有都包括words中的单词
 *      由于要找窗口内的子word，要看这个词是否在字典中，用hash。为words建立一个map，key为word，value为这个word出现的次数
 *      这个窗口也维护一个map，看单词出现次数
 *
 *      由于words中的单词长度是一样的，窗口大小就是words.len * words[0].len
 *
 *      //有点像滑动窗口 但没这么写
 */
public class _030_串联所有单词的子串 {

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (words == null || s == null || words.length == 0 || s.length() == 0) {
            return res;
        }
        Map<String, Integer> wordsMap = new HashMap<>();
        for (String word : words) {
            wordsMap.put(word, wordsMap.getOrDefault(word, 0) + 1);
        }
        int len = words[0].length();
        int window = words.length * len; //窗口大小
        Map<String, Integer> tempMap = new HashMap<>();
        for (int i = 0; i < s.length() - window + 1; i++) {
            tempMap.clear();
            int j = 0;
            for (; j < window; j += len) {
                String temp = s.substring(i + j, i + j + len);
                //如果存在这个词，放进map，如果不存在直接break掉
                if (wordsMap.containsKey(temp)) {
                    if (tempMap.containsKey(temp)) {
                        //如果数量大于words中的数量也要break
                        tempMap.put(temp, tempMap.get(temp) + 1);
                        if (tempMap.get(temp) > wordsMap.get(temp)) {
                            break;
                        }
                    } else {
                        tempMap.put(temp, 1);
                    }
                } else {
                    break;
                }
            }
            if (j == window) {
                //如果顺利润性到窗口长度了，就说明这一段是ok的
                res.add(i);
            }
        }
        return res;
    }
}