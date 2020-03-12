package leetcode.coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 组合总和，一个无重复数字的数组，和一个目标数，找出使所有数字和为target的组合
 * 注意：candidates 中的数字可以无限制重复被选取。
 */
public class _039_组合数 {

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return results;
        }
        Arrays.sort(candidates);
        dfs(candidates, target, 0, 0, new ArrayList<Integer>(), results);
        return results;
    }

    private static void dfs(int[] candidates,int target,int startIndex, int crtSum, ArrayList<Integer> list, List<List<Integer>> results) {
        if (crtSum == target) {
            results.add(new ArrayList<>(list));
            return;
        }
        if (crtSum > target) {
            return;
        }
        //crtSum < target的情况
        for (int i = startIndex; i < candidates.length; i++) {
            if (crtSum + candidates[i] > target) {
                continue;
            }
            crtSum += candidates[i];
            list.add(candidates[i]);
            dfs(candidates, target, i, crtSum, list, results);      //startIndex是用来去重的，但是这个数字可以重复选，所以从原位置开始
            list.remove(list.size() - 1);
            crtSum -= candidates[i];
        }
    }

    /**
     * 上面这个方法是不断加到target，下面的写法是不断减，减到0,依旧需要对结果集去重
     */
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return results;
        }
        Arrays.sort(candidates);
        dfs2(candidates, target, 0, new ArrayList<Integer>(), results);
        return results;
    }

    private static void dfs2(int[] candidates, int target, int startIndex, ArrayList<Integer> list, List<List<Integer>> results) {

        if (target < 0) {
            return;
        }
        if (target == 0) {
            results.add(new ArrayList<>(list));
        }

        for (int i = startIndex; i < candidates.length; i++) {
            if (target - candidates[i] < 0) {
                continue;
            }
            target -= candidates[i];
            list.add(candidates[i]);
            dfs2(candidates, target, i, list, results);
            list.remove(list.size() - 1);
            target += candidates[i];
        }
    }

    public static void main(String[] args) {
        int[] A = {2, 3, 6, 7};
        combinationSum(A, 7);

    }
}