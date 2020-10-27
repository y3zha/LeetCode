package leetcode.coding;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个字符串 s 和一个 非空字符串 p ，找到在 s 中所有关于 p 的字谜的起始索引。
 * 字符串仅由小写英文字母组成，字符串 s 和 p 的长度不得大于 40,000。
 * 输出顺序无关紧要。
 *
 * 滑动窗口+字符串
 * 相当于一个长度为len 的sliding window 从左往右扫一遍
 * 每次只增加一个右边元素 && 减少一个左边的元素
 *
 * 做完这个题做lc 567题
 */
public class _438_找到字符串中所有字母异位词 {

    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s.length() < p.length()) {
            return res;
        }
        int[] arr = new int[26];
        for (int i = 0; i < p.length(); i++) {
            arr[p.charAt(i) - 'a']++;
        }
        int i = 0, j = 0;
        int k = p.length(); //窗口大小
        int count = 0;  //当前窗口大小
        int matched = 0;
        for (i = 0; i < s.length() - k + 1; i++) {
            while (j < s.length() && count < k) {
                arr[s.charAt(j) - 'a']--;   //先让它自减，如果本来是0，那就变为-1，只要>=0说明匹配了一个
                if (arr[s.charAt(j) - 'a'] >= 0) {
                    matched++;
                }
                j++;
                count++;
            }
            //此时count为k
            if (count == k) {
                if (matched == k) {
                    res.add(i);
                }
            }
            arr[s.charAt(i) - 'a']++;
            if (arr[s.charAt(i) - 'a'] > 0) {
                matched--;
            }
            count--;
        }
        return res;
    }

    public static void main(String[] args) {
        findAnagrams("abab", "ab");  //0,6
    }
}