package leetcode.coding;

import java.util.ArrayList;
import java.util.List;

/**
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 */
public class _216_组合总和III {

    public static List<List<Integer>> combinationSum3(int k, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (target == 0) {
            return res;
        }
        dfs(target, k,1, new ArrayList<Integer>(), res);
        return res;
    }

    private static void dfs(int target, int k, int startIndex, ArrayList<Integer> list, List<List<Integer>> res) {
        //超过k此了，或target<0
        if (list.size() > k || target < 0) {
            return;
        }
        if (list.size() == k && target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = startIndex; i <= 9; i++) {
            list.add(i);
            dfs(target - i, k, i + 1, list, res);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        combinationSum3(3, 7);
    }
}