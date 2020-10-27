package leetcode.coding;

public class _622_设计循环队列 {

    class MyCircularQueue {

        private int[] queue;
        private int headIndex;
        private int count;
        private int capacity;

        // 设置队列长度为 k
        public MyCircularQueue(int k) {
            this.capacity = k;
            this.queue = new int[k];
            this.headIndex = 0;
            this.count = 0;
        }

        // 向循环队列插入一个元素。如果成功插入则返回真。
        public boolean enQueue(int value) {
            if (count == capacity) {
                return false;
            }
            queue[(headIndex + count) % capacity] = value;
            count++;
            return true;
        }

        // 从循环队列中删除一个元素。如果成功删除则返回真。
        public boolean deQueue() {
            if (count == 0) {
                return false;
            }
            headIndex = (headIndex + 1) % capacity;
            count -= 1;
            return true;
        }

        public int Front() {
            if (count == 0) {
                return -1;
            }
            return queue[headIndex];
        }

        public int Rear() {
            if (count == 0) {
                return -1;
            }
            int tailIndex = (headIndex + count - 1) % capacity;
            return queue[tailIndex];

        }

        public boolean isEmpty() {
            return count == 0;
        }

        public boolean isFull() {
            return count == capacity;
        }
    }
}