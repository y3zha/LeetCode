package leetcode.coding;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
 * 算法应该具有线性时间复杂度。 不使用额外空间
 *
 * 其他数字出现两次，可以用异或运算的交换律变为0，但是这里是三次如何处理？
 * 这里明显不能用异或了
 *
 * https://leetcode-cn.com/problems/single-number-ii/solution/single-number-ii-mo-ni-san-jin-zhi-fa-by-jin407891/
 *
 * 三进制下不考虑进位的加法：通过定义某种运算 #，使得 0 # 1 = 1，1 # 1 = 2，2 # 1 = 0。在此运算规则下，
 * 出现了 33 次的数字的二进制所有位全部抵消为 00，而留下只出现 11 次的数字二进制对应位为 11。
 * 因此，在此运算规则下将整个 arr 中数字遍历加和，留下来的结果则为只出现 11 次的数字。
 *
 * 1、ones ^= num：记录至目前元素num，二进制某位出现 1 次（当某位出现 3 次时有 ones = 1（1^1^1=1） ，与 twos = 1 共同表示“出现 3 次”）；
 * 2、twos |= ones & num：记录至目前元素num，二进制某位出现 2 次 （当某位出现 2 次时，twos = 1 且 ones = 0 ）；
 * 3、threes = ones & twos：记录至目前元素num，二进制某位出现 3 次（即当 ones 和 twos对应位同时为 1 时 three = 1）。
 * 4、one &= ～threes, two &= ～threes：将 ones twos 中出现了 3 次的对应位清零（取反），实现 “不考虑进位的三进制加法” 。
 *
 */
public class _137_只出现一次的数字II {

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