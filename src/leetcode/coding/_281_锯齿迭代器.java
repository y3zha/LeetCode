package leetcode.coding;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _281_锯齿迭代器 {

    public class ZigzagIterator {

        Queue<Iterator<Integer>> queue;

        public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
            queue = new LinkedList<>();
            Iterator<Integer> it1 = v1.iterator();
            Iterator<Integer> it2 = v2.iterator();
            if (it1.hasNext()) {
                queue.offer(it1);
            }
            if (it2.hasNext()) {
                queue.offer(it2);
            }
        }

        public int next() {
            Iterator<Integer> it = queue.poll();
            int v = it.next();
            if (it.hasNext()) {
                queue.offer(it);
            }
            return v;
        }

        public boolean hasNext() {
            return !queue.isEmpty();
        }
    }
}