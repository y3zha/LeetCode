package leetcode.coding;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 水题
 */
public class _451_根据字符出现频率排序 {

    public String frequencySort(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        ArrayList<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                int diff = o2.getValue() - o1.getValue();
                if (diff == 0) {
                    diff = o2.getKey() - o1.getKey();
                }
                return diff;
            }
        });
        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<Character, Integer>> it = list.iterator();
        while (it.hasNext()) {
            Map.Entry<Character, Integer> entry = it.next();
            Character key = entry.getKey();
            Integer value = entry.getValue();
            for (int i = 0; i < value; i++) {
                sb.append(key);
            }
        }
        return sb.toString();
    }
}