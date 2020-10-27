package leetcode.coding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class _294_翻转游戏II {

    //递归判断，如果下一步是false，那么这一步就是true
    // 并且用map来存放字符串的结果，如果访问过了就不需要再次去访问
    HashMap<String, Boolean> map;
    int n;

    public boolean canWin(String s) {
        map = new HashMap<>();
        n = s.length();
        return helper(s);
    }

    private boolean helper(String s) {
        char[] sc = s.toCharArray();
        for (int i = 0; i < n - 1; i++) {
            if (sc[i] == '+' && sc[i + 1] == '+') {
                sc[i] = sc[i + 1] = '-';
                String temp = new String(sc);
                if (map.get(temp) == null) {
                    boolean flag = helper(temp);
                    if (!flag) return true;
                    map.put(temp, flag);
                }
                // 回溯状态重置
                sc[i] = sc[i + 1] = '+';
            }
        }
        return false;
    }

}