package leetcode.coding;

import java.util.LinkedList;
import java.util.Queue;

public class _346_数据流中的移动平均值 {

    class MovingAverage {

        // 窗口大小
        int w;
        Queue<Integer> queue;
        double cnt = 0;

        public MovingAverage(int size) {
            w = size;
            queue = new LinkedList<>();
        }

        public double next(int val) {
            queue.offer(val);
            cnt += val;
            if (queue.size() <= w) {
                return cnt / queue.size();
            } else {
                Integer poll = queue.poll();
                cnt -= (double) poll;
                return cnt / queue.size();
            }
        }
    }
}