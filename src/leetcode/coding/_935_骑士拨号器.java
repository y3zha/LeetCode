package leetcode.coding;

import java.util.Arrays;

public class _935_骑士拨号器 {

    /**
     * 一种思路是，我直接遍历N次，看每个位置可能跳到的次数，然后都加起来
     */
    private static final int MOD = 1_000_000_007;

    public int knightDialer(int N) {
        long[] arr = new long[10];
        //初始情况下都是有可能跳到的
        Arrays.fill(arr, 1);
        //从第二次起跳开始
        for (int i = 2; i <= N; i++) {
            //0只能由4和6跳过来，其他以此类推,但是一定要用中间变量保存上一轮的结果，不然下面会用到，这样就不对了
            long a0 = arr[4] + arr[6];
            long a1 = arr[6] + arr[8];
            long a2 = arr[7] + arr[9];
            long a3 = arr[4] + arr[8];
            long a4 = arr[0] + arr[3] + arr[9];
            long a6 = arr[0] + arr[1] + arr[7];
            long a7 = arr[2] + arr[6];
            long a8 = arr[1] + arr[3];
            long a9 = arr[2] + arr[4];
            arr[0] = a0 % MOD;
            arr[1] = a1 % MOD;
            arr[2] = a2 % MOD;
            arr[3] = a3 % MOD;
            arr[4] = a4 % MOD;
            arr[5] = 0L;
            arr[6] = a6 % MOD;
            arr[7] = a7 % MOD;
            arr[8] = a8 % MOD;
            arr[9] = a9 % MOD;
        }
        long res = 0;
        for (long value : arr) {
            res += value;
        }
        return (int) (res%MOD);
    }
}