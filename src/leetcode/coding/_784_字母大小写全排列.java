package leetcode.coding;

import java.util.ArrayList;
import java.util.List;

public class _784_字母大小写全排列 {

    /**
     * 对于当前位置如果是字母搞两个递归就可以，一个是不变的继续走，第二个就改变大小写的分支
     */

    private List<String> res;

    public List<String> letterCasePermutation(String s) {
        res = new ArrayList<>();
        dfs(0, s.toCharArray());
        return res;
    }

    private void dfs(int st, char[] s) {
        if (st == s.length) {
            res.add(new String(s));
            return;
        }
        // 不改变 继续走的分支
        dfs(st + 1, s);
        // 如果是字母，则改变的分支
        char c = s[st];
        if (c >= 'A') {
            s[st] = c >= 'a' ? (char) (c - 32) : (char) (c + 32);
            dfs(st, s);
        }
    }
}