package leetcode.interviewbook;

/**
 * 利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。比如，字符串aabcccccaaa会变为a2b1c5a3。
 * 若“压缩”后的字符串没有变短，则返回原先的字符串。你可以假设字符串中只包含大小写英文字母（a至z）。
 *
 */
public class 面试题01_06_字符串压缩 {
    //模拟
    public String compressString(String s) {
        if (s.length() == 0) return s;
        int n = s.length();
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (i < n) {
            char c = s.charAt(i);
            sb.append(c);
            int j = i + 1;  //从i的下一个开始
            int cnt = 1;
            while (j < n && s.charAt(j) == c) {
                j++;
                cnt++;
            }
            sb.append(cnt);
            i = j;
        }
        if (sb.length() < s.length()) return sb.toString();
        else return s;
    }
}