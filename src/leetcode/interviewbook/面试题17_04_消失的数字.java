package leetcode.interviewbook;

/**
 * 数组nums包含从0到n的所有整数，但其中缺了一个。找出缺的那个数
 *
 * 思路是利用异或，对同一个值异或两次，0～n异或一遍，然后nums中的数再异或一遍
 * 最后剩下的就是缺的那个数了
 * 老套路了
 */
public class 面试题17_04_消失的数字 {

    public int missingNumber(int[] nums) {
        int n = nums.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            res ^= i;
            res ^= nums[i];
        }
        res ^= n;   //最后一个别忘记处理
        return res;
    }
}