package leetcode.swordtooffer;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class 面试题59_队列的最大值II {


    /**
     * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的时间复杂度都是O(1)。
     * 若队列为空，pop_front 和 max_value 需要返回 -1
     *
     * 用两个队列即可 和实现最小栈的思路是一样的
     *
     * 队列1保存队列元素
     * 队列2保存当前队列中的单调递减栈
     *  队列一我们还是正常使用的，该push就得push，该出就要出
     *  队列二，如果来个一个元素，比队列2的队尾的要小 或相等，那就正常放入，如果比队尾的要大，那就要把比它小的都给移除
     *  出队的时候，如果deque的头和queue的头一样，那就也要pop，否则就不用pop了，只要queue pop即可
     *
     * 1 3 2 4 5 push、push、push
     * queue 231
     * deque 23
     *
     * 出队
     * 当前队列最大元素为3，，由于queue和deque的对头不相等，返回queue对头即可，因为队列2是单调递减，它后面没有比它大的
     * queue 23
     * deque 3
     *
     * 再出队
     * 当前队列最大元素为3，由于queue和deque的对头相等，要把deque的头给pop掉
     * queue 2
     * deque 2
     *
     * 所以这个题要用到queue和deque
     */
    class MaxQueue {

        Queue<Integer> queue;
        Deque<Integer> deque;

        public MaxQueue() {
            queue = new LinkedList<>();
            deque = new ArrayDeque<>();
        }

        public int max_value() {
            if (queue.isEmpty()) {
                return -1;
            }
            return deque.getFirst();
        }

        public void push_back(int value) {
            while (!deque.isEmpty() && value > deque.peekLast()) {
                deque.removeLast();
            }
            queue.offer(value);
            deque.offer(value);
        }

        public int pop_front() {
            if (queue.isEmpty()) {
                return -1;
            }
            if (queue.peek().equals(deque.peekFirst())) {       //要用equles
                deque.pop();
            }
            return queue.poll();
        }
    }

    class MaxQueue2 {

        // 要得到队列中的最大值，维护一个单调递减队列即可
        // 为什么是单调递减队列呢，因为我们要的是最大值，就是波峰，删除波谷，留下波峰，前面波谷其实就是单调递减形成的

        Queue<Integer> q;
        Deque<Integer> maxQ;
        public MaxQueue2() {
            q = new LinkedList<>();
            maxQ = new LinkedList<>();
        }

        public int max_value() {
            if(q.isEmpty()) return -1;
            return maxQ.peek();
        }

        public void push_back(int value) {
            q.offer(value);
            //维护单调递减队列
            while(!maxQ.isEmpty() && value > maxQ.peekLast()){
                maxQ.removeLast();
            }
            maxQ.offer(value);
        }

        public int pop_front() {
            if(q.isEmpty()) return -1;
            if(q.peek().equals(maxQ.peekFirst())){  //这边一定要用equals，而不能用等于，看引用地址是否一致
                maxQ.pollFirst();
            }
            return q.poll();
        }
    }



}