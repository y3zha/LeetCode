package leetcode.swordtooffer;

public class 面试题56_数组中数字出现的次数II {

    // 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
    //三进制位运算
    //leetcode 137
    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0, threes = 0;
        for (int num : nums) {
            ones ^= num;
            twos |= ones & num;
            threes = ones & twos;
            ones &= ~threes;
            twos &= ~threes;
        }
        return ones;
    }
}