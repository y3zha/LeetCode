package leetcode.coding;

import java.util.Deque;
import java.util.LinkedList;

public class _402_移掉K位数字 {

    /**
     * 从一个字符串数字中删除k个数字使得剩下的数最小，并且保持原来数字的相对位置不变
     *
     * 思路一：暴力，我们知道剩下位置数字的长度，从中选即可 C(n,n-k)
     *
     * 思路二：【单调栈】贪心优化，遍历的过程中，对于每一个元素决定该丢弃还是保留。对于两个数 123a456 和 123b456，两个相同位数的数字大小关系取决于第一个不同的数的大小
     *       所以我们可以选择性丢弃前面相邻的元素
     *       例如 num = 1432219， k = 3
     *       第一位为 1，左边没有相邻的元素，所以不能丢弃
     *       第二位为 4，左边为 1，如果丢弃 1，那么 4 开头的数字会更大，所以我们选择不丢弃
     *       第三位为 3，左边为 4，如果丢弃 4，那么 13 会比 14 小，所以可以丢弃
     *       ... 以此类推
     *
     *       如果给定的数字是一个单调递增的数字，那么我们的算法会永远选择不丢弃。这个题目中要求的，我们要永远确保丢弃 k 个矛盾。
     *       对应的策略是：每丢弃一次，k--，直到 k 为 0，遍历结束
     *       遍历完成，如果 k 仍然大于 0，截取前面 n - k 个即可
     *
     *       但其实不用丢弃k个，其实就是保留 n - k 个
     *
     *       用单调栈即可
     *
     * 时间复杂度：虽然内层还有一个 while 循环，但是由于每个数字最多仅会入栈出栈一次，因此时间复杂度仍然为 O(N)，其中 N 为数字长度。
     *
     * 思想相同的题目 316、321、402、1081
     */

    public static String removeKdigits(String num, int k) {
        Deque<Character> dq = new LinkedList<>();
        int n = num.length();
        // 保留前 n-k 个
        int len = n - k;
        for (int i = 0; i < n; i++) {
            char c = num.charAt(i);
            while (k > 0 && !dq.isEmpty() && c < dq.peekLast()) {
                dq.pollLast();
                k--;
            }
            dq.addLast(c);
        }
        // 保留前 n - k 个
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len && !dq.isEmpty(); i++) {
            Character c = dq.pollFirst();
            if (c == '0' && sb.length() == 0) {
                continue;
            }
            sb.append(c);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(removeKdigits("1173", 2));
    }
}