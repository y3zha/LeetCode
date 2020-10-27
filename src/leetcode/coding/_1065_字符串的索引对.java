package leetcode.coding;

import java.util.ArrayList;
import java.util.List;

public class _1065_字符串的索引对 {

    public int[][] indexPairs(String text, String[] words) {
        List<int[]> list = new ArrayList<>();
        for (String word : words) {
            int n = word.length();
            int idx = text.indexOf(word);
            while (idx != -1) {
                list.add(new int[]{idx, idx + n - 1});
                idx = text.indexOf(word, idx + 1);
            }
        }
        // 排序是有两个条件，a[0] < b[0], 或 a[0] 等于 b[0] 时需要a[1] < b[1]
        list.sort((o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
        return list.toArray(new int[][]{});
    }
}