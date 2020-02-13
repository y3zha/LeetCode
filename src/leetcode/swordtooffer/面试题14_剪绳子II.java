package leetcode.swordtooffer;

import java.math.BigInteger;
import java.util.Arrays;

public class 面试题14_剪绳子II {

    /**
     * 剪绳子1的基础上n上限为1000，开long也没用的
     * 只能使用贪心策略了
     */

    //我下面这么写也还是溢出的。。。
    public static int cuttingRope_wrongVersion(int n) {
        int MOD = 1_000_000_007;
        if (n <= 3) {
            return n - 1;
        }
        int timesOf3 = n / 3;
        int timesOf2 = 0;
        if (n % 3 == 0) {
            timesOf2 = 0;
        } else if (n % 3 == 1) {
            timesOf2 = 2;
            timesOf3--;
        } else if (n % 3 == 2) {
            timesOf2 = 1;
        }
        int res = 1;
        for (int i = 0; i < timesOf3; i++) {
            res = res * 3 % MOD;
        }
        for (int i = 0; i < timesOf2; i++) {
            res = res * 2 % MOD;
        }
        return res;
    }

    //只能使用BigInteger了
    //跑的时候记得导包
    public static int cuttingRope(int n) {
        int MOD = 1_000_000_007;
        if (n <= 3) {
            return n - 1;
        }
        int timesOf3 = n / 3;
        int timesOf2 = 0;
        if (n % 3 == 0) {
            timesOf2 = 0;
        } else if (n % 3 == 1) {
            timesOf2 = 2;
            timesOf3--;
        } else if (n % 3 == 2) {
            timesOf2 = 1;
        }
        BigInteger[] arr = new BigInteger[timesOf2 + timesOf3 + 1];
        // BigDecimal数组在被声明后，所有元素都是null
        //需要初始化
        Arrays.fill(arr, BigInteger.ONE);
        int index = 1;
        BigInteger three = new BigInteger("3");
        BigInteger two = new BigInteger("2");
        for (int i = 0; i < timesOf3; i++) {
            arr[index] = arr[index].multiply(arr[index - 1]).multiply(three);
            index++;
        }
        for (int i = 0; i < timesOf2; i++) {
            arr[index] = arr[index].multiply(arr[index - 1]).multiply(two);
            index++;
        }
        return arr[index - 1].mod(new BigInteger("1000000007")).intValue();
    }

    public static void main(String[] args) {
        System.out.println(cuttingRope(7));
        BigInteger bi1 = new BigInteger("3");
        BigInteger bi2 = new BigInteger("2");

        // System.out.println(bi1.multiply(bi2));

    }



}