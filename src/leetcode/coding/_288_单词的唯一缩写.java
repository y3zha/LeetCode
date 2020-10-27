package leetcode.coding;

import java.util.HashMap;

public class _288_单词的唯一缩写 {

    class ValidWordAbbr {

        // s -> abbr
        HashMap<String, String> map;
        // abbr -> cnt
        HashMap<String, Integer> cnt;

        public ValidWordAbbr(String[] dictionary) {
            map = new HashMap<>();
            cnt = new HashMap<>();
            for (String s : dictionary) {
                String abbr = getAbbr(s);
                if (!map.containsKey(s)) {
                    map.put(s, abbr);
                    cnt.put(abbr, cnt.getOrDefault(abbr, 0) + 1);
                }
            }
        }

        public boolean isUnique(String word) {
            if (map.containsKey(word)) {
                Integer sum = cnt.get(map.get(word));
                return sum == null || sum < 2;
            }
            String abbr = getAbbr(word);
            return cnt.get(abbr) == null || cnt.get(abbr) == 0;
        }

        private String getAbbr(String s) {
            int n = s.length();
            if (n <= 2) {
                return s;
            }
            return String.valueOf(s.charAt(0)) + (n - 2) + s.charAt(n - 1);
        }
    }
}