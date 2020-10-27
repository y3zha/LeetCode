package leetcode.coding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 思路一:接近TLE的方法
 * 扫描每个word的prefix和suffix，如果 prefix是回文的话， 只要suffix的回文在words中， 就一定能凑成回文。
 * 举个例子：abbaba，前缀abba是回文，后缀是ba，如果后缀的回文"ab"在words中，拼起来就是ababbaba。反过来suffix是回文，只要prefix的回文在words中也是一样成立的
 * 找到一个规律是：如果prefix是回文串，要把suffix的回文串放前面整体才能构成回文串，如果suffix是回文串，要把prefix的回文串放后面整体才是回文串。
 * 注意点：因为prefix和suffix可以包括空集和本身word， 添加时需要避免重复，要限制 prefix或者suffix其中至少一个不为空
 */
public class _336_回文对 {

    //接近TLE
    public static List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        if (words == null) {
            return res;
        }
        Map<String, Integer> map = new HashMap<>(); //word -> index
        for (int i = 0; i < words.length; i++) {
            map.putIfAbsent(words[i], i);
        }
        for (String word : map.keySet()) {
            Integer index = map.get(word);
            //枚举prefix和suffix
            for (int i = 0; i <= word.length(); i++) {
                String prefix = word.substring(0, i);       //一开始写的(0,i+1)，发现uc中有空串..
                String suffix = word.substring(i);
                String rPrefix = new StringBuilder(prefix).reverse().toString();
                String rSuffix = new StringBuilder(suffix).reverse().toString();
                if (prefix.equals(rPrefix)) {
                    //如果有rSuffix且不是单词自身(因为prefix可能为空串，如果是单词自身是不可以的),比如【"a","b"】
                    if (map.containsKey(rSuffix) && !rSuffix.equals(word)) {
                        List<Integer> list = new ArrayList<>();
                        list.add(map.get(rSuffix)); //先添加suffix的回文串，再添加prefix的index
                        list.add(index);
                        res.add(list);
                    }
                }
                //不要重复添加自身
                if (i != word.length() && suffix.equals(rSuffix)) {
                    if (map.containsKey(rPrefix)) {
                        List<Integer> list = new ArrayList<>();
                        list.add(index);    //后添加prefix的回文串的index
                        list.add(map.get(rPrefix));
                        res.add(list);
                    }
                }
            }
        }
        return res;
    }




}