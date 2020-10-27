package leetcode.coding;

import java.util.LinkedList;

public class _706_设计哈希映射 {

    class MyHashMap {

        private static final int LEN = 1023;
        private Bucket[] buckets;

        public MyHashMap() {
            buckets = new Bucket[1023];
            for (int i = 0; i < LEN; i++) {
                buckets[i] = new Bucket();
            }
        }

        public void put(int key, int value) {
            int hash = key % LEN;
            buckets[hash].put(key, value);
        }

        public int get(int key) {
            int hash = key % LEN;
            return buckets[hash].get(key);
        }

        public void remove(int key) {
            int hash = key % LEN;
            buckets[hash].remove(key);
        }
    }

    class Bucket{
        LinkedList<Node> list;

        public Bucket() {
            list = new LinkedList<>();
        }

        public void put(Integer key, Integer val) {
            // 遍历这个链表里所有的 Node，看有没有这个key,如果有的话
            for (Node node : list) {
                if (node.key.equals(key)) {
                    node.val = val;
                    return;
                }
            }
            Node node = new Node(key, val);
            list.addLast(node);
        }

        public Integer get(Integer key) {
            for (Node node : list) {
                if (node.key.equals(key)) {
                    return node.val;
                }
            }
            return -1;
        }

        public void remove(Integer key) {
            // 根据key找到节点删除
            list.removeIf(node -> node.key.equals(key));
        }
    }

    class Node{
        Integer key;
        Integer val;

        public Node(Integer key, Integer val) {
            this.key = key;
            this.val = val;
        }
    }
}