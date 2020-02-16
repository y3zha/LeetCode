package leetcode.competition.week176;

import lintcode.算法强化班._04_二分和扫描线.扫描线._391_数飞机;

import java.util.*;

public class _5342_最多可以参加的会议数目 {


    //就按照结束时间升序排
    public static int maxEvents(int[][] events) {
        Arrays.sort(events, (o1, o2) -> o1[1] - o2[0]);
        Set<Integer> set = new HashSet<>();
        for (int[] event : events) {
            int s = event[0];
            int e = event[1];
            for (int i = s; i <=e; i++) {
                if (!set.contains(i)) {
                    set.add(i);
                    break;
                }
            }
        }
        return set.size();
    }

    //但是对于100000*[1,100000]这种例子，上面就可能超时了，所以写个优先队列的


    public static void main(String[] args) {
        int[][] a = {{1, 2}, {1, 2}, {3, 3}, {1, 5}, {1, 5}};
        int[][] b = {{1, 2}, {1, 2}, {3, 4}, {2, 3}};
        maxEvents(b);
    }


}