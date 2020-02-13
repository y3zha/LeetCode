package leetcode.swordtooffer;

import java.util.Stack;

public class 面试题30_包含min函数的栈 {

    /**
     * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
     *
     * minStack如果是空的，就能直接放，如果不是空的，只有这个元素比它栈顶小才能放入，如果这个元素比栈顶的大，那就把栈顶再放一遍，pop的时候两个栈一起pop即可
     */

    class MinStack {
        Stack<Integer> stack;
        Stack<Integer> minStack;
        public MinStack() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int x) {
            stack.push(x);
            if (minStack.isEmpty()) {
                minStack.push(x);
            } else {
                minStack.push(Math.min(x, minStack.peek()));
            }
        }

        public void pop() {
            stack.pop();
            minStack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int min() {
            return minStack.peek();
        }
    }
}