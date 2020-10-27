package leetcode.interviewbook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 面试题08_08_有重复字符串的排列组合 {

    public String[] permutation(String s) {
        List<String> list = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return new String[]{};
        }
        char[] sc = s.toCharArray();
        Arrays.sort(sc);
        String newS = new String(sc);
        boolean[] vis = new boolean[s.length()];
        dfs(newS, "", vis, list);
        return list.toArray(new String[0]);
    }

    private void dfs(String s, String temp, boolean[] vis, List<String> list) {
        if (temp.length() == s.length()) {
            list.add(temp);
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            if (i > 0 && s.charAt(i) == s.charAt(i - 1) && !vis[i - 1]) {
                continue;
            }
            if (!vis[i]) {
                temp += s.charAt(i);
                vis[i] = true;
                dfs(s, temp, vis, list);
                vis[i] = false;
                temp = temp.substring(0, temp.length() - 1);
            }
        }
    }
}