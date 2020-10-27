package leetcode.coding;

import java.util.ArrayList;
import java.util.List;

public class _293_翻转游戏 {

    // 题目描述有问题，翻译为 列出第一次翻转后可能出现的所有情况 更恰当一点。
    public List<String> generatePossibleNextMoves(String s) {
        List<String> res = new ArrayList<>();
        if(s == null || s.length() < 2) return res;
        char[] sc = s.toCharArray();
        int n = sc.length;
        for (int i = 0; i < n - 1; i++) {
            if (sc[i] == '+' && sc[i + 1] == '+') {
                sc[i] = sc[i + 1] = '-';
                res.add(new String(sc));
                sc[i] = sc[i + 1] = '+';
            }
        }
        return res;
    }
}