package leetcode.coding;

/**
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * 思路一：用减法，看能减多少次，这是用于正数的情况，对于负数，加上符号，这个思路会超时，效率太慢，当被除数为2147483648，除数为 1，必然超时
 *
 * 使用倍增的思想优化, 可以将减法的次数优化到对数时间复杂度.
 * 我们将除数左移一位(或者让它加上自己), 即得到了二倍的除数, 这时一次减法相当于减去了2个除数. 不断倍增
 * 与此同时还需要一个 变量 记录此时的除数是最初的除数的多少倍, 每次减法后都加到 结果result 上即可.
 *
 */
public class _029_两数相除 {

    public int divide(int dividend, int divisor) {
        //题目中除数不为0，但这里习惯性地判断下
        if (divisor == 0) {
            return dividend >= 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        if (dividend == 0) {
            return 0;
        }
        //越界情况 -2147483648 / -1
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        //记录最终结果符号
        boolean isNegative = (dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0);

        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);

        //倍增法
        int res = 0;
        while (a >= b) {
            int temp = 0;   //记录此时地除数是最初除数的多少倍
            while (a >= (b << temp)) {  //比如原来是左移0位，然后左移1位、2位，然后temp到了3，发现a小了，实际上一共能除 2^2次，在结果计算时就要temp-1
                temp++;
            }
            a -= b << (temp - 1);
            res += 1 << (temp - 1);
        }
        return isNegative ? -res : res;
    }

    /**
     * 比如15 / 3
     * 15 >= 3   temp 0 -> 1
     * 15 >= 6   temp 1 -> 2
     * 15 >= 12  temp 2 -> 3
     * 15 < 24   break          15 -= 3 << (3-1)    a = 15-12 = 3
     *                          res += 1 << 2       res = 4
     * 3 >=3     temp 0 -> 1
     * 3 < 6     break          a = 3-3
     *                          res = 4 + 1<<(2-1)
     */

}