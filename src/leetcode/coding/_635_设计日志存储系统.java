package leetcode.coding;

import java.util.*;
import java.util.stream.Collectors;

public class _635_设计日志存储系统 {

    class LogSystem {
        Map<Integer, String> logMap;
        Map<String, Integer> map;

        public LogSystem() {
            logMap = new HashMap<>();
            map = new HashMap<>();
            // gra -> 比较前缀长度
            map.put("Year", 4);
            map.put("Month", 7);
            map.put("Day", 10);
            map.put("Hour", 13);
            map.put("Minute", 16);
            map.put("Second", 19);
        }

        public void put(int id, String timestamp) {
            logMap.put(id, timestamp);
        }

        public List<Integer> retrieve(String s, String e, String gra) {
            Integer len = map.get(gra);
            String ss = s.substring(0, len);
            String ee = e.substring(0, len);

            // 拿到时间
            List<String> list = logMap.values().stream()
                    .filter(str -> str.substring(0, len).compareTo(ss) >= 0 && str.substring(0, len).compareTo(ee) <= 0)
                    .collect(Collectors.toList());
            return logMap.keySet().stream().filter(idx -> list.contains(logMap.get(idx))).collect(Collectors.toList());
        }
    }
}