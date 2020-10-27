package leetcode.coding;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class _759_员工空闲时间 {

    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        if (schedule == null || schedule.isEmpty()) return new ArrayList<>();
        // 将所有员工的所有空闲时间加入优先队列，并且按照开始时间排升序, 然后做区间合并
        PriorityQueue<Interval> pq = new PriorityQueue<>(
                (o1, o2) -> o1.start == o2.start ? o1.end - o2.end : o1.start - o2.start);
        schedule.forEach(pq::addAll);
        List<Interval> list = new ArrayList<>();
        // 区间合并
        Interval pre = pq.poll();
        while (!pq.isEmpty()) {
            Interval cur = pq.poll();
            if (cur.start <= pre.end) {
                pre.end = Math.max(pre.end, cur.end);
            } else {
                // 在前一个结束和现在开始这段时间都属于空闲时间
                list.add(new Interval(pre.end, cur.start));
                pre = cur;
            }
        }
        return list;
    }


    class Interval {
        public int start;
        public int end;

        public Interval() {}

        public Interval(int _start, int _end) {
            start = _start;
            end = _end;
        }
    };
}