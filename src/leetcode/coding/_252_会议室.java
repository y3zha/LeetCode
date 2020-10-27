package leetcode.coding;

import java.util.Arrays;

public class _252_会议室 {

    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return true;
        Arrays.sort(intervals, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
        // 1 13 13 15
        int lastStart = intervals[0][0];
        int lastEnd = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            int[] arr = intervals[i];
            if (arr[0] < lastEnd) {
                return false;
            }
            lastStart = arr[0];
            lastEnd = arr[1];
        }
        return true;
    }
}