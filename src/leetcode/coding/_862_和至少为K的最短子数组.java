package leetcode.coding;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 微软面试面到的
 * 1 <= A.length <= 50000
 * -10 ^ 5 <= A[i] <= 10 ^ 5
 * 1 <= K <= 10 ^ 9
 *
 * 但是面试过程中，面试官提到如果K是负数怎么办这个问题
 *
 * 一看到，首先得考虑A的长度是多少，能不能用滑动窗口？如果一直找不到，得要滑多久呢，如果直接莽很大可能就tle了
 * 除了用滑动窗口 还要用个前缀和
 *
 * 我们用数组 P 表示数组 A 的前缀和，即 P[i] = A[0] + A[1] + ... + A[i - 1]。
 * 我们需要找到 x 和 y，使得 P[y] - P[x] >= K 且 y - x 最小。
 *
 */
public class _862_和至少为K的最短子数组 {
    public static void main(String[] args) {
        int[] a = {2, -1, 2};
        shortestSubarray(a, 3);
    }

    //滑动窗口 + 前缀和 这么写超时了
    public static int shortestSubarray(int[] A, int K) {
        //求个前缀和
        int n = A.length;
        int[] ps = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            ps[i] = ps[i - 1] + A[i - 1];
        }
        //滑动窗口
        //枚举窗口大小从1到n
        for (int w = 1; w <= n; w++) {
            //滑动窗口,滑的是prefix Sum的窗口
            int cnt = 0;
            int j = 0;
            for (int i = 0; i < n; i++) {
                while (j <= n && cnt < w) {
                    j++;
                    cnt++;
                }
                if (cnt == w) {
                    if (j <= n && ps[j] - ps[i] >= K) {
                        return j - i;
                    }
                }
                cnt--;
            }
        }
        return -1;
    }

    /**
     * 得要O(n)的解法才能过，需要用到双端队列
     * 我们需要在上面的基础上做一些优化，比如ps[i] >= ps[j]，(i < j) 这种情况我们就不需要考虑，因为这就表明 i ~ j之间他是有负数的
     * 这种我们就可以直接跳过了，直到 ps[x] > ps[i]，如果 ps[x] - ps[i] > K，那么这就是我们要找的
     *
     * 另一个角度 当ps[j] - ps[i] >=k 时候，i也可以跳过了，因为这一段距离已经满足大于等于K了，我们要找更短的
     *
     * 第一种情况是扔掉末尾，如果新来的比末尾小，那么这个last就可以扔掉了，说明last到i之间是有负数的！就像个凹槽一样
     * 第二种情况是扔掉头，如果新来的 - 头 >= K，可以扔掉头，继续比较
     * 那么基于此，我们就要使用双端队列
     * https://github.com/Shellbye/Shellbye.github.io/issues/41
     */

    public static int shortestSubarray2(int[] A, int K) {
        int n = A.length;
        int[] ps = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            ps[i] = ps[i - 1] + A[i - 1];
        }
        int res = 0x7fffffff;
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i <= n; i++) {
            // 情况1
            while (!deque.isEmpty() && ps[i] <= ps[deque.getLast()]) {
                deque.pollLast();
            }
            // 情况2
            while (!deque.isEmpty() && ps[i] - ps[deque.getFirst()] >= K) {
                res = Math.min(res, i - deque.getFirst());
                deque.pollFirst();
            }
            deque.addLast(i);
        }
        return res == 0x7fffffff ? -1 : res;
    }
}