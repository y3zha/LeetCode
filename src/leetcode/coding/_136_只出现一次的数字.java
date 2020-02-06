package leetcode.coding;

/**
 * https://leetcode-cn.com/problems/single-number/solution/xue-suan-fa-jie-guo-xiang-dui-yu-guo-cheng-bu-na-y/
 * 这个大佬分析的太好了！
 * 异或运算 利用任意数异或0还是它本身。相同数字都变成0了，最后就是我们要找的
 * 异或有交换律定理，相同为0不同为1，不管数字先后，只要两个数字相同对应的二进制都会被异或为00000000，最后剩下的就是所要找的值
 */
public class _136_只出现一次的数字 {

    public static int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res = res ^ nums[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {4, 1, 2, 1, 2};
        singleNumber(nums);
    }

}