package leetcode.coding;

import java.util.Arrays;
import java.util.HashMap;

public class _001_两数之和 {

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map;
        map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i]) && map.get(target - nums[i]) != i) {
                return new int[]{i, map.get(target - nums[i])};
            }
        }
        return new int[]{};
    }

}