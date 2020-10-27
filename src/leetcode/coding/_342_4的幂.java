package leetcode.coding;

public class _342_4的幂 {

    //3的幂的写法
    public boolean isPowerOfFour(int n) {
        if (n == 0) {
            return false;
        }
        while (n % 4 == 0) {
            n /= 4;
        }
        return n == 1;
    }

    public boolean isPowerOfFour2(int n) {
        return n > 0 && (n == 1 || (n % 4 == 0 && isPowerOfFour2(n / 4)));
    }
}