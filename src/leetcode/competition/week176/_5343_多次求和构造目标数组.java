package leetcode.competition.week176;

import java.util.Collections;
import java.util.PriorityQueue;

public class _5343_多次求和构造目标数组 {

    //最大的那个是上一轮的和，推上去
    public static boolean isPossible(int[] target) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int sum = 0;
        for (int i = 0; i < target.length; i++) {
            pq.add(target[i]);
            sum += target[i];
        }

        while (sum != target.length) {
            int cur = pq.poll();    //当前最大和
            int rest = sum - cur;
            int pre = cur - rest;   //上一轮那个位置上的数字是几
            if (pre >= cur || pre < 1) {
                return false;
            }
            sum = cur;
            pq.offer(pre);
        }
        return true;
    }

    /**
     * 但是可能会超时
     */
    public static boolean isPossible2(int[] target) {
        PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
        long sum = 0;
        for (int i = 0; i < target.length; i++) {
            sum += target[i];
            pq.offer((long)target[i]);
        }
        //如果此时队列为空或者最大值就是1，直接return true
        if (pq.isEmpty() || pq.peek() == 1) {
            return true;
        }
        while (true) {
            //取出最大的那个
            Long poll = pq.poll();
            //如果此时堆中最大的为1
            if (pq.peek() == 1) {
                //直接看它满足或不满足公式
                return (poll - 1) % (sum - poll) == 0;
            } else {
                //需要计算多少轮才能比第二小的数小
                long n = (poll - pq.peek()) / (sum - poll) + 1;
                //得到这个数字
                long x = poll - n * (sum - poll);
                if (x <= 0) {
                    return false;
                }
                //更新sum
                sum = poll - (sum - poll) * (n - 1);
                pq.offer(x);
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {9, 3, 5};
        isPossible2(a);

    }
}