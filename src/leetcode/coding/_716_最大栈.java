package leetcode.coding;

import java.util.Stack;

// 设计一个最大栈，支持 push、pop、top、peekMax 和 popMax 操作。
// 和最小栈是一个思路
// 要添加元素了，如果来的比辅助栈内的还大，就可以放进去，否则就放
// 这个难点在popMax啊，要先拿出来放到一个 helper 里删除再放进去

// -1e7 <= x <= 1e7
// 操作次数不会超过 10000。
// 当栈为空的时候不会出现后四个操作。
public class _716_最大栈 {

    class MaxStack {

        Stack<Integer> stack;
        Stack<Integer> maxStack;

        public MaxStack() {
            stack = new Stack<>();
            maxStack = new Stack<>();
        }

        public void push(int x) {
            stack.push(x);
            if (maxStack.isEmpty()) maxStack.push(x);
            else maxStack.push(Math.max(x, maxStack.peek()));
        }

        public int pop() {
            maxStack.pop();
            return stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int peekMax() {
            return maxStack.peek();
        }

        // 很关键 top、pop、push都是用自己写的方法
        public int popMax() {
            int max = peekMax();
            Stack<Integer> helper = new Stack<>();
            while (top() != max) {
                helper.push(pop());
            }
            pop();
            // 再放进去
            while (!helper.isEmpty()) {
                push(helper.pop());
            }
            return max;
        }
    }
}