package leetcode.coding;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * dp，子序列，不是子串，子序列可以不连续
 * dp[i]代表以位置i结尾的最长子序列
 */
public class _300_最长上升子序列 {

    //暴力dp
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[n + 1];
        int res = 1;
        for (int i = 1; i <= n; i++) {
            dp[i] = 1;
            for (int j = 1; j < i; j++) {
                if (nums[j - 1] < nums[i - 1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                res = Math.max(res, dp[i]);
            }
        }
        return res;
    }

    //使用二分优化
    //优化:一旦前面有两个dp值一样了，比如dp[i] = dp[j],并缺nums[i] > nums[j] ，那就只要考虑第j个就可以了
    //启示：同样的dp值，存一个坐标，这个坐标对应的nums[index]值最小。
    //怎么做？对于每个dp值，保存对应的nums[i]的值
    //序列是单调上升的，在单调上升中找最后一个比自己小的数用二分法 lon2n，二分法很重要
    public static int lengthOfLIS2(int[] nums) {
        if (nums == null ||nums.length == 0) return 0;
        int n = nums.length;
        int[] a = new int[n];
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int idx = Arrays.binarySearch(a, 0, res, nums[i]);
            if (idx < 0) {
                idx = -idx - 1;
            }
            //把这个nums[i]放在插入位置上
            a[idx] = nums[i];
            if (idx == res) {
                res++;
            }
        }
        for (int i = 0; i < res; i++) {
            System.out.println(a[i]);
        }
        return res;
    }


    //利用更简单的API TreeSet的Ceiling方法
    //TreeSet.ceiling(x)方法可以直接找出set中大于x的最小数字，如果不存在则返回null。
    //1. 如果这个数字存在，则删除这个数字，然后把x插入set中，相当于代替该数字。
    //2. 如果这个数字不存在，说明x大于set中任何数字，直接把x插入set中。
    //最后返回set的大小即可。

    public static int lengthOfLIS3(int[] nums) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            Integer c = set.ceiling(nums[i]);
            //如果set中没有大于nums[i]的最小数字，那么就符合最长上升子序列，加入
            //如果有，把让那个移除那个数字，换做这个nums[i]，因为nums[i]更小
            if (c == null) {
                set.add(nums[i]);
            } else {
                set.remove(c);
                set.add(nums[i]);
            }
        }
        for (Integer integer : set) {
            System.out.println(integer);
        }
        return set.size();
    }

    public static void main(String[] args) {
        int[] a = {1, 5, 10, 20, 8 };
        lengthOfLIS2(a);
    }




}