package leetcode.coding;

import java.util.HashMap;
import java.util.LinkedList;

public class _705_设计哈希集合 {

    // 设计hash集合而不是hash表
    // 自定义结构，这里使用拉链法
    // hash  = val MOD base
    // 对于桶的设计，用数组，插入删除 O(n)，查找O(1)，每个桶下挂一个数组

    class MyHashSet {

        private Bucket[] buckets;
        private static final int LEN = 947;

        public MyHashSet() {
            buckets = new Bucket[LEN];
            for (int i = 0; i < LEN; i++) {
                buckets[i] = new Bucket();
            }
        }

        public void add(int key) {
            int hash = key % LEN;
            buckets[hash].insert(key);
        }

        public void remove(int key) {
            int hash = key % LEN;
            buckets[hash].remove(key);
        }

        public boolean contains(int key) {
            int hash = key % LEN;
            return buckets[hash].containsKey(key);
        }
    }

    class Bucket{
        private LinkedList<Integer> list;

        public Bucket() {
            list = new LinkedList<>();
        }

        // 插入
        private void insert(Integer key) {
            if (list.indexOf(key) == -1) {
                list.addFirst(key);
            }
        }

        // 删除
        private void remove(Integer key) {
            if (list.indexOf(key) != -1) {
                list.remove(key);
            }
        }

        // 是否存在
        private boolean containsKey(Integer key) {
            return list.indexOf(key) != -1;
        }
    }
}