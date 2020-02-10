package leetcode.coding;

import java.util.*;

/**
 * 一种方法是直接用map统计词频，排序，这样子操作
 *
 * 第二种办法是利用堆，构造个节点，存放值和出现频率
 */
public class _347_前K个高频元素 {

    //第二种方法
    class Node implements Comparable {
        int val;
        int freq;

        public Node(int val, int freq) {
            this.val = val;
            this.freq = freq;
        }

        @Override
        public int compareTo(Object o) {
            Node node = (Node) o;
            return node.freq - this.freq;
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        //值->node
        HashMap<Integer, Node> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                Node node = new Node(num, 1);
                map.put(num, node);
            } else {
                Node node = map.get(num);
                node.freq++;
            }
        }
        PriorityQueue<Node> pq = new PriorityQueue<>(nums.length);
        for (Integer key : map.keySet()) {
            pq.add(map.get(key));
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            res.add(pq.poll().val);
        }
        return res;
    }
}