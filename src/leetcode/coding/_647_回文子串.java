package leetcode.coding;

public class _647_回文子串 {

    // 计算这个字符串中有多少个回文子串。具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。

    // 中心扩展法
    public int countSubstrings(String s) {
        int cnt = 0;
        char[] sc = s.toCharArray();
        int n = sc.length;
        int i, j;
        for (int c = 0; c < n; c++) {
            i = c;
            j = c;
            while (i >= 0 && j < n && sc[i] == sc[j]) {
                cnt++;
                i--;
                j++;
            }
        }
        for (int c = 0; c < n; c++) {
            i = c;
            j = c + 1;
            while (i >= 0 && j < n && sc[i] == sc[j]) {
                cnt++;
                i--;
                j++;
            }
        }
        return cnt;
    }

}