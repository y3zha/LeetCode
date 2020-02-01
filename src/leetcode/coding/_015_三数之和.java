package leetcode.coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和。写一个通用解法，K数之和，实际上是一个背包问题，给定一个数组，选出K个数，使其和为0，找出满足条件的有多少种情况，那就用dp
 * 这个题要返回结果集，使用组合的思想
 */
public class _015_三数之和 {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        //target是0，就不传了
        dfs(nums, 0, 0,visited,new ArrayList<Integer>(), res, 0);
        return res;
    }

    //使用dfs超出了时间限制，但是思路是正确的
    private void dfs(int[] nums, int startIndex, int crtSum, boolean[] visited, ArrayList<Integer> list, List<List<Integer>> res, int count) {
        if (count == 3 && crtSum == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = startIndex; i < nums.length; i++) {
            //去重,如果后面的数和前面一样，但是前面没选，那后面的这个就不能选
            if (!visited[i] && i > startIndex && nums[i] == nums[i - 1]) {
                continue;
            }
            if (!visited[i]) {
                list.add(nums[i]);
                visited[i] = true;
                crtSum += nums[i];
                count++;
                dfs(nums, i + 1, crtSum, visited, list, res, count);
                count--;
                crtSum -= nums[i];
                visited[i] = false;
                list.remove(list.size() - 1);
            }
        }
    }
}