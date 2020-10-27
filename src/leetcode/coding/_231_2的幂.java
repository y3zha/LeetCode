package leetcode.coding;

/**
 * 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
 *
 * 这个题就让人想到了hashmap的空间开的是2的幂，这里同样利用n & (n - 1) = 0
 *
 * 见326，3的幂，342，4的幂
 */
public class _231_2的幂 {

    //判断这个数字n是不是2的幂
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}