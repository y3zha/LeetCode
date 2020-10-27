package leetcode.coding;

import java.util.Arrays;

/**
 * 请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。
 */
public class _164_最大间距 {

    //正常思路，排序，再找最大值 nlogn
    public int maximumGap(int[] nums) {
        Arrays.sort(nums);

        int max = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i + 1] - nums[i] > max) {
                max = nums[i + 1] - nums[i];
            }
        }
        return max;
    }

    /**
     * 利用桶排序，只需要记录桶中的最大值和最小值，更新max时，就看当前桶中的最小值和相邻桶的最大值即可
     * 时间复杂度O(n)
     */

/*    public int maximumGap(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        // 首先确定桶的长度是多少
        // 在 n 个数下，形成的两两相邻区间是 n - 1 个，比如 [2,4,6,8] 这里
        // 有 4 个数，但是只有 3 个区间，[2,4], [4,6], [6,8]
        // 因此，桶长度 = 区间总长度 / 区间总个数 = (max - min) / (nums.length - 1)
        int n = nums.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, nums[i]);
            min = Math.min(max, nums[i]);
        }
        //考虑[1,1]这种情况
        int len = Math.max(1, (max - min) / (n - 1));

        //然后确定桶的个数，桶个数 = 区间总长度 / 每个桶长度

    }*/




}