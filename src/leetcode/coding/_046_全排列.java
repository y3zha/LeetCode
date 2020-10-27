package leetcode.coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 全排列
 */
public class _046_全排列 {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();

        if (nums == null || nums.length == 0) {
            return results;
        }
        boolean[] visited = new boolean[nums.length];
        dfs(nums, new ArrayList<Integer>(), results, visited);
        return results;
    }

    private void dfs(int[] nums, List<Integer> list, List<List<Integer>> results, boolean[] visited) {
        if (list.size() == nums.length) {
            results.add(new ArrayList<>(list));
            return;
        }
        //遍历每一个
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                list.add(nums[i]);
                visited[i] = true;
                dfs(nums, list, results, visited);
                list.remove(list.size() - 1);
                visited[i] = false;
            }
        }
    }

}
