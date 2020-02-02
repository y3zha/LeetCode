package leetcode.competition.week174;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 起跳点必须高于它到落地点所有柱子的高度
 * 返回可以为访问多少个下标。
 * 动态规划 dp[i]代表从这个位置起跳最多可以访问多少个柱子，怎么转移？它可以向左跳也可以向右跳，两边只能选一个较大的，再加上它自己本身（1），就是dp[i]
 * 因为第一步只能往一个方向跳，
 *
 * 从哪里开始计算呢？不是对数组从左往右算，也不是从数组的右往左算，应该是从矮的柱子开始往高的柱子算。比如我要得到柱子13的状态，肯定要先得到矮的的状态
 * 因为dp本身就是【自底向上】
 *
 * 把柱子从矮到高排序，每根柱子的下标存在一个容器中即可。
 */
public class _5331_跳跃游戏V {

    public static int maxJumps(int[] arr, int d) {
        int n = arr.length;
        int[] idxs = new int[n];
        int[] dp = new int[n];
        int index = 0;
        //把柱子从矮到高排序，每根柱子的下标保存在idx中。
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(i, arr[i]); //index -> val
        }
        //根据value升序排序
        ArrayList<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, (o1, o2) -> o1.getValue() - o2.getValue());
        for (int i = 0; i < list.size(); i++) {
            idxs[index++] = list.get(i).getKey();
        }
        for (int i = 0; i < n; i++) {
            System.out.print(idxs[i] + " ");
        }

        int res = 0;

        for (int _i = 0; _i < n; _i++) {
            //取出下标
            int i = idxs[_i];
            //初始化dp数组中的这个下标
            dp[i] = 1;
            //访问它左边的
            for (int j = i - 1; j >= 0 && j >= i - d; j--) {
                //只要这个高度大于等于idx处的高度，那就只能break了
                if (arr[j] >= arr[i]) {
                    break;
                }
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            
            //访问它右边的
            for (int j = i + 1; j < n && j <= i + d; j++) {
                if (arr[j] >= arr[i]) {
                    break;
                }
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            
            //更新结果
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] a = {6, 4, 14, 6, 8, 13, 9, 7, 10, 6, 12};
        maxJumps(a, 2);

    }

}