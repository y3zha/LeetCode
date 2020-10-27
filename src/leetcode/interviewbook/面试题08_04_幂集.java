package leetcode.interviewbook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 面试题08_04_幂集 {

    //得到所有子集合
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null) {
            res.add(new ArrayList<>());
            return res;
        }
        Arrays.sort(nums);
        boolean[] vis = new boolean[nums.length];
        dfs(nums, 0, res, new ArrayList<Integer>(), vis);
        return res;
    }

    private void dfs(int[] nums, int start, List<List<Integer>> res, ArrayList<Integer> list, boolean[] vis) {
        res.add(new ArrayList<>(list));
        for (int i = start; i < nums.length; i++) {
            //去重
            if (i != start && nums[i - 1] == nums[i] && !vis[i - 1]) {
                continue;
            }
            if (!vis[i]) {
                vis[i] = true;
                list.add(nums[i]);
                dfs(nums, i + 1, res, list, vis);
                list.remove(list.size() - 1);
                vis[i] = false;
            }
        }
    }
}