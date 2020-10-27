package leetcode.coding;

import java.io.*;
import java.util.HashMap;

public class _560_和为K的子数组 {

    /**
     * 这个题和713题很相似
     *
     * 一种写法是和760题我的滑动动窗口的写法一样，可能会超时，还有种写法就是760题的第二种写法
     * 双指针
     *
     * 这边写双指针，感觉不是很方便，还是用前缀和
     */
    public static int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] ps = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            ps[i] = nums[i - 1] + ps[i - 1];
        }
        int res = 0;;
        for (int s = 0; s < n; s++) {
            for (int e = s + 1; e <= n; e++) {
                if (ps[e] - ps[s] == k) {
                    res++;
                }
            }
        }
        return res;
    }

    /**
     * 时间复杂度还是很高
     * 可以通过Prefix sum来求解。假设我们令P[i] = A[0] + A[1] + ... + A[i-1]和P[j] = A[0] + A[1] + ... + A[j-1]
     * 那么P[j] - P[i] = A[i] + A[i+1] + ... + A[j-1]。如果P[j] - P[i] == S的话，那么[i,j]就是我们需要的区间。
     * 所以我们对于每个j，我们只要计算有多少个i使得P[j] - P[i] == S，这样我们就得到了以P[j]作为右区间并且和为S的区间数。
     * 对于A中的每个元素都做同样的处理，最有将所有的结果相加即可。
     *
     * 具体实现上，我们通过hash_map记录P[j]。初始化的时候要注意一个细节，对于dict[0]=1。
     * 为什么？因为当P[j]==S时，P[i]=0并且此时我们的result=1。
     */

    public static int subarraySum2(int[] nums, int k) {
        int res = 0;
        int curSum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            curSum += nums[i];
            if (map.containsKey(curSum - k)) {
                res += map.get(curSum - k);
            }
            map.put(curSum, map.getOrDefault(curSum, 0) + 1);
        }
        return res;
    }
}