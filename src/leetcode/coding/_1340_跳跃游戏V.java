package leetcode.coding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class _1340_跳跃游戏V {

    /**
     * 起跳点必须高于它到落地点所有柱子的高度
     * 返回最多可以访问多少个下标。
     * 动态规划 dp[i]代表从这个位置起跳最多可以访问多少个柱子，怎么转移？
     * 它可以向左跳也可以向右跳，两边只能选一个较大的，再加上它自己本身（1），就是dp[i]
     * 因为第一步只能往一个方向跳，
     *
     * 从哪里开始计算呢？不是对数组从左往右算，也不是从数组的右往左算，应该是从矮的柱子开始往高的柱子算。比如我要得到柱子13的状态，肯定要先得到矮的的状态
     * 因为dp本身就是【自底向上】
     *
     * 把柱子从矮到高排序，每根柱子的下标存在一个容器中即可。
     */

    public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        int[] idxs = new int[n];
        int[] dp = new int[n];

        // index -> val (高度)
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i, arr[i]);
        }
        ArrayList<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());

        for (int i = 0; i < n; i++) {
            idxs[i] = list.get(i).getKey();
        }

        int res = 0;

        // 柱子由低到高排序，取出下标
        for (int _i = 0; _i < n; _i++) {
            int i = idxs[_i];
            dp[i] = 1;

            // 访问它左边的所有柱子
            for (int j = i - 1; j >= 0 && i - j <= d; j--) {
                if (arr[j] >= arr[i]) {
                    break;
                }
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }

            // 访问它右边的柱子
            for (int j = i + 1; j < n && j - i <= d; j++) {
                if (arr[j] >= arr[i]) {
                    break;
                }
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }


}