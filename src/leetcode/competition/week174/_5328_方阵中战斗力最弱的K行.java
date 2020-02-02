package leetcode.competition.week174;

import java.util.*;

public class _5328_方阵中战斗力最弱的K行 {

    //自己写的，有点复杂
    public int[] kWeakestRows(int[][] mat, int k) {
        int n = mat.length;
        int m = mat[0].length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 1) {
                    count++;
                }
            }
            map.put(i, count);
        }
        ArrayList<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                int diff = o1.getValue() - o2.getValue();
                if (diff == 0) {
                    diff = o1.getKey() - o2.getKey();
                }
                return diff;
            }
        });
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            Map.Entry<Integer, Integer> entry = list.get(i);
            res[i] = entry.getKey();
        }
        return res;
    }
}