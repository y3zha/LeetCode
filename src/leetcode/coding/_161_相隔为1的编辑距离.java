package leetcode.coding;

public class _161_相隔为1的编辑距离 {

    // 拿到s串和t串的长度，如果长度之差大于 1，编辑距离必定不为 1，或者是相等的情况 返回false
    // 如果长度差 1，两根指针遍历即可，一个个匹配
    // 如果长度差为0，就只要看是否只有一个位置不一样

    public boolean isOneEditDistance(String s, String t) {
        if (Math.abs(s.length() - t.length()) > 1) {
            return false;
        }
        // 令s串为长串，t串为短串，方便遍历
        if (s.length() < t.length() || s.equals(t)) {
            String temp = s;
            s = t;
            t = temp;
        }
        int n = s.length();
        int m = t.length();
        if (n - m == 1) {
            // 遍历短串
            for (int i = 0; i < m; i++) {
                if (s.charAt(i) != t.charAt(i)) {
                    return s.substring(i + 1).equals(t.substring(i));
                }
            }
        } else {
            int cnt = 0;
            int i = 0;
            while (i < n) {
                if (s.charAt(i) != t.charAt(i)) {
                    cnt++;
                    i++;
                }
            }
            return cnt == 1;
        }
        // "a" 与"" 返回true
        return false;
    }
}