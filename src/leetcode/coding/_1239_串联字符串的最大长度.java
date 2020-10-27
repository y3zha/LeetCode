package leetcode.coding;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _1239_串联字符串的最大长度 {

    /*
    对于 arr 列表中的每个字符串，无非就是选或者不选，这就相当于一颗二叉树，左边是选择，右边是不选择，我们的目的就是在所有的分支中选择出长度最长的
    当然添加的时候要check下字符是否只出现过一次
     */

    public int maxLength(List<String> arr) {
        Map<Character, Integer> map = new HashMap<>();
        return dfs(arr, 0, map);
    }

    private int dfs(List<String> arr, int idx, Map<Character, Integer> map) {
        if (idx == arr.size()) {
            return 0;
        }
        // 表示加入之后的状态的 map
        HashMap<Character, Integer> nMap = new HashMap<>(map);
        if (isUnique(arr.get(idx), nMap)) {
            // 说明能放入
            // 放和不放两种策略
            int a = arr.get(idx).length() + dfs(arr, idx + 1, nMap);
            int b = dfs(arr, idx + 1, map);
            return Math.max(a, b);
        }
        // 只能不放
        return dfs(arr, idx + 1, map);

    }

    private boolean isUnique(String s, Map<Character, Integer> map) {
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (Character c : map.keySet()) {
            if (map.get(c) > 1) {
                return false;
            }
        }
        return true;
    }


    /*
    空间效率太低，使用位压缩改进，一共26个字母，使用一个int来表示即可
     */
    public int maxLength2(List<String> arr) {
        int state = 0;
        return dfs2(arr, 0, state);
    }

    private int dfs2(List<String> arr, int idx, int state) {
        if (idx == arr.size()) {
            return 0;
        }
        int ns = state; // 表示加入之后的状态
        boolean flag = true;
        String s = arr.get(idx);
        for (int i = 0; i < s.length(); i++) {
            int num = s.charAt(i) - 'a';
            // (state) & (1 << num) != 0
            if (((ns >> num) & 1) == 1) {
                flag = false;
                break;
            }
            ns |= (1 << num);
        }

        if (flag) {
            // 说明能放入
            // 放和不放两种策略
            int a = arr.get(idx).length() + dfs2(arr, idx + 1, ns);
            int b = dfs2(arr, idx + 1, state);
            return Math.max(a, b);
        }
        // 只能不放
        return dfs2(arr, idx + 1, state);
    }


}