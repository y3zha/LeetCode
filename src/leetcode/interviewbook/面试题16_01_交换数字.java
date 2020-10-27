package leetcode.interviewbook;

public class 面试题16_01_交换数字 {

    /**
     * 编写一个函数，不用临时变量，直接交换numbers = [a, b]中a与b的值。
     * 思路一： 利用加法减法思路
     *      A = A+B 01
     *      B = A-B
     *      A = A-B
     *
     * 思路二： 更进一步，看到加法，我们自然就会想到异或运算
     *      a ^ b = c
     *      a ^ c = b
     *      b ^ c = a
     *
     *
     */

    public int[] swapNumbers(int[] nums) {
        int A = nums[0];    //0
        int B = nums[1];    //1
        long a = A + B;     //1
        int b = (int) (a - B);  //0
        a = a - b;  //1
        return new int[]{(int) a, b};
    }

    public int[] swapNumbers2(int[] nums) {
        nums[0] ^= nums[1]; //[c,b]
        nums[1] ^= nums[0]; //[c,a]
        nums[0] ^= nums[1]; //[b,a]
        return nums;
    }
}