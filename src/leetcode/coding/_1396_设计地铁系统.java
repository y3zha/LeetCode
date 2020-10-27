package leetcode.coding;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

// 182周周赛题
// 思路，用一个Map<Integer, Map<String, Integer>> 保存 用户id -> <起始站，入站时间>
// 再用一个Map<String,Map<Double,Integer>> 保存 起始站-终点站 -> <耗费时间，次数>
// 这里就比较巧妙了！用了一个横杠连接起来表示直接到达的情况
public class _1396_设计地铁系统 {

    // 这个写的有点问题！
    class UndergroundSystem {
        Map<Integer, Map<String, Integer>> user;
        Map<String, Map<Long, Integer>> count;

        public UndergroundSystem() {
            user = new HashMap<>();
            count = new HashMap<>();
        }

        public void checkIn(int id, String stationName, int t) {
            user.computeIfAbsent(id, k -> new HashMap<>()).put(stationName, t);
        }

        public void checkOut(int id, String endStation, int endTime) {
            Map.Entry<String, Integer> entry = user.get(id).entrySet().iterator().next();
            String startStation = entry.getKey();
            Integer startTime = entry.getValue();
            String temp = startStation + "-" + endStation;
            int time = endTime - startTime;
            if (count.containsKey(temp)) {
                Map<Long, Integer> map = count.get(temp);
                Map.Entry<Long, Integer> e = map.entrySet().iterator().next();
                Long key = e.getKey() + time;
                Integer val = e.getValue() + 1;
                map.put(key, val);
                count.put(temp, map);
            } else {
                Map<Long, Integer> map = new HashMap<>();
                map.put((long) time, 1);
                count.put(temp, map);
            }
            // 要记得remove
            user.remove(id);
        }

        public double getAverageTime(String startStation, String endStation) {
            String temp = startStation + "-" + endStation;
            if (!count.containsKey(temp)) {
                return 0;
            }
            Map<Long, Integer> map = count.get(temp);
            Map.Entry<Long, Integer> entry = map.entrySet().iterator().next();
            return (entry.getKey() / (double) entry.getValue());
        }
    }

    //采用pair更爽！
    class UndergroundSystem1 {
        Map<Integer, Pair<String, Integer>> user;
        Map<String, Pair<Integer, Integer>> count;

        public UndergroundSystem1() {
            user = new HashMap<>();
            count = new HashMap<>();
        }

        public void checkIn(int id, String stationName, int t) {
            user.put(id, new Pair<>(stationName, t));
        }

        public void checkOut(int id, String stationName, int t) {
            Pair<String, Integer> pair = user.get(id);
            String key = pair.getKey() + "-" + stationName;
            Pair<Integer, Integer> p = count.getOrDefault(key, new Pair<>(0, 0));
            Integer newTimeCount = p.getKey() + t - pair.getValue();
            count.put(key, new Pair<>(newTimeCount, p.getValue() + 1));
            // 要记得remove
            user.remove(id);
        }

        public double getAverageTime(String startStation, String endStation) {
            String key = startStation + "-" + endStation;
            Pair<Integer, Integer> pair = count.get(key);
            return pair.getKey() / (double)pair.getValue();
        }
    }



}