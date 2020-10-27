package leetcode.coding;

import java.util.*;

public class _253_会议室II {

    // 计算至少需要多少间会议室，才能满足这些会议安排 ==> 最多同时开多少个会议 ==> 扫描线
    // 每当有会议加入时，meetings 加1， 每当有会议退出时，meetings 减1。求出meetings的最大值即可。
    public static int minMeetingRooms(int[][] intervals) {
        // 我们存进一个list中，并且标记每个会议的开始和结束，0代表会议开始，1代表会议结束
        List<int[]> list = new ArrayList<>();
        for (int[] interval : intervals) {
            list.add(new int[]{interval[0], 0});
            list.add(new int[]{interval[1], 1});
        }
        // 对它按照时间开始时间升序排序，如果开始时间一样，结束的在前！不然要重复算的，比如{{1, 13}, {13, 15}};，一定是结束的13在前
        list.sort((o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0]);
        int res = 0;
        int rooms = 0;
        for (int[] interval : list) {
            // 如果标记为开始，room++
            if (interval[1] == 0) {
                rooms++;
                res = Math.max(res, rooms);
            } else {
                rooms--;
            }
        }
        return res;
    }


    /**
     * 看到利用优先队列的做法
     * 先将二维数组中的各项小数组按照开始时间点进行从小到大的排序。
     * 遍历有序的二维数组，将其开始时间与优先队列头部的数值（结束时间）比较，若小于，则说明重叠。
     * 重叠的次数+1即为会议室数量。
     */
    public static int minMeetingRooms2(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, Comparator.comparing(o -> o[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int rooms = 0;
        for (int i = 0; i < intervals.length; i++) {
            pq.offer(intervals[i][1]);
            if (intervals[i][0] < pq.peek()) {
                rooms++;
            } else {
                pq.poll();
            }
        }
        return rooms;
    }



    public static void main(String[] args) {
        _253_会议室II test = new _253_会议室II();
        int[][] ints = {{1, 13}, {13, 15}};
        test.minMeetingRooms(ints);
    }
}