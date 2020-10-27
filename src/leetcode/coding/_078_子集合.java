package leetcode.coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 子集，dfs组合问题
 *
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。比如[1,2][2,1]就是重复的
 *
 */
public class _078_子集合 {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }

        dfs(nums, 0, new ArrayList<>(), ans);
        return ans;
    }

    private void dfs(int[] nums, int st, ArrayList<Integer> list, List<List<Integer>> ans) {
        ans.add(new ArrayList<>(list));
        for (int i = st; i < nums.length; i++) {
            list.add(nums[i]);
            dfs(nums, i + 1, list, ans);    // 从 i+1开始，而不是从 idx+1开始，组合需要保持一定的顺序性
            list.remove(list.size() - 1);
        }
    }


    // 其实不需要 vis 数字，因为每个元素都唯一，只访问一次，往后访问即可
    // public List<List<Integer>> subsets2(int[] nums) {
    //     List<List<Integer>> res = new ArrayList<>();
    //     if (nums == null || nums.length == 0) {
    //         return res;
    //     }
    //     Arrays.sort(nums);
    //     boolean[] visited = new boolean[nums.length];
    //     dfs(nums, 0, new ArrayList<Integer>(), res, visited);
    //     return res;
    // }
    //
    // private void dfs(int[] nums, int startIndex, ArrayList<Integer> list, List<List<Integer>> res, boolean[] visited) {
    //     res.add(new ArrayList<>(list));
    //     if (startIndex == nums.length) {
    //         return;
    //     }
    //     for (int i = startIndex; i < nums.length; i++) {
    //         if (!visited[i]) {
    //             list.add(nums[i]);
    //             visited[i] = true;
    //             dfs(nums, i + 1, list, res, visited);   //传i+1而不是startIndex+1
    //             visited[i] = false;
    //             list.remove(list.size() - 1);
    //         }
    //     }
    // }
}