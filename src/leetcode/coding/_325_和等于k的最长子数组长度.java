package leetcode.coding;

import java.util.HashMap;

public class _325_和等于k的最长子数组长度 {

    // 前缀和即可
    public static int maxSubArrayLen(int[] nums, int k) {
        int n = nums.length;
        // 值和下标
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int curSum = 0, res = 0;
        for (int i = 0; i < n; i++) {
            curSum += nums[i];
            if (map.containsKey(curSum - k)) {
                Integer idx = map.get(curSum - k);
                res = Math.max(res, i - idx + 1);
            }
            // 这里放的时候要注意，如果两个地方的前缀和一样，也就是之前放过，那我们就不要再放进去了，因为我们要最长的子数组
            if (!map.containsKey(curSum)) {
                map.put(curSum, i);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, -1, 5, -2, 3};
        maxSubArrayLen(nums, 3);

    }

}