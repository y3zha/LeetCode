package leetcode.interviewbook;

import java.util.Deque;
import java.util.LinkedList;

public class 面试题03_05_栈排序 {

    /*
    解题思路：
    1、此题需要维护一个有序的栈，即每次push和pop操作前后，栈都应该是有序的。
    2、根据题干要求，只允许使用辅助栈，很容易想到，每次push前，将栈中元素比当前小的push到辅助栈中，完成后再push回来；
        每次pop只需将当前栈顶元素pop出来即可。
    3、还有没有继续优化的可能？有！连续多次push时，需要多次把元素在两个栈中传递。想象这样一个极端情况：
    连续n次push相同或相近元素，需要来回操作2n x i次，其中i为每次移动的元素数量。
    实际上，我们只需要移动2 x i次：先把i个元素移到辅助栈，再把n个元素放入栈，最后将辅助栈中元素移回来即可。
    此为惰性更新

     */
    class SortedStack {

        private Deque<Integer> stack;
        private Deque<Integer> tmp;

        public SortedStack() {
            stack = new LinkedList<>();
            tmp = new LinkedList<>();
        }

        public void push(int val) {
            // 只要栈顶的比它小，就放到tmp中
            while (!stack.isEmpty() && stack.peek() < val) {
                tmp.push(stack.pop());
            }
            stack.push(val);
            while (!tmp.isEmpty()) {
                stack.push(tmp.pop());
            }
        }

        public void pop() {
            while (!tmp.isEmpty()) {
                stack.push(tmp.pop());
            }
            if (!stack.isEmpty()) {
                stack.pop();
            }
        }

        public int peek() {
            while (!tmp.isEmpty()) {
                stack.push(tmp.pop());
            }
            return stack.isEmpty() ? -1 : stack.peek();
        }

        public boolean isEmpty() {
            return stack.isEmpty() && tmp.isEmpty();
        }
    }
}