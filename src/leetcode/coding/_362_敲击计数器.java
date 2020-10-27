package leetcode.coding;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class _362_敲击计数器 {

    class HitCounter {

        int lastHit;
        TreeMap<Integer, Integer> map;

        public HitCounter() {
            lastHit = 0;
            map = new TreeMap<>();
        }

        public void hit(int timestamp) {
            if (lastHit == 0) {
                map.put(timestamp, 1);
            } else {
                map.put(timestamp, map.get(lastHit) + 1);
            }
            lastHit = timestamp;
        }

        // 统计过去五分钟内敲击的次数
        public int getHits(int timestamp) {
            if (lastHit == 0) {
                    return 0;
            }
            int start = timestamp - 300;
            if (start <= 0) {
                return map.get(lastHit);
            }
            Integer key = map.floorKey(start);
            if (key == null) return map.get(lastHit);
            return map.get(lastHit) - map.get(key);
        }
    }
}