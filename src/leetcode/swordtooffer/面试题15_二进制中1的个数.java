package leetcode.swordtooffer;

/**
 * 三种方法
 */
public class 面试题15_二进制中1的个数 {

    //正常位移解法 超时
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            if ((n & 1) == 1) {
                count++;
            }
            n >>= 1;
        }
        return count;
    }

    //lowbit解法
    public int hammingWeight2(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n -= n & (-n);
        }
        return count;
    }

    //利用n-1的解法
    public int hammingWeight3(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = n & (n - 1);
        }
        return count;
    }

    //方法四 Integer.bitCount
    public static int hammingWeight4(int i) {
        i = i - ((i >>> 1) & 0x55555555);
        i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
        i = (i + (i >>> 4)) & 0x0f0f0f0f;
        i = i + (i >>> 8);
        i = i + (i >>> 16);
        return i & 0x3f;
    }


}