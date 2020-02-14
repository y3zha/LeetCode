package leetcode.swordtooffer;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class 面试题49_丑数 {

    /**
     * 见leetcode题解 https://leetcode-cn.com/problems/chou-shu-lcof/solution/dui-he-dong-tai-gui-hua-si-lu-xiang-jie-by-jerry_n/
     */

    //思路一：
    public int nthUglyNumber(int n) {
        PriorityQueue<Long> pq = new PriorityQueue<>();
        Set<Long> s = new HashSet<>();
        //初始化,放进堆和set，发现1要开Long数组才可以
        long[] primes = new long[]{2, 3, 5};
        for (long prime : primes) {
            pq.offer(prime);
            s.add(prime);
        }
        long num = 1;
        for (int i = 1; i < n; i++) {
            num = pq.poll();
            //遍历三个因子
            for (int j = 0; j < 3; j++) {
                if (!s.contains(num * primes[j])) {
                    pq.offer(num * primes[j]);
                    s.add(num * primes[j]);
                }
            }
        }
        return (int) num;
    }

    public int nthUglyNumber2(int n) {
        int[] res = new int[n];
        res[0] = 1;
        int p2 = 0, p3 = 0, p5 = 0;
        for (int i = 1; i < n; i++) {
            // 更新数组
            res[i] = Math.min(res[p2] * 2, Math.min(res[p3] * 3, res[p5] * 5));
            // 移动指针，不能写if else if 这种，要同步增加的
            if (res[i] == res[p2] * 2) p2++;
            if (res[i] == res[p3] * 3) p3++;
            if (res[i] == res[p5] * 5) p5++;
        }
        return res[n - 1];
    }



}