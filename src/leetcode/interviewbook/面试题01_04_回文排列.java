package leetcode.interviewbook;

import java.util.HashSet;
import java.util.Set;

public class 面试题01_04_回文排列 {

    /**
     * 一个特别好的方法，非常巧妙
     * 如果每个字符出现次数为偶数，或者只有一个字符出现次数为奇数，那必定能构成回文串
     */

    public boolean canPermutePalindrome(String s) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (set.contains(c)) {
                set.remove(c);
            } else {
                set.add(c);
            }
        }
        return set.size() <= 1;
    }
}