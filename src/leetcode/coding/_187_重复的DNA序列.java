package leetcode.coding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
 * 编写一个函数，找到十个字符长度的子串，该子串在整个DNA序列中出现次数超过一次。
 */
public class _187_重复的DNA序列 {

    //方法一：最笨的就是用map统计
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        int n = s.length();
        if (n < 10) {
            return res;
        }
        for (int i = 0; i < n - 10 + 1; i++) {
            String sub = s.substring(i, i + 10);
            map.put(sub, map.getOrDefault(sub, 0) + 1);
        }
        for (String key : map.keySet()) {
            if (map.get(key) > 1) {
                res.add(key);
            }
        }
        return res;
    }
}