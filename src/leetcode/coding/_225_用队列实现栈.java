package leetcode.coding;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 队列：先进先出
 * 栈：先进后出
 *
 * 利用两个队列，再插入一个元素前，先把队列中的元素都放到临时队列中去，等插完这个元素了，把临时队列中的元素再放进队列。
 *
 */

public class _225_用队列实现栈 {


    class MyStack {

        Queue<Integer> queue;
        Queue<Integer> temp;

        public MyStack() {
            queue = new LinkedList<>();
            temp = new LinkedList<>();
        }

        public void push(int x) {
            while (!queue.isEmpty()) {
                temp.offer(queue.poll());
            }
            queue.offer(x);
            while (!temp.isEmpty()) {
                queue.offer(temp.poll());
            }
        }

        public int pop() {
            return queue.poll();
        }

        public int top() {
            return queue.peek();
        }

        public boolean empty() {
            return queue.isEmpty();
        }
    }
}