package leetcode.coding;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class _564_寻找最近的回文数 {

    /*
    总结下特殊情况：

    1. 小于等于10的数，返回n-1
    2. 10的幂，返回n-1
    3. 若干个9，返回n+2
    4. 11，这个数字比较特殊，返回9
    接下来是普通情况怎么求解

    首先把n从中间分成a、b两部分，如果长度是奇数就给a多分点。
    然后用a、a+1、a-1为左边分别构建一个回文数，注意n长度为奇数的时候左边的最后一个字符不能复制过去。
    最后选取离n最近且不为n的结果即可
     */
    public String nearestPalindromic(String s) {
        long num = Long.parseLong(s);
        if (num < 10) return String.valueOf(num - 1);
        if (num == 11) return "9";
        int nine = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '9') {
                nine++;
            }
        }
        if (nine == s.length()) return String.valueOf(num + 2);
        // 处理10的幂次
        if (num % Math.pow(10, s.length() - 1) == 0) {
            return String.valueOf(num - 1);
        }
        int n = s.length();
        long val = Long.parseLong(s.substring(0, (n + 1) / 2));
        // 先把前半部分做自增，并对 val、val-1、val+1 分别构建回文串，然后拼接
        String[] front = {String.valueOf(val), String.valueOf(val - 1), String.valueOf(val + 1)};
        String[] back = n % 2 == 0 ? getPalindrome(front, true, (n + 1) / 2) : getPalindrome(front, false, (n + 1) / 2);
        String[] can = {front[0] + back[0], front[1] + back[1], front[2] + back[2]};

        long minDiff = 0x7fffffff;
        String ans = "#";
        for (int i = 0; i < can.length; i++) {
            long diff = Math.abs(Long.parseLong(can[i]) - num);
            if (diff != 0 && diff <= minDiff) {
                if (diff == minDiff) {
                    ans = ans.equals("#") ? can[i] : Long.parseLong(ans) < Long.parseLong(can[i]) ? ans : can[i];
                } else {
                    minDiff = diff;
                    ans = can[i];
                }
            }
        }
        return ans;
    }

    private String[] getPalindrome(String[] s, boolean flag, int len) {
        String[] ss = new String[3];
        if (!flag) {
            ss[0] = new StringBuilder(s[0].substring(0, s[0].length() - 1)).reverse().toString();
            ss[1] = new StringBuilder(s[1].substring(0, s[1].length() < len ? s[1].length() : s[1].length() - 1)).reverse().toString();
            ss[2] = new StringBuilder(s[2].substring(0, s[2].length() - 1)).reverse().toString();
        } else {
            ss[0] = new StringBuilder(s[0]).reverse().toString();
            ss[1] = new StringBuilder(s[1]).reverse().toString();
            ss[2] = new StringBuilder(s[2]).reverse().toString();
        }
        return ss;
    }

    public static void main(String[] args) {
        new _564_寻找最近的回文数().nearestPalindromic("11911");
    }
}