package leetcode.coding;

import java.util.Arrays;

public class _1024_视频拼接 {


    /**
     * 思路一：动态规划。最能直接想到的方法
     * f[i] 表示将区间 [0,i)覆盖所需要的最少子区间的数量
     * 初始化 f[i] 为 0x3f3f3f3f,f[0] = 0,
     *
     * 递推关系：f[i] = min{f[aj]} + 1，其中 aj < i <= bj
     * 即前半部分可以用 f[aj]来代替，后面只要覆盖掉 i 这个位置即可
     *
     * 时间复杂度 O(T*N)
     */
    public int videoStitching(int[][] clips, int T) {
        int[] f = new int[T + 1];
        Arrays.fill(f, 0x3f3f3f3f);
        // 空区间所需要的最少子区间数量为 0
        f[0] = 0;
        for (int i = 1; i <= T; i++) {
            for (int[] clip : clips) {
                if (clip[0] < i && i <= clip[1]) {
                    f[i] = Math.min(f[i], f[clip[0]] + 1);
                }
            }
        }
        return f[T];
    }

    /**
     * 贪心
     *
     * 对于所有左端点相同的区间，右端点越远越好，我们只需要这最长的一段即可，也就是说，不有两段区间 左端点是一样的
     * 我们处理所有的子区间，对于每一个左端点i，记录以其为左端点时，最远的右端点，记为 maxn[i]
     *
     * 枚举每一个位置 i，我们需要记录不大于 i 的位置所能到达最远的右端点 maxRight，然后更新 maxRight（用maxn[i]）
     * 如果更新后发现 maxRight 为 i ，说明无法完成目标，返回-1
     * pre 用来记录上一个区间的结束，如果 pre == i，说明我们要启用一个新的区间
     *
     */
    public int videoStitching2(int[][] clips, int T) {
        int[] maxn = new int[T];
        for (int[] clip : clips) {
            if (clip[0] < T) {
                maxn[clip[0]] = Math.max(maxn[clip[0]], clip[1]);
            }
        }
        int maxRight = 0;
        int pre = 0;
        int ans = 0;
        for (int i = 0; i < T; i++) {
            maxRight = Math.max(maxRight, maxn[i]);
            if (maxRight == i) {
                return -1;
            }
            if (pre == i) {
                ans++;
                pre = maxRight;
            }
        }
        return ans;
    }





}