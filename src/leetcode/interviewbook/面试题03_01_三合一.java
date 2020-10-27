package leetcode.interviewbook;

public class 面试题03_01_三合一 {

    // 描述如何只用一个数组来实现三个栈。
    // 就是将一个数组分成3个栈，然后自己可以去实现如何存储数据，代码有详细的注释
    class TripleInOne {

        // 开三个栈的大小，并且给他们标号
        private int stackSize;
        private int[] stack;
        private int[] top;


        public TripleInOne(int stackSize) {
            this.stackSize = stackSize;
            stack = new int[3 * stackSize];
            top = new int[]{0, 1, 2};
        }

        public void push(int stackNum, int value) {
            // 拿到栈顶下标
            int tt = top[stackNum];
            // 当当前下标是3的倍数，说明已经塞满了
            if (tt / 3 == stackSize) {
                return;
            }
            stack[tt] = value;
            // 栈顶+3
            top[stackNum] += 3;
        }

        public int pop(int stackNum) {
            if (isEmpty(stackNum)) {
                return -1;
            }
            // 拿到栈顶指针
            int tt = top[stackNum];
            // 拿到元素,栈顶是加完元素后+3的，这里也要-3
            int val = stack[tt - 3];
            top[stackNum] -= 3;
            return val;
        }

        public int peek(int stackNum) {
            if (isEmpty(stackNum)) {
                return -1;
            }
            int tt = top[stackNum];
            return stack[tt - 3];
        }

        public boolean isEmpty(int stackNum) {
            return top[stackNum] < 3;
        }
    }
}