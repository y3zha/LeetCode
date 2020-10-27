package leetcode.coding;

import java.util.LinkedList;
import java.util.Queue;

public class _995_K连续位的最小翻转次数 {

    //暴力 java 能过，但是很慢，py暴力是过不去的
    public int minKBitFlips(int[] A, int K) {
        int n = A.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (A[i] == 0) {
                if (i + K > n) {
                    return -1;
                } else {
                    for (int j = i; j < i + K; j++) {
                        A[j] ^= 1;
                    }
                    res++;
                }
            }
        }
        return res;
    }

    // 首先我们可以知道，对于每个位置而言，只有初始状态和总共被反转了多少次决定了自己最终的状态。
    // 另一方面，我们知道每一个长度为K的区间，最多只会被反转一次，因为两次反转后对最终结果没有影响。
    // 基于此，我们从前往后遍历数组，如果遇到一个0，我们将当前位置开始的长度为k区间的区间反转。
    // 如果遇到0时，剩下的区间长度不足K说明我们没有办法完成反转。
    // 但是如果我们每次反转当前区间时，将区间内每个数都取反，时间复杂度是O(n*k)的，这样是不够快的。
    // 我们需要优化一下，我们再考虑每个位置上的元素，他只会被前面K - 1个元素是否被反转所影响
    // 所以我们只需要知道前面k - 1个元素总共反转了多少次(更进一步的说我们只关系反转次数的奇偶性)。
    // 我们使用一个队列保存i前面k - 1个位置有多少元素被反转了。
    // 如果队列长度为奇数，那么当前位置的1被变成0了需要反转，如果为偶数，说明当前位置的0还是0，需要反转。
    // 归在一起，也就是说，如果队列长度 + 当前位置的值 是偶数的话，那就是需要翻转的！
    // 如果最后k - 1个位置还有0的话说明失败。否则将i加入队列，更新答案。
    // 时间复杂度：每个元素最多被进入队列和出队列一次，所以总的时间复杂度为O(n)的。
    public int minKBitFlips2(int[] A, int K) {
        int n = A.length;
        Queue<Integer> queue = new LinkedList<>();
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (!queue.isEmpty() && queue.peek() + K == i) {
                queue.poll();
            }
            if (((queue.size() + A[i]) & 1) == 0) {
                if (i + K > n) {
                    return -1;
                }
                queue.offer(i);
                res++;
            }
        }
        return res;
    }
}