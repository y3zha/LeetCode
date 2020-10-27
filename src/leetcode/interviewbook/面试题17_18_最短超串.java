package leetcode.interviewbook;

import java.util.HashMap;
import java.util.Map;

public class 面试题17_18_最短超串 {

    /**
     * 滑动窗口，和超级短串的解法一模一样
     */
    public String minWindow(String s, String t) {
        // t 串长度，这也是一个判断是否结束的标准
        int cnt = t.length();
        // 统计 t 串中的字符
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < cnt; i++) {
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);
        }

        // 滑动窗口
        int i = 0, j = 0, n = s.length();
        // 记录起点和符合条件的长度
        int st = 0, len = Integer.MAX_VALUE;

        while (j < n) {
            // 判断是否符合条件，来cnt--
            char c = s.charAt(j);
            if (map.getOrDefault(c, 0) > 0) {
                cnt--;
            }
            // 这一步要放在外面,如果我们的t串是abc，而s中有一串 aaaaaabc，我们找到的时候是这一串，但前面很多a我们是不需要的
            // 让他减就好，等到cnt为0了，如果是负的就往前走即可，-2表示有两个是多余的，-3表示有3个是多余的，一直走带我们要的那个即可
            map.put(c, map.getOrDefault(c, 0) - 1);

            // 如果符合条件
            if (cnt == 0) {
                // 先把小于0的填充
                while (i < j && map.getOrDefault(s.charAt(i), 0) < 0) {
                    map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
                    i++;
                }
                // 可以更新结果了
                if (j - i + 1 < len) {
                    len = j - i + 1;
                    st = i;
                }
                // 移动i
                map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
                cnt++;
                i++;
            }
            j++;
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(st, st + len);
    }
}