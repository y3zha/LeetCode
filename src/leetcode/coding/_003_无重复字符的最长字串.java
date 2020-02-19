package leetcode.coding;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 无重复字符的最长字串
 * 滑动窗口题，利用hashmap，时间复杂度和空间复杂度都是O(n)
 */
public class _003_无重复字符的最长字串 {

    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        int left = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            //如果这个位置i上的字母出现了，需要把左指针移动到与i上字母相同的这个字母 的后一个位置
            if (map.containsKey(s.charAt(i))) {
                //获取已经出现这个字母的位置，并往后挪动1位
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            //把这个字母放进去
            map.put(s.charAt(i), i);
            max = Math.max(max, i - left + 1);
        }
        return max;
    }

    //自己写了一个
    public static int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        char[] sc = s.toCharArray();
        int i = 0,j = 0;
        Set<Character> set = new HashSet();
        int max = 0;
        int count = 0;
        for(i = 0;i < n;i++){
            while(j < n && !set.contains(sc[j])){
                set.add(sc[j]);
                j++;
                count++;
            }
            max = Math.max(max,count);
            //剔除第一个
            set.remove(sc[i]);
            count--;
        }
        return max;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        lengthOfLongestSubstring2(s);

    }

}