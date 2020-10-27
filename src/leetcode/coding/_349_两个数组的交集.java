package leetcode.coding;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class _349_两个数组的交集 {

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int num : nums2) {
            if (map.containsKey(num)) {
                set.add(num);
            }
        }
        return set.stream().mapToInt(Integer::valueOf).toArray();
    }
}