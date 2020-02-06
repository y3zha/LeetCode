package leetcode.coding;

public class _338_比特位计数 {

    //思路一：一个个求，这是很正常的思路
    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            res[i] = countOne(i);
        }
        return res;
    }

    private int countOne(int n) {
        int count = 0;
        while (n != 0) {
            if ((n & 1) == 1) {
                count++;
            }
            n >>= 1;
        }
        return count;
    }

    /**
     * 思路二：找规律
     * 对于奇数：二进制表示中，奇数一定比前面那个偶数多一个 1，因为多的就是最低位的 1。
     * 1 = 1, 3 = 11 ,5 = 101
     *
     * 对于偶数：二进制表示中，偶数中 1 的个数一定和除以 2 之后的那个数一样多。因为最低位是 0，除以 2 就是右移一位，
     *         也就是把那个 0 抹掉而已，所以 1 的个数是不变的。
     * 2 = 10， 4 = 100， 6 = 1 1 0 （3也是两个）...
     *
     * 1 = 1
     * 2 = 10
     * 3 = 11
     * 4 = 100
     * 5 = 101
     * 6 = 110
     * 7 = 111
     *
     */

    public int[] countBits2(int num) {
        int[] res = new int[num + 1];
        res[0] = 0;
        for (int i = 1; i <= num; i++) {
            //如果是奇数
            if ((i & 1) == 1) {
                //比前面偶数多个1
                res[i] = res[i - 1] + 1;
            } else {
                //如果是偶数，就是和它除以2的数一样多
                res[i] = res[i / 2];
            }
        }
        return res;
    }
}