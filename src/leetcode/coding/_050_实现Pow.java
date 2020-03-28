package leetcode.coding;

/**
 * 实现 Pow(x, n)
 * 这边用快速幂的思想
 */
public class _050_实现Pow {

    //快速幂模版.....，没有考虑负数的情况
    // public static double myPow(double x, int n) {
    //     if (n == 0) {
    //         return 1;
    //     }
    //     double res = 1;
    //     while (n > 0) {     //没有考虑负数的情况
    //         //如果是奇数
    //         if ((n & 1) == 1) {
    //             res = res * x;
    //             n--;
    //         }
    //         x = (x * x);
    //         n /= 2;
    //     }
    //     return res;
    // }

    public static void main(String[] args) {
        System.out.println(myPow(2, Integer.MIN_VALUE));
    }

    public static double myPow(double x, int n) {
        long N = n; //先隐式转换到long类型，因为当n为integer.MIN_VALUE，取倒数就越出int的界了
        //如果是负数就转换成 1.0/x即可     1.0!
        double base = N < 0 ? 1.0 / x : x;
        if (N < 0) N = -N;

        double res = 1;
        while (N > 0) {
            if ((N & 1) == 1) {
                res = res * base;
                N--;
            }
            base = base * base;
            N /= 2;
        }
        return res;
    }
}