package leetcode.swordtooffer;

public class 面试题17_打印从1到最大的n位数 {

    /**
     * 直接求10^n 可以快速幂求
     * 复习下快速幂
     */

    public int[] printNumbers(int n) {
        //用快速幂求10的n次方
        int base = 10;
        int temp = 1;
        while (n != 0) {
            if ((n & 1) == 1) {
                temp *= base;
            }
            base *= base;
            n >>= 1;
        }
        int[] res = new int[temp - 1];
        for (int i = 0; i < temp - 1; i++) {
            res[i] = i + 1;
        }
        return res;
    }
}