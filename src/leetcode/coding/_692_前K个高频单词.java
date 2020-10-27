package leetcode.coding;

import java.util.*;

/**
 * 给一个单词列表，求出这个列表中出现频次最高的K个单词。
 * 需要按照单词的词频排序后输出，越高频的词排在越前面。如果两个单词出现的次数相同，则词典序小的排在前面
 * 用 O（n log k)的时间和 O(n) 的额外空间完成。
 *
 * 思路很无脑：
 * 先用HashMap统计一下每一个word的频率。建立一个min-heap，把HashMap里的Pair以频率排序，往heap里扔。
 * heap超过k就用poll扔掉一个回到k的size。最后把heap里剩下的k个倒出来。
 *
 * 注意，建小顶堆，因为我们不要的直接poll了！
 */
public class _692_前K个高频单词 {

    private class Pair implements Comparable{
        public String word;
        public int freq;

        public Pair(String word, int freq) {
            this.word = word;
            this.freq = freq;
        }

        @Override
        public int compareTo(Object o) {
            //建小顶堆，都要倒着来
            Pair pair = (Pair) o;
            int diff = this.freq - pair.freq;
            if (diff == 0) {
                diff = pair.word.compareTo(this.word);
            }
            return diff;
        }
    }

    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap<>();

        for (String word : words) {
            if (!map.containsKey(word)) {
                map.put(word, 1);
            } else {
                map.put(word, map.get(word) + 1);
            }
        }

        Queue<Pair> queue = new PriorityQueue<>(k);

        //放进去k个
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            Pair pair = new Pair(entry.getKey(), entry.getValue());
            queue.offer(pair);
            if (queue.size() > k) {
                queue.poll();
            }
        }

        //输出结果
        List<String> res = new ArrayList<>(k);
        int index = k - 1;
        while (!queue.isEmpty()) {
            res.add(queue.poll().word);
        }
        Collections.reverse(res);
        return res;
    }
}