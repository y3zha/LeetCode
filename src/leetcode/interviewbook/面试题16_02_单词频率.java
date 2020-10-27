package leetcode.interviewbook;

import java.util.HashMap;
import java.util.Map;

public class 面试题16_02_单词频率 {

    class WordsFrequency {

        Map<String, Integer> map;

        public WordsFrequency(String[] book) {
            map = new HashMap<>();
            for (String s : book) {
                map.put(s, map.getOrDefault(s, 0) + 1);
            }
        }

        public int get(String word) {
            return map.getOrDefault(word, 0);
        }
    }
}