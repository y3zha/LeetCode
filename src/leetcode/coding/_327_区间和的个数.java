package leetcode.coding;

import java.util.TreeMap;

/**
 * 返回区间和在 [lower, upper] 之间的个数
 *
 * https://www.acwing.com/file_system/file/content/whole/index/content/5609/
 */
public class _327_区间和的个数 {

    /*
    首先想到了前缀和
    前缀和数组为sum[];
    满足条件的区间和为：
    lower <= sum[i] - sum[j] <= upper;
    将上述式子变形得到：
    sum[i] - upper <= sum[j] <= sum[i] - lower;
    也就是说在前缀和数组sum[0...i]中，满足上述条件的sum[j]都对应着一个满足条件的区间;
    参考题解 https://leetcode-cn.com/problems/count-of-range-sum/solution/treemap-onlognfang-fa-by-wdw87/
    // 关于 subMap方法 https://www.breakyizhan.com/java/5499.html
    http://gitbook.net/java/util/treemap_submap_inclusive.html
     */

    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) return 0;
        // 前缀和 -> 出现次数
        TreeMap<Long, Integer> map = new TreeMap<>();
        map.put(0L, 1);
        int res = 0, n = nums.length;
        long prefixSum = 0L;
        for (int i = 0; i < n; i++) {
            prefixSum += nums[i];
            // sum[i] - upper <= sum[j] <= sum[i] - lower;
            // 加上两个true就是代表左右端点同时包含
            for (Integer value : map.subMap(prefixSum - upper, true, prefixSum - lower, true).values()) {
                System.out.println(value);
                res += value;
            }
            System.out.println("---");
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }
        return res;
    }

    /**
     * https://www.acwing.com/file_system/file/content/whole/index/content/5609/
     * 树状数组，同493、315
     * 这道题一个很直观的做法就是记录前缀和，然后使用双层循环遍历所有的区间，时间复杂度O(n2)。
     * 我们考虑如何来优化这个这个双层循环，我们在固定子数组的右边界的时候，采用遍历的方式求出所有区间和在[lower,upper]之间的数组个数
     * 我们可以以更优的方式求解所有可行的区间。假设右区间为A[j]，前缀和为preSum[j]
     * lower <= ps[j] - ps[i] <= upper，我们固定了右端点j，想知道这样的i有多少个
     * ps[j] - upper <= ps[i] <= ps[j] - lower,需要求的就是所有满足前面这个的个数(i < j)
     * 也可以是 ps[i] + lower <= ps[j] <= upper +ps[i],这个就是固定左端点，看这样的右端点有几个
     * 求某一个区间内的个数，我们可以使用树状数组或者线段树来求解。
     *
     * 我们每次读到一个数，先把合法区间内前缀和的个数求出来（区间查询）
     * 然后将当前前缀和出现的次数加上一（单点更新）
     *
     * 思路：1、求前缀和数组，并将其离散化
     */

    // public int countRangeSum(int[] nums, int lower, int upper) {
    //     int n = nums.length;
    //     int[] ps = new int[n + 1];
    //     for (int i = 1; i <= n; i++) {
    //         ps[i] = ps[i - 1] + nums[i - 1];
    //     }
    //
    // }



    public class BIT{
        int n;
        int[] c;

        public BIT(int n) {
            this.n = n;
            c = new int[n + 1];
        }

        public void update(int idx, int val) {
            while (idx <= n) {
                c[idx] += val;
                idx += lowbit(idx);
            }
        }

        public int query(int idx) {
            int res = 0;
            while (idx > 0) {
                res += c[idx];
                idx -= lowbit(idx);
            }
            return res;
        }

        private int lowbit(int idx) {
            return idx & (-idx);
        }
    }


}