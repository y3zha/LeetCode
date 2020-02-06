package leetcode.coding;

public class _191_位1的个数 {

    //解法一，逐位判断，常规饿的解法
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            if ((n & 1) == 1) {
                count++;
            }
            n = n >> 1;
        }
        return count;
    }

    /**
     * 解法二：
     * 有一个方法，可以把最右边的 1 置为 0，就是n&(n-1)，这个很常用，比如hashmap底层
     * 比如十进制的 10，二进制形式是 1010，然后我们只需要把它和 9 进行按位与操作，也就是 10 & 9 = (1010) & (1001) = 1000，也就是把 1010 最右边的 1 置为 0。
     * 规律就是对于任意一个数 n，然后 n & (n-1) 的结果就是把 n 的最右边的 1 置为 0 。
     * 有了这个技巧，我们只需要把原数依次将最右边的 1 置为 0，直到原数变成 0，记录总共操作了几次即可。
     */
    public int hammingWeight2(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }
}