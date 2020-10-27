package leetcode.coding;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class _1086_前五科的均分 {

    public static int[][] highFive(int[][] items) {
        Arrays.sort(items, (o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0]);
        // 此时items的最后一行是序号最大的，可以得到人数
        int n = items.length;
        int cnt = items[n - 1][0];
        int[][] res = new int[cnt][2];
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int stuId = items[i][0];
            if (set.contains(stuId)) {
                continue;
            }
            int score = 0;
            set.add(stuId);
            for (int j = i; j < i + 5; j++) {
                score += items[j][1];
            }
            // 不要+5，因为循坏i还会再加一次
            i += 4;
            res[stuId - 1][0] = stuId;
            res[stuId - 1][1] = score / 5;
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] a = {{1, 91}, {1, 92}, {2, 93}, {2, 97}, {1, 60}, {2, 77}, {1, 65}, {1, 87}, {1, 100}, {2, 100}, {2, 76}};
        int[][] b = {{1, 84}, {1, 72}, {1, 47}, {1, 43}, {1, 78}, {2, 79}, {2, 4}, {2, 23}, {2, 88}, {2, 79}, {3, 75}, {3, 80}, {3, 38}, {3, 73}, {3, 4}};
        highFive(b);
    }

}