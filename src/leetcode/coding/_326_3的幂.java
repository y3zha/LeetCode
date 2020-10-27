package leetcode.coding;

/**
 * 给定一个整数，写一个函数来判断它是否是 3 的幂次方。
 *
 * 这个就没有什么窍门了
 *
 * 342 4的幂
 */
public class _326_3的幂 {

    //迭代写法
    public boolean isPowerOfThree(int n) {
        if (n == 0) {
            return false;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        return n == 1;  //如果是3的幂，最后一定是3的倍数除以3，判断和1相不相等即可
    }

    //递归
    public boolean isPowerOfThree2(int n) {
        return n > 0 && (n == 1 || (n % 3 == 0 && isPowerOfThree2(n / 3)));
    }

}