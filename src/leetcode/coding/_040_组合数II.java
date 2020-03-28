package leetcode.coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用一次。里面的数字可能是重复的
 *
 */
public class _040_组合数II {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        Arrays.sort(candidates);
        boolean[] visited = new boolean[candidates.length];
        dfs(candidates, target, 0, new ArrayList<Integer>(), res, visited);
        return res;
    }

    //需要对输出去重，就需要startIndex
    private void dfs(int[] candidates, int target, int startIndex, ArrayList<Integer> list, List<List<Integer>> res, boolean[] visited) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = startIndex; i < candidates.length; i++) {
            if (!visited[i]) {
                if (target - candidates[i] < 0) {
                    return;
                }

                //在这对输入去重
                //比如1 1 2，要求和是3，答案只有一个，就是1 2，但这里有两个1，1'和1''，这可能导致答案是1'2 和1''2，这就需要对输入去重
                //如果当前数字在前面出现了（candidates[i] == candidates[i - 1]），但是前面没选（i>startIndex,至少是后面一个，不能是自己本身），那么在这里也不能选
                if (i > 0 && candidates[i] == candidates[i - 1] && i > startIndex) {
                    continue;
                }
                target -= candidates[i];
                visited[i] = true;
                list.add(candidates[i]);
                dfs(candidates, target, i + 1, list, res, visited); //不能重复选，就需要从i+1开始dfs
                list.remove(list.size() - 1);
                visited[i] = false;
                target += candidates[i];
            }
        }
    }
}