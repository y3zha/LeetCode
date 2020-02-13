package leetcode.swordtooffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 面试题38_字符串的排列 {

    /**
     * 这个题有重复的字符的，要去重
     */
    public String[] permutation(String s) {
        List<String> list = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return new String[]{};
        }
        char[] sc = s.toCharArray();
        Arrays.sort(sc);    //老是忘记排序
        boolean[] visited = new boolean[s.length()];
        dfs(sc,  "", list, visited);
        return list.toArray(new String[list.size()]);
    }

    private void dfs(char[] sc,  String temp, List<String> list, boolean[] visited) {
        if (temp.length() == sc.length) {
            list.add(new String(temp));
            return;
        }
        for (int i = 0; i < sc.length; i++) {
            if (i > 0 && !visited[i - 1] && sc[i] == sc[i - 1]) {
                continue;
            }
            if (!visited[i]) {
                visited[i] = true;
                temp += sc[i];
                dfs(sc, temp, list, visited);
                temp = temp.substring(0, temp.length() - 1);
                visited[i] = false;
            }
        }
    }
}