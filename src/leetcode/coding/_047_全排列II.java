package leetcode.coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 全排列2，有重复数字
 */
public class _047_全排列II {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return results;
        }
        //需要先排序
        Arrays.sort(nums);

        boolean[] visited = new boolean[nums.length];
        dfs(nums, new ArrayList<Integer>(), results, visited);
        return results;
    }

    private void dfs(int[] nums, ArrayList<Integer> list, List<List<Integer>> results, boolean[] visited) {
        if (list.size() == nums.length) {
            results.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            //如果这个数字在前面出现了，但是没有选前面的那个数字，这个数字就不能用，需要跳过2
            if (i > 0 && !visited[i - 1] && nums[i] == nums[i - 1]) {
                continue;
            }
            if (!visited[i]) {
                list.add(nums[i]);
                visited[i] = true;
                dfs(nums, list, results, visited);
                visited[i] = false;
                list.remove(list.size() - 1);
            }
        }
    }

}