package leetcode.coding;

import java.util.*;

public class _350_两个数组的交集II {

    // map <元素，出现次数>
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int num : nums2) {
            if (map.containsKey(num) && map.get(num) > 0) {
                list.add(num);
                map.put(num, map.get(num) - 1);
            }
        }
        return list.stream().mapToInt(Integer::valueOf).toArray();
    }


}