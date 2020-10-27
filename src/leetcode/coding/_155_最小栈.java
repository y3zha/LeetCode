package leetcode.coding;

import java.util.Stack;

/**
 * 和lintcode的12题是一样的，需要一个辅助栈，来放每一个当前元素之前（包括当前元素）的最小值，
 */
public class _155_最小栈 {

    class MinStack {
        Stack<Integer> stack = null;
        Stack<Integer> minStack = null;
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
            return;
        }

        public void pop() {
            stack.pop();
            minStack.pop();
            return;
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }
}