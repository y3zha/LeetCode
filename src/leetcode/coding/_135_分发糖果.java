package leetcode.coding;

import java.util.Arrays;

public class _135_分发糖果 {

    //评分高的必须获得更多的糖果，评分一样无所谓了


    /**
     * 首先给所有人一颗糖果
     *
     * 从左向右遍历 如果 rat[i] > rat[i-1] 那么第i个位置还要在之前一个基础上多给一颗糖果，此时只是满足从左向右的情况
     * 从右向左再遍历，如果 rat[i] > rat[i+1]，那么第i个位置还要在之前一个基础上再多给一颗糖果
     *
     * 用两个数组记录遍历的情况，
     * 最后结果就是每个位置取这个位置较大的 然后都加起来
     */
    public int candy(int[] rat) {
        int n = rat.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, 1);
        Arrays.fill(right, 1);
        int res = 0;
        //从左向右遍历
        for (int i = 1; i < n; i++) {
            if (rat[i] > rat[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }
        //从右向左遍历
        for (int i = n - 2; i >= 0; i--) {
            if (rat[i] > rat[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
        }
        for (int i = 0; i < n; i++) {
            res += Math.max(left[i], right[i]);
        }
        return res;
    }


}