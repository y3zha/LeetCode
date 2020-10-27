package leetcode.interviewbook;

import java.util.HashSet;
import java.util.Set;

public class 面试题01_01_判定字符是否唯一 {

    public boolean isUnique(String s) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                return false;
            } else {
                set.add(s.charAt(i));
            }
        }
        return true;
    }
}