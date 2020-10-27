package leetcode.coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 */
public class _077_组合 {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (k == 0) {
            return res;
        }
        dfs(n, 1, new ArrayList<>(), k, res);
        return res;
    }

    private void dfs(int n, int startIndex, ArrayList<Integer> list, int k, List<List<Integer>> res) {
        if (list.size() == k) {
            res.add(new ArrayList<>(list));
        }
        for (int i = startIndex; i <= n; i++) {
            list.add(i);
            dfs(n, i + 1, list, k, res);    //传i+1而不是startIndex+1
            list.remove(list.size() - 1);
        }
    }

}