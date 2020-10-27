package leetcode.interviewbook;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class 面试题03_03_堆盘子 {

    class StackOfPlates {
        private int cap;
        private List<Deque<Integer>> list;

        // cap 表示每个栈内盘子最多有多少个
        public StackOfPlates(int cap) {
            this.cap = cap;
            list = new ArrayList<>();
        }

        public void push(int val) {
            if (cap == 0) return;
            if (list == null || list.isEmpty()) {
                addStack(val);
                return;
            }
            Deque<Integer> stack = list.get(list.size() - 1);
            if (stack.size() < cap) {
                stack.push(val);
            } else {
                addStack(val);
            }
        }

        public int pop() {
            if (cap == 0) return -1;
            for (int i = list.size() - 1; i >= 0; i--) {
                Deque<Integer> stack = list.get(i);
                if (!stack.isEmpty()) {
                    int res = stack.pop();
                    if (stack.isEmpty()) {
                        list.remove(i);
                    }
                    return res;
                }
            }
            return -1;
        }

        public int popAt(int index) {
            if (cap == 0) return -1;
            if (list.isEmpty() || list.size() - 1 < index) {
                return -1;
            }
            Deque<Integer> stack = list.get(index);
            if (stack.isEmpty()) {
                return -1;
            }
            int res = stack.pop();
            if (stack.isEmpty()) {
                list.remove(index);
            }
            return res;
        }

        private void addStack(int val) {
            Deque<Integer> stack = new LinkedList<>();
            if(stack.size() != cap) {
                stack.push(val);
            }
            list.add(stack);
        }
    }
}