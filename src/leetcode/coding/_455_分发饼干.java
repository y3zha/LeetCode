package leetcode.coding;

import java.util.Arrays;

public class _455_分发饼干 {

    public int findContentChildren(int[] g, int[] s) {
        if (g == null || s == null) {
            return 0;
        }
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0, j = 0, n = g.length, m = s.length;
        while (i < n && j < m) {
            if (g[i] <= s[j]) {
                i++;
                j++;
            } else {
                j++;
            }
        }
        return i;
    }

}