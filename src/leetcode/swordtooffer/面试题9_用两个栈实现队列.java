package leetcode.swordtooffer;

import java.util.Stack;

/**
 * 利用两个栈实现队列
 * stack1作为出队的时候用
 * stack2作为push的时候用，push都是push到stack2中去
 * 思路：我们总是把元素push到stack2中去，要出队的时候，如果stack1为空，就把stack2的都放到stack1中去，再stack1出队
 * 否则stack就可以直接出队
 */
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