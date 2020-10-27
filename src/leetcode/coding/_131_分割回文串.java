package leetcode.coding;

import java.util.ArrayList;
import java.util.List;

/**
 * dfs
 *
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 *
 * 返回 s 所有可能的分割方案。
 */
public class _131_分割回文串 {

    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return res;
        }
        //中心扩展法来得到一个回文判断数组
        boolean[][] f = getPalindromeArray(s);
        dfs(s, f, 0,new ArrayList<String>(), res);
        return res;
    }

    private void dfs(String s, boolean[][] f, int startIndex, ArrayList<String> list, List<List<String>> res) {
        if (startIndex == s.length()) {
            res.add(new ArrayList<>(list));
        }
        for (int i = startIndex; i < s.length(); i++) {
            String temp = s.substring(startIndex, i + 1);       //左闭右开
            if (!f[startIndex][i]) {
                continue;
            }
            list.add(temp);
            dfs(s, f, i + 1, list, res);    //注意这里从i+1开始的
            list.remove(list.size() - 1);
        }
    }

    private boolean[][] getPalindromeArray(String s) {
        char[] chs = s.toCharArray();
        int n = chs.length;
        boolean[][] f = new boolean[n][n];
        //可能是中心点center（奇数），也可能是中心线（n为偶数的情况）
        for (int c = 0; c < n; c++) {
            int i = c, j = c;
            while (i >= 0 && j < n && chs[i] == chs[j]) {
                f[i][j] = true;
                i--;
                j++;
            }
        }
        //中心线的情况
        for (int c = 0; c < n; c++) {
            int i = c, j = c + 1;
            while (i >= 0 && j < n && chs[i] == chs[j]) {
                f[i][j] = true;
                i--;
                j++;
            }
        }
        return f;
    }
}