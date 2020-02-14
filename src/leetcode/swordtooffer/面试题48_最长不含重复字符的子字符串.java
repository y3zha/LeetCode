package leetcode.swordtooffer;

import java.util.HashSet;
import java.util.Set;

public class 面试题48_最长不含重复字符的子字符串 {


    /**
     * 滑动窗口呗
     */

    public int lengthOfLongestSubstring(String s) {
        char[] sc = s.toCharArray();
        int n = sc.length;
        int i = 0, j = 0;
        Set<Character> set = new HashSet<>();
        int max = 0;
        for (i = 0; i < n; i++) {
            while (j < n) {
                if (!set.contains(sc[j])) {
                    set.add(sc[j]);
                    j++;
                    continue;
                }
                break;
            }
            max = Math.max(max, set.size());
            set.remove(sc[i]);
        }
        return max;
    }


}