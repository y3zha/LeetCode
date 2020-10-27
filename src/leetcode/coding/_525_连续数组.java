package leetcode.coding;

import java.util.HashMap;
import java.util.Map;

public class _525_连续数组 {

    // 前缀和  + hash
    public int findMaxLength(int[] nums) {
        int n = nums.length;
        int ans = 0;
        int ps = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < n; i++) {
            // 把 0 视为 -1
            ps += nums[i] == 1 ? 1 : -1;
            if (map.containsKey(ps)) {
                ans = Math.max(ans, i - map.get(ps));
            } else {
                map.put(ps, i);
            }
        }
        return ans;
    }
}