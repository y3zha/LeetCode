package leetcode.coding;

import java.util.HashMap;
import java.util.Map;

public class _1365_有多少小于当前数字的数字 {


    public int[] smallerNumbersThanCurrent(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) map.put(num, map.getOrDefault(num, 0) + 1);
        int n = nums.length;
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            int val = nums[i], cnt = 0;
            for (Integer key : map.keySet()) {
                if (key < val) {
                    cnt += map.get(key);
                }
            }
            ans[i] = cnt;
        }
        return ans;
    }

}