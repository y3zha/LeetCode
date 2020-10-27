package leetcode.coding;

import java.math.BigInteger;

public class _1259_不相交的握手 {

    /**
     * 把每一个人视作一个顶点，握手视作一条边
     * 那么有效的握手是：经过一次握手，把图分成两个部分，每个部分都含有偶数个顶点
     * 总人数为偶数，那么经过分析，当前人 p 和p+1握手后，剩余部分为偶数
     * 要想找到多个有效的握手，p只能继续和 (p+1) + 2，(p+1) + 4...继续握手，这样划分的两个部分才能是偶数个
     *
     * @param n n个人
     * @return 方案数
     */
    public int numberOfWays(int n) {
        // f[i]表示有i个人时候的方案数
        int mod = (int) (1e9 + 7);
        long[] f = new long[n + 1];
        f[0] = 1;
        for (int i = 2; i <= n; i += 2) {
            f[i] = 0;
            // 求 0、2、4...i-4、i-2 的方案数
            for (int j = 2; j <= i; j++) {
                f[i] = (f[i] + f[j - 2] * f[i - j] % mod) % mod;
            }
        }
        return (int) f[n];
    }

    /**
     * 给定 n 求卡特兰数
     * C(2n n)/n+1
     * 令f(0)=1, f(1)=1, catalan数满足递归式：
     * f(n)=f(n-1)*(4*n-2)/(n+1);
     */
    public int numberOfWays2(int n) {
        n /= 2;
        BigInteger mod = BigInteger.valueOf((long) (1000000007));
        BigInteger four = BigInteger.valueOf(4);
        BigInteger two = BigInteger.valueOf(2);
        BigInteger one = BigInteger.valueOf(1);
        BigInteger ans = BigInteger.valueOf(1);
        for (int i = 2; i <= n; i++) {
            BigInteger bi = BigInteger.valueOf(i);
            ans = ans.multiply(four.multiply(bi).subtract(two)).divide(bi.add(one));
        }
        // 一定要最后再mod.不能在计算过程中mod，因为有个除法，如果一边算一边mod，就不是卡特兰数了
        return ans.mod(mod).intValue();
    }

}