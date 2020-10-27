package leetcode.interviewbook;

import java.util.Stack;

/**
 * 利用两个栈实现队列
 * stack1作为出队的时候用
 * stack2作为push的时候用，push都是push到stack2中去
 * 思路：我们总是把元素push到stack2中去，要出队的时候，如果stack1为空，就把stack2的都放到stack1中去，再stack1出队
 * 否则stack就可以直接出队
 */
public class 面试题03_04_化栈为队 {

    class MyQueue {

        Stack<Integer> stack1;
        Stack<Integer> stack2;

        public MyQueue() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        public void push(int x) {
            stack2.push(x);
        }

        public int pop() {
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

        //要看队头，如果stack1不为空，那就是stack1的头，否则就要把stack2的都push到stack1中去再看头
        public int peek() {
            if (!stack1.isEmpty()) {
                return stack1.peek();
            }
            if (!stack2.isEmpty()) {
                while (!stack2.isEmpty()) {
                    stack1.push(stack2.pop());
                }
            }
            return stack1.peek();
        }

        public boolean empty() {
            return stack1.isEmpty() && stack2.isEmpty();
        }
    }
}