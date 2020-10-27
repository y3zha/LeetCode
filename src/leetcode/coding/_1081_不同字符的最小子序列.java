package leetcode.coding;

import java.util.*;

public class _1081_不同字符的最小子序列 {

    /**
     * 和 316 题一模一样，单调栈解法见 316题
     */
    public String smallestSubsequence(String s) {
        int n = s.length();
        Deque<Character> dq = new LinkedList<>();
        Set<Character> set = new HashSet<>();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (!set.contains(c)) {
                while (!dq.isEmpty() && c < dq.peekLast() && map.get(dq.peekLast()) > 0) {
                    set.remove(dq.pollLast());
                }
                set.add(c);
                dq.offerLast(c);
            }
            map.put(c, map.get(c) - 1);
        }
        StringBuilder sb = new StringBuilder();
        while (!dq.isEmpty()) {
            sb.append(dq.pollFirst());
        }
        return sb.toString();
    }

}