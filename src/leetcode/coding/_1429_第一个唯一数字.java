package leetcode.coding;

import java.util.*;

public class _1429_第一个唯一数字 {

    class FirstUnique {
        HashMap<Integer, Integer> map = new HashMap<>();
        Queue<Integer> q = new LinkedList<>();

        public FirstUnique(int[] nums) {
            for (int num : nums) {
                add(num);
            }
        }

        public int showFirstUnique() {
            while (!q.isEmpty()) {
                int num = q.peek();
                if (map.get(num) > 1) q.poll();
                else return num;
            }
            return -1;
        }

        public void add(int value) {
            if (map.containsKey(value)) {
                map.put(value, map.get(value) + 1);
            } else {
                map.put(value, 1);
                q.offer(value);
            }
        }
    }
}