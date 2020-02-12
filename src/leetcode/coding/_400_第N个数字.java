package leetcode.coding;

public class _400_第N个数字 {

    /**
     * 思路
     * 1、将一个数字进行拆分 ，1位数一共十个数字，10位，2位数一共90个数字，180位...
     * 2、要确定n所在的位数是第几位，先对位数取商，计算出n位对应的数字a，再取余，就能得出a是第几位
     * 解释下，n是第几位，我们要不断更新 比如n=11，我们算出1个数字的情况下一共是10位，然后n-10，就是在下了一轮中的第几位
     * n/base，base是代表 【几位数】，n/base就能得到这个数字是当前位数内的第几个数字，得到这个数字之后。这个数字的 n%base位就是我们要找的啦。
     *
     * 细节：对1e9处理
     */
    public int findNthDigit(int n) {
        if (n < 10) {
            return n;
        }
        int base = 1;
        long count = 0;  //计算有多少位,测试的时候发现有个1e9的用例，这个用例会导致count越界，所以要用long类型
        while (true) {
            count = helper(base);
            if (n < count) break;
            n -= count;
            base++;
        }
        //得到新的n和count了，算出第n位对应什么数字
        int num = (int) (n / base + Math.pow(10, base - 1));
        return String.valueOf(num).charAt(n % base) - '0';
    }

    // 计算当前有多少位 1位数10种，10位；2位数 90个数字 180位；3位数 900个 2700位
    private long helper(int base) {
        if (base == 1) {
            return 10;
        }
        return (long) (Math.pow(10, base - 1) * 9 * base);
    }
}