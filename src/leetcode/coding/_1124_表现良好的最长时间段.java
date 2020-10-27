package leetcode.coding;

import java.util.HashMap;
import java.util.Map;

public class _1124_表现良好的最长时间段 {

    /*
    输入：hours = [9,9,6,0,6,6,9]
    输出：3
    解释：最长的表现良好时间段是 [9,9,6]。
    比如 [9,9,6]，劳累天数2天，不劳累天数1天，这说明是表现良好的时间段
    求这个最长的时间段
     */

    // 暴力n2
    public int longestWPI(int[] hours) {
        int n = hours.length;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = hours[i] > 8 ? 1 : -1;
        }
        int[] ps = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            ps[i] = ps[i - 1] + arr[i - 1];
        }
        for (int i = 0; i < ps.length; i++) {
            System.out.print(ps[i] + " ");
        }
        int res = 0;
        //枚举右端点j
        for (int j = 1; j <= n; j++) {
            //枚举左端点i
            for (int i = 0; i < j; i++) {
                if (ps[j] - ps[i] > 0) {
                    res = Math.max(res, j - i);
                    // 剪枝
                    if (j - i < res) {
                        break;
                    }
                }
            }
        }
        return res;
    }

    // 前缀和加哈希
    public int longestWPI2(int[] hours) {
        int res = 0;
        int cnt = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < hours.length; i ++) {
            cnt += hours[i] > 8 ? 1 : -1;
            // 只要cnt大于0，都是良好时间段
            if (cnt > 0) {
                res = i + 1;
            } else {
                // 如果碰到了同样的cnt，不用更新，如果不存在这个key，就放进去
                map.putIfAbsent(cnt, i);
                // 用 cnt - 1 去字典里找，如果找到了下标 j，那么就说明从 0 到 j 的前缀和是 cnt-1，而从0到 i 的前缀和是 cnt
                // 那么显然从 j 到 i的和是（cnt - (cnt - 1)） = 1 > 0，也就是说从 j+1到 i 的表现肯定是满足的
                // 并且由于 j 是 cnt-1中最小的，所以 i-j 是最大的。
                // 此时再跟 res 比较看是否需要更新。
                if (map.containsKey(cnt - 1)) {
                    res = Math.max(res, i - map.get(cnt - 1));
                }
                // 上面为什么只需要查找 cur-1？因为满足条件的前缀和只能是小于等于cur-1的，也就是说其实也可以查找 cur-2,cur-3...，但是，cur-2的下标一定不可能在 cur-1的下标左边。使用反证法，前提是cur-1代表的是最小下标，那么如果 cur-2在 cur-1左边，而cur-2的左边一定还会有 cur-1出现（cur值是从0开始的），这就和最小下标的前提矛盾了。
                // 那么问题又来了，如果 cur-1不存在，是否要查找 cur-2,cur-3...呢？
                // 也不需要，思路跟上面是一样的，如果 cur-1不存在，cur-2,cur-3...一定也不存在。举个例子，不可能从0跳到-2，-3，而中间没有-1。

            }
        }
        return res;
    }


}