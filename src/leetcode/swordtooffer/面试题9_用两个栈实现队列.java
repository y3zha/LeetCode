package leetcode.swordtooffer;

import java.util.Stack;

public class 面试题9_用两个栈实现队列 {

    class CQueue {

        Stack<Integer> stack1;
        Stack<Integer> stack2;

        public CQueue() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        public void appendTail(int value) {
            stack2.push(value);
        }

        public int deleteHead() {
            if (stack1.isEmpty()) {
                if (stack2.isEmpty()) {
                    return -1;
                }
                while (!stack2.isEmpty()) {
                    stack1.push(stack2.pop());
                }
            }
            return stack1.pop();
        }
    }
}