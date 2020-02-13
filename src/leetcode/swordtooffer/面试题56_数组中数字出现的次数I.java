package leetcode.swordtooffer;

public class 面试题56_数组中数字出现的次数I {

    /**
     * 简明扼要
     * 1.对所有数字异或,一样的数字抵消,出现一次的两个数字异或运算后必定不为0;
     * 2.用lowbit得到一个二进制位最右边一位为1的数字 mask;    (也就是两者出现不等的地方,异或运算。。相同为0，不同为1)
     * 3.mask和数组的每个数字做与运算,等于0的分为一组,等于mask的分为一组,同时也将两个不一样的数字分开;
     */
    public int[] singleNumbers(int[] nums) {
        int temp = 0;
        for (int num : nums) {
            temp ^= num;
        }
        int mask = temp & (-temp);
        int[] res = new int[2];
        for (int num : nums) {
            if ((num & mask) == 0) {
                res[0] ^= num;
            } else {
                res[1] ^= num;
            }
        }
        return res;
    }
}