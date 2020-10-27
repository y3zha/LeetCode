package leetcode.coding;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class _379_电话目录管理系统 {

    class PhoneDirectory {
        HashSet<Integer> set;
        int max;
        public PhoneDirectory(int maxNumbers) {
            set = new HashSet<>();
            max = maxNumbers;
        }

        public int get() {
            for (int i = 0; i < max; i++) {
                if (set.contains(i)) {
                    continue;
                }
                set.add(i);
                return i;
            }
            return -1;
        }

        public boolean check(int number) {
            return !set.contains(number);
        }

        public void release(int number) {
            if (set.contains(number)) {
                set.remove(number);
            }
        }
    }
}