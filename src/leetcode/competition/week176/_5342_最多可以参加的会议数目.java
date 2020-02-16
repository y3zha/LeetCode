package leetcode.competition.week176;

import lintcode.算法强化班._04_二分和扫描线.扫描线._391_数飞机;

import java.util.*;
import java.util.stream.IntStream;

public class _5342_最多可以参加的会议数目 {


    //就按照结束时间升序排
    public static int maxEvents(int[][] events) {
        Arrays.sort(events, (o1, o2) -> o1[1] - o2[1]);
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

    /**
     * 哪个会议先要结束了，就去参加哪个会议呗，因为我这天参加谁的都可以，但是只有这个会议这天就要结束了
     *
     * 要找到当天结束时间最快的会议，我们可以运用一个小顶堆解决。这个堆是按结束时间从小到大排序的。
     * 小顶堆里存的是当天可参加的所有会议的结束时间。 很明显，我们只需要选择小顶堆的top会议即可。
     */
    public static int maxEvents2(int[][] events) {
        //按照起点排序就好，因为我们要按顺序找
        Arrays.sort(events, (o1, o2) -> o1[0] - o2[0]);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int res = 0, last = 1, i = 0, n = events.length;
        while (i < n || !pq.isEmpty()) {
            //将start相同的会议都放进堆里
            while (i < n && events[i][0] == last) {
                pq.offer(events[i++][1]);
            }
            //把之前结束过的会议pop掉
            while (!pq.isEmpty() && pq.peek() < last) {
                pq.poll();
            }
            //顶上的就是俺们要参加的
            if (!pq.isEmpty()) {
                pq.poll();
                res++;
            }
            last++;
        }
        return res;
    }



    public static void main(String[] args) {
        int[][] a = {{1, 2}, {1, 2}, {3, 3}, {1, 5}, {1, 5}};
        int[][] b = {{1, 2}, {1, 2}, {3, 4}, {2, 3}};
        maxEvents2(b);
    }


}