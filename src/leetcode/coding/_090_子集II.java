package leetcode.coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * <p>
 * 说明：解集不能包含重复的子集。
 */
public class _090_子集II {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }
        Arrays.sort(nums);
        dfs(nums, 0, new ArrayList<Integer>(), ans);
        return ans;
    }

    private void dfs(int[] nums, int st, ArrayList<Integer> list, List<List<Integer>> ans) {
        ans.add(new ArrayList<>(list));
        for (int i = st; i < nums.length; i++) {
            if (i > st && nums[i-1] == nums[i]) continue;
            list.add(nums[i]);
            dfs(nums, i + 1, list, ans);
            list.remove(list.size() - 1);
        }
    }


    // public List<List<Integer>> subsetsWithDup2(int[] nums) {
    //     List<List<Integer>> res = new ArrayList<>();
    //     if (nums == null || nums.length == 0) {
    //         return res;
    //     }
    //     //做这种组合、全排列、子集问题一定要先排序，因为去重是按照顺序来的！
    //     Arrays.sort(nums);
    //     boolean[] visited = new boolean[nums.length];       //这边不用visited也可以把
    //     dfs(nums, 0, new ArrayList<Integer>(), res, visited);
    //     return res;
    // }
    //
    // private void dfs(int[] nums, int startIndex, ArrayList<Integer> list, List<List<Integer>> res, boolean[] visited) {
    //     res.add(new ArrayList<>(list));
    //     for (int i = startIndex; i < nums.length; i++) {
    //         //去重。如果这个数字和前面数字一样，且前面数字没有选，那这个数字就不能选
    //         if (i > startIndex && nums[i] == nums[i - 1] && !visited[i]) {
    //             continue;
    //         }
    //         if (!visited[i]) {
    //             list.add(nums[i]);
    //             visited[i] = true;
    //             dfs(nums, i + 1, list, res, visited);
    //             visited[i] = false;
    //             list.remove(list.size() - 1);
    //         }
    //     }
    // }
}