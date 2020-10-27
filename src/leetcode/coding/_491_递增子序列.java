package leetcode.coding;

import java.util.*;

public class _491_递增子序列 {

    /**
     * 写法一：使用 set 去重
     */
    public static List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }
        dfs(nums, new ArrayList<>(), ans, 0);
        return ans;
    }

    private static void dfs(int[] nums, List<Integer> list, List<List<Integer>> ans, int st) {
        if (list.size() >= 2) {
            ans.add(new ArrayList<>(list));
        }
        // 同一轮中，我只能选最前面的数字
        Set<Integer> set = new HashSet<>();
        for (int i = st; i < nums.length; i++) {
            if (!list.isEmpty() && list.get(list.size() - 1) > nums[i] || set.contains(nums[i])) {
                continue;
            }
            list.add(nums[i]);
            set.add(nums[i]);
            dfs(nums, list, ans, i + 1);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 不使用 set 去重的思路
     * 官方题解写的很不错，使得序列合法，只要给选择一个限定条件即可
     *  1. 首先，只有当当前元素大于等于上一个元素的时候才能选择这个元素，这样枚举出来的肯定都是合法的
     *  2. 其次，如何保证不重复，需要给【不选择】限定一个条件，只有当前元素不等于上一个选择的元素的时候，那么才考虑不选当前元素，直接递归后面的
     *      如果有两个相同的元素，考虑这样四种情况
     *        前者被选择，后者被选择
     *        前者被选择，后者不被选择
     *        前者不被选择，后者被选择
     *        前者不被选择，后者不被选择
     *   其中第二种情况和第三种情况其实是等价的，我们这样限制之后，舍弃了第二种，保留了第三种，于是达到了去重的目的。
     */

    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> findSubsequences2(int[] nums) {
        helper(nums, 0, 0x80000000, new ArrayList<>());
        return ans;
    }

    private void helper(int[] nums, int cur, int last, List<Integer> list) {
        if (cur == nums.length ) {
            if (list.size() >= 2) {
                ans.add(new ArrayList<>(list));
            }
            return;
        }
        // 当前选择
        if (nums[cur] >= last) {
            list.add(nums[cur]);
            helper(nums, cur + 1, nums[cur], list);
            list.remove(list.size() - 1);
        }
        // 当前不选择
        if (nums[cur] != last) {
            helper(nums, cur + 1, last, list);
        }
    }


    public static void main(String[] args) {
        findSubsequences(new int[]{4, 7, 7});
    }
}