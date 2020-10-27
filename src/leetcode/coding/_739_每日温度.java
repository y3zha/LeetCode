package leetcode.coding;

import java.util.Stack;

public class _739_每日温度 {

    /**
     * 单调栈, 找每个位置右边第一个比他大的元素的下标，减去它的下标就是这个位置的答案
     * 单调递减栈,留波峰
     *
     * @param T
     * @return
     */
    public int[] dailyTemperatures(int[] T) {
        int n = T.length;
        int[] res = new int[n];

        Stack<Integer> stack = new Stack<>();

        int i = 0;
        while (i < n) {

            if (stack.isEmpty() || T[i] <= T[stack.peek()]) {
                stack.push(i);
                i++;
            } else {
                Integer idx = stack.pop();
                res[idx] = i - idx;
            }
        }
        while (!stack.isEmpty()) {
            res[stack.pop()] = 0;
        }
        return res;
    }
}