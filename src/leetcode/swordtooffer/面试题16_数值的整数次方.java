package leetcode.swordtooffer;

public class 面试题16_数值的整数次方 {

    /**
     * leetcode 50 题 pow(x,n)
     *
     * 快速幂
     */
    public double myPow(double x, int n) {
        long N = n; //先隐式转换到long类型，因为当n为integer.MIN_VALUE，-n就越出int的界了
        double base = N < 0 ? 1.0 / x : x;

        if (N<0) N = -N;
        double res = 1;
        while (N > 0) {
            if ((N & 1) == 1) {
                res *= base;
            }
            base *= base;
            N >>= 1;
        }
        return res;
    }
}