package leetcode.coding;

import java.util.LinkedList;
import java.util.Queue;

public class _251_展开二维向量 {

    class Vector2D {

        private Queue<Integer> queue;
        public Vector2D(int[][] v) {
            queue = new LinkedList<>();
            for (int i = 0; i < v.length; i++) {
                for (int j = 0; j < v[i].length; j++){
                    queue.offer(v[i][j]);
                }
            }
        }

        public int next() {
            return queue.poll();
        }

        public boolean hasNext() {
            return !queue.isEmpty();
        }
    }
}